import React from "react";
import SellerService from "../service/SellerService";
import dateFormat from 'dateformat';
import auctionImg from '../auction.png';
import PulseLoader from "react-spinners/PulseLoader";

class SellerComponent extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            products: [],
            bids: [],
            product: {},
            productId: "",
            fetchedDetails: true
        }
    }

    componentDidMount() {
        SellerService.fetchAllProducts().then((response) => {
            this.setState({ products: response.data});
            this.setState({ productId: response.data[0].productID})
        })
    }

    render () {

        const product = this.state.product;
        const bids = this.state.bids;
        const products = this.state.products;
        const fetchedDetails = this.state.fetchedDetails;

        const handleFetchBidDetails = () => {
            this.setState({ fetchedDetails: false});
            this.setState({ product: {}});
            SellerService.fetchBidDetails(this.state.productId).then((response) => {
                this.setState({ bids: response.data.bidsPlaced });
                this.setState({ product: response.data });
                this.setState({ fetchedDetails: true });
            })
        }

        const handleProductChange = (event) => {
            this.setState({ productId: event.target.value });
        }

        return (
            <div>
                <img src={auctionImg} alt="E-Auction" style={{marginTop: "30px", marginLeft: "50px", height: "120px"}}/>
                <div className="input-group mb-3" style={{margin: "auto", width: "30%", marginBottom: "70px", marginTop: "-40px"}}>
                    <div className="input-group-prepend">
                        <label className="control-label" style={{marginRight: "20px", 
                        marginTop: "5px", fontSize: "18px"}}>Product</label>
                    </div>
                    <select id="product" className="form-select form-select-sm" onChange={handleProductChange} value={this.state.productId}>
                            <option value="default" disabled={true}>Select Product</option>
                            {
                                products.map(
                                    product => <option key={product.productID} value={product.productID}>{product.productName}</option>
                                )
                            }
                    </select>
                    <div className="input-group-append" style={{marginLeft: "20px"}}>
                        <button className="btn btn-outline-secondary" type="button" onClick={handleFetchBidDetails}>GET Details</button>
                    </div>
                </div>

                {!fetchedDetails &&
                    <div className="text-center" style={{marginTop: "200px"}}>
                        <PulseLoader color={"#9B9B9B"} loading={!fetchedDetails} size={15} />
                    </div>
                }

                {product.productName && 
                    <>
                        <table className="table table-striped table-borderless text-center" style={{margin: "auto", width: "50%", marginBottom: "45px", marginTop: "50px"}}>
                            <tbody>
                                <tr>
                                    <th scope="row">Product Name</th>
                                    <td>{product.productName}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Short Description</th>
                                    <td>{product.shortDescription}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Detailed Description</th>
                                    <td>{product.detailedDescription}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Category</th>
                                    <td>{product.category}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Starting Price</th>
                                    <td>{product.startingPrice}</td>
                                </tr>
                                <tr>
                                    <th scope="row">Bid End Date</th>
                                    <td>{dateFormat(product.bidEndDate, "mmmm dS, yyyy")}</td>
                                </tr>
                            </tbody>
                        </table>

                        {bids.length === 0 && 
                            <p className="text-center">No Bids have been placed on this product yet!!!</p>
                        }

                        { bids.length > 0 && 

                        <div style={{margin: "auto", width: "50%"}}>
                        <p className="text-center" style={{fontSize: "20px"}}> Bids </p>
                        <table className = "table table-striped table-borderless text-center">
                            <thead>
                                <tr>
                                    <td><b>Bid Amount</b></td>
                                    <td><b>Name</b></td>
                                    <td><b>Email</b></td>
                                    <td><b>Mobile</b></td>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.bids.map(
                                        bid =>
                                            <tr key = {bid.buyerEmail}>
                                                <td> {bid.bidAmount} </td>
                                                <td> {bid.buyerFirstName + " " + bid.buyerLastName} </td>
                                                <td> {bid.buyerEmail} </td>
                                                <td> {bid.buyerPhone} </td>
                                            </tr>
                                    )
                                }
                            </tbody>
                        </table>
                        </div>
                        }
                    </>
                }

                
            </div>
        )
    }

}

export default SellerComponent;