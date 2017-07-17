/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

/**
 *
 * @author Peter
 */

import java.net.URL;




/**
 *
 * @author Peter
 */
import javax.swing.JEditorPane; 
import javax.swing.JFrame; 
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class StockDisplay {

    /**
     * @param args the command line arguments
     */
    public static void test(String s, String _name) {
        JEditorPane jep = new JEditorPane();
	jep.setEditable(false);   
        Stock currStock = new Stock();
        try {
        currStock.PullData(s, "m", "1");
        currStock.PullInfo();
        jep.setContentType("text/html");
		   jep.setText("<html><head> <title>STOCK SCREENER </title>"
                           + "</head></head>"
                           + "<body>"
                           + "<div class=\"container\" style=’text-align: center;’>"
                           + "<img src= 'http://162.243.251.180/graph.png'></img>"
                           + "</div> "
                           + "<table bgcolor=\"#DFE2DB\" border=\"1\" style=\"width:100%\">" + currStock.getInfo() +
                           "</html>");
        } catch (Exception e) {
		   jep.setContentType("text/html");
		   jep.setText("<html>Could not load Stock </html>");
		 } 
		 
		 JScrollPane scrollPane = new JScrollPane(jep);     
		 JFrame f = new JFrame("Stock Screener");
                 Writer writer = null;

                try {
                        writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(_name + "//searches.txt", true), "utf-8"));
                        writer.append("\r\n" + currStock.getName());
                } catch (IOException ex) {
  // report
                } finally {
                    try {writer.close();} catch (Exception ex) {/*ignore*/}
}
		 f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		 f.getContentPane().add(scrollPane);
		 f.setSize(800, 742);
		 f.show();
		 

    }
    
    
}
