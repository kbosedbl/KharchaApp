package com.kaustav_bose.kharchaapp;

class Data_Model {
    String category;
    float amount;

    public Data_Model(String category, float amount ) {
        this.amount=amount;
        this.category=category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
