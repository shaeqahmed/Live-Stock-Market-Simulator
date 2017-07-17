/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import java.io.Writer;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author Peter
 */
public class DisplayQ {
    
    
    
     public static void Display(Queue<Order> orderQueues, String s) {
        JEditorPane jep = new JEditorPane();
	jep.setEditable(false);   
        jep.setContentType("text/html");
        jep.setText("<html>" + makeTable(orderQueues) + "</html>"); 
	 JScrollPane scrollPane = new JScrollPane(jep);     
	JFrame f = new JFrame(s);
         Writer writer = null;
          f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          f.getContentPane().add(scrollPane);
	   f.setSize(800, 742);
            f.show();
		 

    }
 public static void DisplayS(Queue<String> searches, String s) {
        JEditorPane jep = new JEditorPane();
	jep.setEditable(false);   
        jep.setContentType("text/html");
        jep.setText("<html>" + makeTableS(searches) + "</html>"); 
	 JScrollPane scrollPane = new JScrollPane(jep);     
	JFrame f = new JFrame(s);
         Writer writer = null;
          f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          f.getContentPane().add(scrollPane);
	   f.setSize(800, 742);
            f.show();
		 

    }
   public static String makeTableS(Queue<String> searches){
     Queue<String> searchQueue = new LinkedList(searches);
     String html= "<table><tr><td>STOCK</td></tr>";
     while(!searchQueue.isEmpty()){
         String curr = searchQueue.poll();
         html+="<tr><td>"+ curr +"</td></tr>";
   }
    return html+"</table>";

   }
 
    public static String makeTable (Queue<Order> orders){
     Queue<Order> orderQueue = new LinkedList(orders);
     String html= "<table><tr><td>STOCK</td><td>AMOUNT</td><td>COST</td><td>Time</td></tr>";
     while(!orderQueue.isEmpty()){
         Order curr = orderQueue.poll();
         html+="<tr><td>"+ curr.getStockTicker() +"</td><td>" + curr.getAmount()+ "</td><td>" + curr.getStockCost() + ""
                 + "</td><td>" + curr.getTime() + "</td></tr>";
     }
     return html+"</table>";
   }
    
}
