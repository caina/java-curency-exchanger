/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi;

import com.caina.cwi.controller.Currency;

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
        c.downloadCurrencyTable();
    }
    
    
    
    
}
