/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.model;

import com.caina.cwi.schema.QuotationDataSchema;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author web
 */
public class QuotationModel {
    
    private List<QuotationDataSchema> quotationDataSquemaList;
    private QuotationDataSchema quotationDataSchema;
    
    private QuotationDataSchema quotationFrom;
    private QuotationDataSchema quotationto;
    
    /*
    
    @param List of strings 
    @return void
    **/
    public void setQuotationCSVData(List<String[]> readedCsvFile) {
        quotationDataSquemaList = new ArrayList();
        readedCsvFile.forEach((csvLineData) -> {
            quotationDataSchema = new QuotationDataSchema();
            try {
                quotationDataSchema.populateCSVListData(csvLineData);
            } catch (ParseException ex) {
                Logger.getLogger(QuotationModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            quotationDataSquemaList.add(quotationDataSchema);
        });
    }
    
    /**
     * Create the currency exchanger,
     * the math is, just devide the currecy from by the currency to, as an rule by three would do
     * @param currencyFrom
     * @param currencyTo
     * @param valueConvert
     * @return
     */
    public BigDecimal convertCurrency(String currencyFrom, String currencyTo, BigDecimal valueConvert){
        //pegar a moeda from
        quotationFrom = getQuotationByCurrency(currencyFrom);
        System.out.println(quotationFrom.getBuyingRate().toString());
                
        //pegar a moeda to
        quotationto = getQuotationByCurrency(currencyTo);
        System.out.println(quotationto.getBuyingRate().toString());
        
        System.out.println(quotationFrom.getBuyingRate().divide(quotationto.getBuyingRate()).toString());
        //converter
        return valueConvert.multiply(
                    quotationFrom.getBuyingRate().divide(quotationto.getBuyingRate(), 3, RoundingMode.CEILING)
                );
    }
    
    public QuotationDataSchema getQuotationByCurrency(String currencyName){
        for(QuotationDataSchema quotationSchema : quotationDataSquemaList ){
            if(quotationSchema.isCurrency(currencyName)){
                return quotationSchema;
            }
        }
        return null;
    }
    
}
