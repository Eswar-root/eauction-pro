package com.iiht.response;

import com.iiht.enums.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductDataResponse {

    public String productName;

    public String shortDescription;

    public String detailedDescription;

    public Category category;

    public Double startingPrice;

    public Date bidEndDate;

    public List<BidDataResponse> bidsPlaced;

}
