package com.repair.lsb.easyrepair.model;

import java.util.ArrayList;

public class Type {
    private String type;
    private ArrayList<Merchant> merchants;

    public Type(String type,ArrayList<Merchant> merchants){
        this.type = type;
        this.merchants = new ArrayList<>(merchants);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(ArrayList<Merchant> merchants) {
        this.merchants = merchants;
    }
}
