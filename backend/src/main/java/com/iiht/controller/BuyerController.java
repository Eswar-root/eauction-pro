package com.iiht.controller;

import com.iiht.dto.BidDto;
import com.iiht.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RestController
@RequestMapping("/e-auction/api/v1/buyer")
public class BuyerController {

    @Autowired
    BuyerService buyerService;

    @PostMapping("/place-bid")
    public ResponseEntity<BidDto> placeBid(@Valid @RequestBody BidDto bidDto) throws Exception {
        BidDto bidDtoSaved = buyerService.placeBid(bidDto);
        return new ResponseEntity<>(bidDtoSaved, HttpStatus.OK);
    }

    @PutMapping("/update-bid/{productId}/{buyerEmailId}/{newBidAmount}")
    public ResponseEntity<BidDto> updateBid(@PathVariable("productId") String productId, @PathVariable("buyerEmailId") String buyerEmailId,
                                            @PathVariable("newBidAmount") Double newBidAmount) throws Exception {
        BidDto bidDto = buyerService.updateBid(productId, buyerEmailId, newBidAmount);
        return new ResponseEntity<>(bidDto, HttpStatus.OK);
    }
}
