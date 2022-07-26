import axios from 'axios';

const URL = 'http://localhost:8080/e-auction/api/v1/seller/';

class SellerService {

    fetchAllProducts() {
        return axios.get(URL + "get-all-products");
    }

    fetchBidDetails(productId) {
        console.log(URL + "show-bids/" + productId);
        return axios.get(URL + "show-bids/" + productId)
    }

}

export default new SellerService()