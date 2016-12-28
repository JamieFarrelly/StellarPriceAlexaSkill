package com.jamiefarrelly.PayWithFireAlexa.model;

public class CurrencyTypeDescription {
    
    private CurrencyType code;
    
    private String description;

    public CurrencyType getCode() {
        return code;
    }

    public void setCode(CurrencyType type) {
        this.code = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
