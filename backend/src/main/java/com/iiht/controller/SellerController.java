package com.iiht.controller;

import com.iiht.dto.ProductDto;
import com.iiht.response.ProductDataResponse;
import com.iiht.response.ProductResponse;
import com.iiht.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RestController
@RequestMapping("/e-auction/api/v1/seller")
@CrossOrigin(origins = "http://localhost:3000")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping(value = "/add-product")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto) throws Exception {
        sellerService.addProduct(productDto);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/show-bids/{productId}")
    public ResponseEntity<ProductDataResponse> showBidsForProduct(@PathVariable("productId") String productId) throws Exception {
        ProductDataResponse productDataResponse = sellerService.showBidsForProduct(productId);
        return new ResponseEntity<>(productDataResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") String productId) throws Exception {
        sellerService.deleteProduct(productId);
        return new ResponseEntity<>("Product Delete Successfully", HttpStatus.OK);
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponse = sellerService.getAllProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
}
