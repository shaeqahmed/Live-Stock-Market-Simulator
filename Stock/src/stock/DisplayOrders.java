/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;
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
import java.lang.*;
import java.util.Stack;
import javax.swing.text.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author Peter
 */
public class DisplayOrders {
    public static void Display(Stack<Order> orderStack, String s) {
        System.out.println(orderStack);
        JEditorPane jep = new JEditorPane();
	jep.setEditable(false);   
        jep.setContentType("text/html");
        jep.setText("<html>" + makeTable(orderStack) + "</html>"); 
	 JScrollPane scrollPane = new JScrollPane(jep);     
	JFrame f = new JFrame(s);
         Writer writer = null;
          f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          f.getContentPane().add(scrollPane);
	   f.setSize(800, 742);
            f.show();
    }
public static void DisplayP(ArrayList<Order> port, String s) {
        JEditorPane jep = new JEditorPane();
	jep.setEditable(false);   
        jep.setContentType("text/html");
        jep.setText("<html>" + makeTableP(port) + "</html>"); 
	 JScrollPane scrollPane = new JScrollPane(jep);     
	JFrame f = new JFrame(s);
         Writer writer = null;
          f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          f.getContentPane().add(scrollPane);
	   f.setSize(800, 742);
            f.show();
    }
public static String makeTable (Stack<Order> orders){
     Stack<Order> orderStack = (Stack<Order>)orders.clone();
     String html= "<table><tr><td>STOCK</td><td>AMOUNT</td><td>COSTs</td><td>TIME</td></tr>";
     while(!orderStack.isEmpty()){
         Order curr = orderStack.pop();
         html+="<tr><td>"+ curr.getStockTicker() +"</td><td>" + curr.getAmount()+ "</td>"
                 + "<td>" + curr.getStockCost() + "</td><td>" + curr.getTime() + "</td></tr>";
     }
     return html+"</table>";
   }
public static String makeTableP(ArrayList<Order> orders){
     String html= "<table><tr><td>STOCK</td><td>AMOUNT</td><td>Cost</td><td>TIME</td></tr>";
     for(int i = 0; i < orders.size(); i++) {
         Order curr = orders.get(i);
         html+="<tr><td>"+ curr.getStockTicker() +"</td><td>" + curr.getAmount()+ "</td>"
                 + "<td>" + curr.getCostOrder() + "</td><td>" + curr.getTime() + "</td></tr>";
     }
     return html+"</table>";
   }    
}
