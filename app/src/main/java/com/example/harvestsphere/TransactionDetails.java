package com.example.harvestsphere;

public class TransactionDetails {

    public String RetailerID,BiddingPrice;
    public TransactionDetails() {

    }

    public TransactionDetails(String retailerID, String biddingPrice) {
        this.RetailerID = retailerID;
        this.BiddingPrice = biddingPrice;
    }
}
