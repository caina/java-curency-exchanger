package com.caina.cwi;

import com.caina.cwi.controller.Currency;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caina
 */
public class Main {
    
    
    public static void main(String[] args) {
        Currency currencyExchanger = new Currency();
      
        try {
            System.out.println(currencyExchanger.currencyQuotation("USD", "EUR", 100, "20/11/2014"));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(currencyExchanger.currencyQuotation("USD", "EUR", 50, "07/04/2016"));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(currencyExchanger.currencyQuotation("USD", "EUR", 50, "20/11/2019"));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(currencyExchanger.currencyQuotation("", "EUR", 50, "20/11/2015"));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println(currencyExchanger.currencyQuotation("USD", "EUR", -50, "20/11/2015"));
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
 
}
