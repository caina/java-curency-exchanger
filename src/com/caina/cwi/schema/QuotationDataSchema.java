/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.schema;

import com.caina.cwi.helper.Helper;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author web
 */
public class QuotationDataSchema {

    private Date       quotationDate;
    private int        codMoeda;
    private String     type;
    private String     moeda;
    private BigDecimal taxaCompa;
    private BigDecimal taxaVenda;
    private BigDecimal prioridadeCompra;
    private BigDecimal paridadeVenda;
    
    public void populateCSVListData(String[] csvLineData) throws ParseException {
        
        this.quotationDate               = Helper.convertStringToDate(csvLineData[0]);
        this.codMoeda           = Integer.parseInt(csvLineData[1]);
        this.type               = csvLineData[2];
        this.moeda              = csvLineData[3];
        this.taxaCompa          = Helper.stringToBigDecimal(csvLineData[4]);
        this.taxaVenda          = Helper.stringToBigDecimal(csvLineData[5]);
        this.prioridadeCompra   = Helper.stringToBigDecimal(csvLineData[6]);
        this.paridadeVenda      = Helper.stringToBigDecimal(csvLineData[7]);
        
        System.out.println("com.caina.cwi.schema.QuotationDataSchema.populateCSVListData()");
    }



 
    
}
