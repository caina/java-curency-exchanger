package com.caina.cwi.controller;

import com.caina.cwi.helper.Helper;
import com.caina.cwi.model.QuotationModel;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caina
 */
public class Currency {

    private final String QUOTATION_CSV_URL_PATH = "http://www4.bcb.gov.br/Download/fechamento/";
    private String CSV_FILE_PATH;
    
    private QuotationModel quotationModel;
    private Date quotationDate;
    
    /**
     * Calculate the exchange for a given currency
     * @param currencyFrom 
     * @param currencyTo 
     * @param exchangeValue any positive value
     * @param quotationDate in the format dd/MM/yyyy
     * @return the exchange value
     * @throws Exception
     */
    public BigDecimal currencyQuotation(String currencyFrom, String currencyTo, Number exchangeValue, String quotationDate) throws Exception{
        
        this.quotationModel = new QuotationModel();
        this.getLastWorkingDayQuotation(quotationDate);
           
        if(this.quotationDate.after(new Date())){
            throw new Exception("The date is invalid");
        }
        
        this.quotationModel.setQuotationCSVData(this.downloadAndReadCSVFile());
        BigDecimal _exchangedNonFormated = this.quotationModel.convertCurrency(currencyFrom, currencyTo, new BigDecimal(exchangeValue.toString()));
        return _exchangedNonFormated.setScale(3, RoundingMode.DOWN).setScale(2,RoundingMode.DOWN);
    }
    
    /*
        Download the csv quotation file, instantiate the Quotation model and
        populate the data with the list readed from the file.
        @param null
        @result void
    */
    public List<String[]> downloadAndReadCSVFile() throws Exception{
        CSV_FILE_PATH = Helper.formatDateToCSVFileName(this.quotationDate)+".csv";
        try {
             Helper.downloadFileByUrl(CSV_FILE_PATH,QUOTATION_CSV_URL_PATH+CSV_FILE_PATH);
         } catch (IOException ex) {
             Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
         }
        return Helper.readCsvFile(CSV_FILE_PATH);
    }

    /**
        Get the last working day by string of date 
        @param String of the Quotation date on format dd/MM/yyyy
    **/
    private void getLastWorkingDayQuotation(String quotationDate) throws Exception {
        try {
            this.quotationDate = Helper.convertStringToDate(quotationDate);
        } catch (ParseException ex) {
            throw new Exception("Invali date format");
        }
        Calendar calendarQuotationDate =  Calendar.getInstance();
        calendarQuotationDate.setTime(this.quotationDate);
        switch(calendarQuotationDate.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SATURDAY:
                calendarQuotationDate.add(Calendar.DAY_OF_MONTH, -1);
                break;
            case Calendar.SUNDAY:
                calendarQuotationDate.add(Calendar.DAY_OF_MONTH, -2);
                break;
        }
        this.quotationDate = calendarQuotationDate.getTime();
    }
}
