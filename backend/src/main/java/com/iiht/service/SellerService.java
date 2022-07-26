package com.iiht.service;

import com.iiht.dto.ProductDto;
import com.iiht.entity.BidEntity;
import com.iiht.entity.ProductEntity;
import com.iiht.exception.custom.InvalidInputException;
import com.iiht.repository.BidRepository;
import com.iiht.repository.ProductRepository;
import com.iiht.response.BidDataResponse;
import com.iiht.response.ProductDataResponse;
import com.iiht.response.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final Logger logger = LoggerFactory.getLogger(SellerService.class);

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BidRepository bidRepository;

    public void addProduct(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
        productRepository.save(productEntity);
    }

    public ProductDataResponse showBidsForProduct(String productId) throws Exception {

        Optional<ProductEntity> productEntity = productRepository.findById(productId);

        if (productEntity.isEmpty()) {
            logger.warn("Given product id is unavailable");
            throw new InvalidInputException("Given product id is unavailable");
        }

        ProductEntity product = productEntity.get();

        List<BidEntity> bidEntityList = bidRepository.findAllByProductId(productId);

        return getProductDataResponseDto(product, bidEntityList);
    }

    public void deleteProduct(String productId) throws Exception {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()) {
            logger.warn("Given product id is unavailable");
            throw new InvalidInputException("Given product id is unavailable");
        }

        ProductEntity product = productEntity.get();

        Date currentDate = new Date();
        if (currentDate.compareTo(product.getBidEndDate()) > 0) {
            logger.warn("Product cannot be deleted after bid end date");
            throw new InvalidInputException("Product cannot be deleted after bid end date");
        }

        int numOfBids = bidRepository.countNumberOfBidsForProduct(productId);
        if (numOfBids > 0) {
            logger.warn("At least one Bid has been placed on the product");
            throw new InvalidInputException("At least one Bid has been placed on the product");
        }

        productRepository.delete(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductResponse> productResponses = new ArrayList<>();
        productEntities.forEach(productEntity -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductID(productEntity.getId());
            productResponse.setProductName(productEntity.getProductName());
            productResponses.add(productResponse);
        });
        return productResponses;
    }

    private ProductDataResponse getProductDataResponseDto(ProductEntity product, List<BidEntity> bidEntityList) {
        ProductDataResponse productDataResponse = new ProductDataResponse();
        productDataResponse.setProductName(product.getProductName());
        productDataResponse.setShortDescription(product.getShortDescription());
        productDataResponse.setDetailedDescription(product.getDetailedDescription());
        productDataResponse.setCategory(product.getCategory());
        productDataResponse.setStartingPrice(product.getStartingPrice());
        productDataResponse.setBidEndDate(product.getBidEndDate());

        productDataResponse.bidsPlaced = new ArrayList<>();
        bidEntityList.forEach(bidEntity -> {
            BidDataResponse bidDataResponse = new BidDataResponse();
            bidDataResponse.setBidAmount(bidEntity.getBidAmount());
            bidDataResponse.setBuyerFirstName(bidEntity.getBuyerFirstName());
            bidDataResponse.setBuyerLastName(bidEntity.getBuyerLastName());
            bidDataResponse.setBuyerEmail(bidEntity.getBuyerEmail());
            bidDataResponse.setBuyerPhone(bidEntity.getBuyerPhone());
            productDataResponse.bidsPlaced.add(bidDataResponse);
        });

        return productDataResponse;
    }

}
