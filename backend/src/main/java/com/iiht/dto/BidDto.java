package com.iiht.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class BidDto {

    @Schema(type = "string", example = "62af4c733c341260c2fcea2b")
    public String productId;

    @Schema(type = "double", example = "15000")
    public Double bidAmount;

    @NotNull
    @Size(min = 5, max = 30)
    @Schema(type = "string", example = "Rakesh")
    public String buyerFirstName;

    @NotNull
    @Size(min = 3, max = 25)
    @Schema(type = "string", example = "Aggarwal")
    public String buyerLastName;

    @Schema(type = "string", example = "M-334, Happy Homes")
    public String buyerAddress;

    @Schema(type = "string", example = "Gurgaon")
    public String buyerCity;

    @Schema(type = "string", example = "Haryana")
    public String buyerState;

    @Schema(type = "string", example = "110038")
    public String buyerPin;

    @NotNull
    @Size(min = 10, max = 10)
    @Schema(type = "string", example = "6374852375")
    public String buyerPhone;

    @NotNull
    @Email
    @Schema(type = "string", example = "rakesh.aggarwal1999@gmail.com")
    public String buyerEmail;

}
