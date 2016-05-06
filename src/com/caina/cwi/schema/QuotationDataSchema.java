/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.schema;

import com.caina.cwi.helper.Helper;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author caina
 */
public class QuotationDataSchema {

    private Date        quotationDate;
    private String      currency;
    private BigDecimal  buyingRate;
    private String      quotationType;
    private BigDecimal  quotationParity;

    public void populateCSVListData(String[] csvLineData) throws ParseException {
        this.setQuotationDate(Helper.convertStringToDate(csvLineData[0]));
        this.setCurrency(csvLineData[3]);
        this.setBuyingRate(Helper.stringToBigDecimal(csvLineData[4]));
        this.setQuotationType(csvLineData[2]);
        this.setQuotationParity(Helper.stringToBigDecimal(csvLineData[6]));
    }

    public Date getQuotationDate() {
        return quotationDate;
    }

    public void setQuotationDate(Date quotationDate) {
        this.quotationDate = quotationDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBuyingRate() {
        return buyingRate;
    }
    
    public BigDecimal getBuyingRateParity(){
        if(this.getQuotationType().equals("A")){
            return buyingRate.divide(getQuotationParity(),6, RoundingMode.CEILING);
        }else{
            return buyingRate.multiply(getQuotationParity());
        }
    }
    
    public void setBuyingRate(BigDecimal buyingRate) {
        this.buyingRate = buyingRate;
    }

    public boolean isCurrency(String currencyName) {
        return getCurrency().equals(currencyName);
    }

    public String getQuotationType() {
        return quotationType;
    }

    public void setQuotationType(String quotationType) {
        this.quotationType = quotationType;
    }

    public BigDecimal getQuotationParity() {
        return quotationParity;
    }

    public void setQuotationParity(BigDecimal quotationParity) {
        this.quotationParity = quotationParity;
    }
  
}