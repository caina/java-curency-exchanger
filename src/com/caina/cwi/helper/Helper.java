/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.caina.cwi.helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 *
 * @author caina
 */
public class Helper {
   
    
   /*
    Method to download files from server, very straightforward
    @param: name of the file, this indicate as well tha path where it will be downloaded
    @output: null
    */ 
   public static void downloadFileByUrl(final String filename, final String urlString) throws MalformedURLException, IOException {
        
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try {
            in = new BufferedInputStream(new URL(urlString).openStream());
            fout = new FileOutputStream(filename);

            final byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (fout != null) {
                fout.close();
            }
        }
    }
   
   /*
   Read an given CSV file and parse into a List 
   @param: CSV file to be readed
   @return: List with the CSV data
   */
   public static List<String[]> readCsvFile(final String pathToCSV) {
       
    List<String[]> resultDataImport = new ArrayList<String[]>();
    String csvFile = System.getProperty("user.dir")+"/"+pathToCSV;
    BufferedReader br = null;
    String cvsSplitBy = ";";
    String line;
        
    try {
        br = new BufferedReader(new FileReader(csvFile));
        while ((line = br.readLine()) != null) {
            String[] csvLineData = line.split(cvsSplitBy);
            resultDataImport.add(csvLineData);
        }
    } catch (FileNotFoundException e) {
            e.printStackTrace();
    } catch (IOException e) {
            e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return resultDataImport;
  }

    /**
     * Convert the date in format ddMMyyyy to Date
     * @param String 
     * @return Date
     * @throws ParseException
     */
    public static Date convertStringToDate(String quotationDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(quotationDate);
    }
    
    /**
     * Format the givend date to the same format that the website's request
     * @param Date
     * @return Formated String in yyyyMMdd  
     **/
    public static String formatDateToCSVFileName(Date quotationDate) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendarQuestionDate =  Calendar.getInstance();
        calendarQuestionDate.setTime(quotationDate);
        return df.format(calendarQuestionDate.getTime());
    }

    public static BigDecimal stringToBigDecimal(String bigDecimalString) {
        return new BigDecimal(bigDecimalString.replace(",", "."));
    }
}
