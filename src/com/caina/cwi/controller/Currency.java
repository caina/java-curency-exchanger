/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.controller;


import com.caina.cwi.helper.Helper;
import com.caina.cwi.model.QuotationModel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
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
    
    //20160504.csv
    private String QUOTATION_CSV_URL_PATH = "http://www4.bcb.gov.br/Download/fechamento/";
    private String CSV_FILE_PATH;
    
    private QuotationModel quotaionModel;
    private Date quotationDate;
    
    /**
     * TODO 
     Handle the data
     * @param currencyFrom
     * @param currencyTo
     * @param exchangeValue
     * @param quotationDate
     * @return 
     * @throws java.text.ParseException 
     **/
    public BigDecimal currencyQuotation(String currencyFrom, String currencyTo, Number exchangeValue, String quotationDate) throws ParseException{
        this.quotaionModel = new QuotationModel();
        this.getLastWorkingDayQuotation(quotationDate);
        this.downloadAndReadCSVFile();
        this.quotaionModel.setQuotationCSVData(this.downloadAndReadCSVFile());
        
         BigDecimal exchange = this.quotaionModel.convertCurrency(currencyFrom, currencyTo, new BigDecimal(exchangeValue.toString()));
         System.out.println(exchange.toString());
         return null;
    }
    
    
    /*
        Download the csv quotation file, instantiate the Quotation model and
        populate the data with the list readed from the file.
        @param null
        @result void
    */
    public List<String[]> downloadAndReadCSVFile(){
        CSV_FILE_PATH = Helper.formatDateToCSVFileName(this.quotationDate)+".csv";
        try {
             Helper.downloadFileByUrl(CSV_FILE_PATH,QUOTATION_CSV_URL_PATH+CSV_FILE_PATH);
         } catch (IOException ex) {
             Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
         }
        return Helper.readCsvFile(CSV_FILE_PATH);
    }

    /**
    Resolve the last working day by string of date 
    **/
    private void getLastWorkingDayQuotation(String quotationDate) throws ParseException {
        this.quotationDate = Helper.convertStringToDate(quotationDate);
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
