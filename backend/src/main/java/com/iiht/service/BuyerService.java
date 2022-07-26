package com.iiht.service;

import com.iiht.dto.BidDto;
import com.iiht.entity.BidEntity;
import com.iiht.entity.ProductEntity;
import com.iiht.exception.custom.InvalidInputException;
import com.iiht.repository.BidRepository;
import com.iiht.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BuyerService {

    private final Logger logger = LoggerFactory.getLogger(BuyerService.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BidRepository bidRepository;

    public BidDto placeBid(BidDto bidDto) throws Exception {
        Optional<ProductEntity> productEntity = productRepository.findById(bidDto.productId);
        if (productEntity.isEmpty()) {
            logger.warn("Product Id not available for bidding");
            throw new InvalidInputException("Product Id not available for bidding");
        }

        ProductEntity product = productEntity.get();

        Date currentDate = new Date();
        if (currentDate.compareTo(product.getBidEndDate()) > 0) {
            logger.warn("Bidding date is after the product's bidding end date");
            throw new InvalidInputException("Bidding date is after the product's bidding end date");
        }

        BidEntity previousBid = bidRepository.findByProductIdAndBuyerEmail(bidDto.productId, bidDto.buyerEmail);
        if (previousBid != null) {
            logger.warn("The user has already placed a bid for this product.");
            throw new InvalidInputException("The user has already placed a bid for this product.");
        }

        BidEntity bidEntity = new BidEntity();
        BeanUtils.copyProperties(bidDto, bidEntity);
        bidRepository.save(bidEntity);
        return bidDto;
    }

    public BidDto updateBid(String productId, String buyerEmailId, Double newBidAmount) throws Exception {
        BidEntity bidEntity = bidRepository.findByProductIdAndBuyerEmail(productId, buyerEmailId);
        if (bidEntity == null) {
            logger.warn("No previous bid available to update for this productId and user");
            throw new InvalidInputException("No previous bid available to update for this productId and user");
        }

        ProductEntity productEntity = productRepository.findById(productId).get();

        Date currentDate = new Date();
        if (currentDate.compareTo(productEntity.getBidEndDate()) > 0) {
            logger.warn("Cannot update Bid after the product's bidding end date");
            throw new InvalidInputException("Cannot update Bid after the product's bidding end date");
        }

        BidDto bidDto = new BidDto();
        BeanUtils.copyProperties(bidEntity, bidDto);
        bidDto.bidAmount = newBidAmount;
        BeanUtils.copyProperties(bidDto, bidEntity);
        bidRepository.save(bidEntity);
        return bidDto;
    }

}
