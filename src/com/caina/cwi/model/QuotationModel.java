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
 * @author caina
 */
public class QuotationModel {
    
    private List<QuotationDataSchema> quotationDataSquemaList;
    private QuotationDataSchema quotationDataSchema;
    
    private QuotationDataSchema quotationFrom;
    private QuotationDataSchema quotationto;
    
    /*
    Receive the CSV file and convert to a list of Schemas
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
    public BigDecimal convertCurrency(String currencyFrom, String currencyTo, BigDecimal valueConvert) throws Exception{
        
        if(valueConvert.signum()  == -1){
            throw new Exception("Value must be more than zero");
        }
        
        quotationFrom = getQuotationByCurrency(currencyFrom);
        quotationto   = getQuotationByCurrency(currencyTo);
        return valueConvert.multiply(
                    quotationFrom.getBuyingRate().divide(quotationto.getBuyingRate(), 7, RoundingMode.DOWN)
                );
    }
    
    /**
     * Iteract with a list of Schemas to find an given currency, if no one found, throw exception 
     * @param currencyName The name of a currency to look up
     * @return Respective Quotation
     * @throws Exception
     */
    public QuotationDataSchema getQuotationByCurrency(String currencyName) throws Exception{
        for(QuotationDataSchema quotationSchema : quotationDataSquemaList ){
            if(quotationSchema.isCurrency(currencyName)){
                return quotationSchema;
            }
        }
        throw new Exception("Currency not found");
    }
    
}
