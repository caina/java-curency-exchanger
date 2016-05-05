/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.controller;


import com.caina.cwi.helper.Helper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author caina
 */
public class Currency {
    
    private static final String QUOTATION_CSV_URL_PATH = "http://www4.bcb.gov.br/Download/fechamento/20160504.csv";
    private static final String CSV_FILE_PATH = "downloadble/quotation.csv";
    
    
    public BigDecimal currencyQuotation(String currencyFrom, String currencyTo, Number exchangeValue, String quotationDate){
        downloadCurrencyTable();
        
        return null;
    }
    
    
    /*
        Check if file is in cache, if not, download it
        Download the CSV file;
    */
    public void downloadCurrencyTable(){
            
        
            //        check cache
            
            //download
        try {
             Helper.downloadFileByUrl(CSV_FILE_PATH,QUOTATION_CSV_URL_PATH);
         } catch (IOException ex) {
             Logger.getLogger(Currency.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        Helper.readCsvFile(CSV_FILE_PATH);
        
    }
    
    
      
    
    
    
    
}
