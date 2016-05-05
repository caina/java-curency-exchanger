/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi;

import com.caina.cwi.controller.Currency;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caina
 */
public class Main {
    
    private Currency currency;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Currency c = new Currency();
         try {
            c.currencyQuotation("USD", "EUR", 100.00, "20/11/2014");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            c.currencyQuotation("USD", "EUR", 100.00, "04/05/2016");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            c.currencyQuotation("XCD", "PLN", 75.00, "30/04/2016");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        

    }
    
    
    
    
}
