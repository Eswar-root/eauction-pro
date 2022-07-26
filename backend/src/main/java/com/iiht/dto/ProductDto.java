package com.iiht.dto;

import com.iiht.enums.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class ProductDto {
    
    @NotNull(message = "Product Name should not be null")
    @Size(min = 5, max = 30)
    @Schema(type = "string", example = "Lamp Stand")
    public String productName;

    @Schema(type = "string", example = "Ancient Lamp Stand")
    public String shortDescription;

    @Schema(type = "string", example = "Used for lightening up the area")
    public String detailedDescription;

    public Category category;

    @Schema(type = "double", example = "10000")
    public Double startingPrice;

    @Future
    @Schema(type = "string", example = "2022-08-22")
    public Date bidEndDate;

    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string", example = "Anush")
    public String sellerFirstName;

    @NotNull
    @Size(min = 3, max = 25)
    @Schema(type = "string", example = "Kumar")
    public String sellerLastName;

    @Schema(type = "string", example = "H-832, Royal Gardens")
    public String sellerAddress;

    @Schema(type = "string", example = "Bangalore")
    public String sellerCity;

    @Schema(type = "string", example = "Karnataka")
    public String sellerState;

    @Schema(type = "string", example = "530068")
    public Double sellerPin;

    @NotNull
    @Size(min = 10, max = 10)
    @Schema(type = "string", example = "6723568594")
    public String sellerPhone;

    @NotNull
    @Email
    @Schema( type = "string", example = "aman.kumar2000@gmail.com")
    public String sellerEmail;
}
