package com.iiht.entity;

import com.iiht.enums.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection = "product")
@Getter
@Setter
public class ProductEntity {

    @Id
    private String id;

    @Field
    private String productName;

    @Field
    private String shortDescription;

    @Field
    private String detailedDescription;

    @Field
    private Category category;

    @Field
    private Double startingPrice;

    @Field
    private Date bidEndDate;

    @Field
    private String sellerFirstName;

    @Field
    private String sellerLastName;

    @Field
    private String sellerAddress;

    @Field
    private String sellerCity;

    @Field
    private String sellerState;

    @Field
    private Double sellerPin;

    @Field
    private String sellerPhone;

    @Field
    private String sellerEmail;
}
