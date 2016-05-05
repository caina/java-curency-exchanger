/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.model;

import com.caina.cwi.schema.QuotationDataSchema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author web
 */
public class QuotationModel {
    
    private List<QuotationDataSchema> quotationDataSquemaList;
    private QuotationDataSchema quotationDataSchema;
    /*
    
    @param List of strings 
    @return void
    **/
    public void setQuotationCSVData(List<String[]> readedCsvFile) {
        quotationDataSquemaList = new ArrayList();
        readedCsvFile.forEach((csvLineData) -> {
            quotationDataSchema = new QuotationDataSchema();
            quotationDataSchema.populateCSVListData(csvLineData);
            
        });
    }
    
}