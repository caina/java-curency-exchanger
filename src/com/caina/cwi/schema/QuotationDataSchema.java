/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.schema;

import com.caina.cwi.helper.Helper;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author caina
 */
public class QuotationDataSchema {

    private Date       quotationDate;
    private String     currency;
    private BigDecimal buyingRate;

    
    public void populateCSVListData(String[] csvLineData) throws ParseException {
        this.setQuotationDate(Helper.convertStringToDate(csvLineData[0]));
        this.setCurrency(csvLineData[3]);
        this.setBuyingRate(Helper.stringToBigDecimal(csvLineData[4]));
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

    public void setBuyingRate(BigDecimal buyingRate) {
        this.buyingRate = buyingRate;
    }

    public boolean isCurrency(String currencyName) {
        return getCurrency().equals(currencyName);
    }
  
}
