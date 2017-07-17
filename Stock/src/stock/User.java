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
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class User {
    String _name;
    String _password;
    Stack<Order> _orders;
    ArrayList<Order>  _portfolio;
    Double _spending;
    Double _margin;
    Queue<Order> _orderQueue;
    Queue<String> _searches;

 public ArrayList<Order> getPortfolio() {
     return _portfolio;
 }
 public String getName() {
     return _name;
 }
 public Stack getOrders() {
     return _orders;
 }
public User() {
    _name = null;
    _password = null;
    _spending = 100000.0;
    _portfolio = new ArrayList<Order>();
    _orderQueue = new LinkedList<Order>();
    _orders = new Stack<Order>();
    _searches = new LinkedList<String>();
}
public User(String n) {
    _name = n;
    _spending = 100000.0;
    _margin = 0.0;
     _portfolio = new ArrayList<Order>();
    _orderQueue = new LinkedList<Order>();
    _orders = new Stack<Order>();
    _searches = new LinkedList<String>();
    updateQueue();
    updateOrders();
    updateSearches();
    UpdatePortfolio();
    UpdateQueueToOrder();
    updateOrders();
    UpdatePortfolio();
    UpdateSpending();
}
public Double getSpending() {
    return _spending;
}
public void setName(String s) {
    _name = s;
}
public void UpdatePortfolio(){
         Stack<Order> orderStack = (Stack<Order>)_orders.clone();
         _portfolio = null;
         _portfolio = new ArrayList<Order>();
         while(!orderStack.isEmpty()) {
             Order currOrder = orderStack.pop();
             if(_portfolio.isEmpty()){
                 _portfolio.add(currOrder);
             }
             int pos  = inPortfolio(currOrder);
             if(pos == -1) {
                 _portfolio.add(currOrder);
             }
             else {
                 _portfolio.get(pos).setAmount(currOrder.getAmount() + _portfolio.get(pos).getAmount());
                 _portfolio.get(pos).setTime(currOrder.getTime());
                 _portfolio.get(pos).setCostOrder(_portfolio.get(pos).getCostOrder() + currOrder.getCostOrder());
                 _portfolio.get(pos).setCost(_portfolio.get(pos).getCostOrder()/_portfolio.get(pos).getAmount());
             }     
                
         }
}
public void UpdateQueueToOrder(){
  for(int i = 0; i < _orderQueue.size(); i++){
    if(isTradingTime() || wasTradingTime(_orderQueue.peek().getTime()))
    {
       Order currOrder = _orderQueue.peek();
       if(_spending < Math.abs(currOrder.getCostOrder())) 
           break;
       else {
            Writer writer = null;
    

      try {
                        writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(_name + "//orders.txt", true), "utf-8"));
                        writer.append("\r\n" + currOrder.toString());
           } catch (IOException ex) {
  // report
       } finally {
     try {writer.close();} catch (Exception ex) {/*ignore*/}
}
    }
       removeQueue(_orderQueue.poll().toString());
      }
       
      }
    }
  
public void removeQueue(String lineToRemove) {
    try {
 
      File inFile = new File(_name + "//queue.txt");
      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
      BufferedReader br = new BufferedReader(new FileReader(_name + "//queue.txt"));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
      String line = null;
      while ((line = br.readLine()) != null) {
        if (!line.trim().equals(lineToRemove)) {
          pw.println(line);
          pw.flush();
        }
      }
      pw.close();
      br.close();
      
    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }
public void UpdateSpending(){
    _spending = 100000.0;
    for(int i = 0; i < _portfolio.size(); i++)
    if (_portfolio.get(i).getAmount() > 0)
        _spending = _spending - _portfolio.get(i).getCostOrder();
    else {
        Double currPrice = getCurrentPrice(_portfolio.get(i).getStockTicker());
        _margin = -1*_portfolio.get(i).getAmount()*currPrice;
        _spending = _spending - _portfolio.get(i).getCostOrder() - _margin;

    }
}

public Double getCurrentPrice(String s) {
String TICKER = "http://finance.google.com/finance/info?client=ig&q=" + s;
String price = null;
String objString = null;
        try {
        objString = "{" + readJsonFromUrl(TICKER) + "}"; 
        objString = objString.replaceFirst(".$","");
        JSONObject obj = new JSONObject(objString);
        price = obj.getString("l");
        }
        catch (Exception e) {
            System.out.println(objString);
        }
    return Double.parseDouble(price); 
}

public static String readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      StringBuilder sb = new StringBuilder();
      int cp;
      while ((cp = rd.read()) != 123){
      }
      while ((cp = rd.read()) != -1) {
        sb.append((char) cp);
        if (cp == 125){
            break;
        }
      }
      String jsonText = sb.toString();
      return jsonText;
    } finally {
      is.close();
    }
  }

public int inPortfolio(Order o){
    for(int i = 0; i < _portfolio.size(); i++)
        if (o.getStockTicker().equals(_portfolio.get(i).getStockTicker()))
            return i;
    return -1;
}
public void updateOrders() {
    try {
    Scanner scanner = new Scanner( new File(_name + "//orders.txt"), "UTF-8" ); //Update this 
    scanner.nextLine();
    _orders = null;
    _orders = new Stack<Order>();
    while(scanner.hasNextLine()) {
        String currOrder = scanner.nextLine();
        String s = currOrder.substring(0,currOrder.indexOf(" "));
        currOrder =  currOrder.substring(currOrder.indexOf(" ") + 1);
        Integer a = Integer.parseInt(currOrder.substring(0,currOrder.indexOf(" ")));
        currOrder =  currOrder.substring(currOrder.indexOf(" ") + 1);
        String d = currOrder.substring(0, 28);
        currOrder = currOrder.substring(27);
        Double c = Double.parseDouble(currOrder.substring(0,currOrder.indexOf(" ")));
        _orders.add(new Order(s,a,d,c));
    }
    
    scanner.close();    
            } 
    catch (IOException e) {
       return;
    }
 
    }
public void updateQueue() {
    try {
    _orderQueue = null;
     _orderQueue = new LinkedList<Order>();
    Scanner scanner = new Scanner( new File(_name + "//queue.txt"), "UTF-8" ); //Update this 
    scanner.nextLine();
    while(scanner.hasNextLine()) {
        String currOrder = scanner.nextLine();
        String s = currOrder.substring(0,currOrder.indexOf(" "));
        currOrder =  currOrder.substring(currOrder.indexOf(" ") + 1);
        Integer a = Integer.parseInt(currOrder.substring(0,currOrder.indexOf(" ")));
        currOrder =  currOrder.substring(currOrder.indexOf(" ") + 1);
        String d = currOrder.substring(0, 26);
        currOrder = currOrder.substring(27);
        Double c = Double.parseDouble(currOrder.substring(0,currOrder.indexOf(" ")));
        _orderQueue.add(new Order(s,a,d,c));
    }
    
    scanner.close();    
            } 
    catch (IOException e) {
       return;
    }
}

public void updateSearches() {
    try {
    Scanner scanner = new Scanner( new File(_name + "//searches.txt"), "UTF-8" ); //Update this 
    while(scanner.hasNext()) {
        _searches.add(scanner.next());
    }
    
    scanner.close();    
            } 
    catch (IOException e) {
       return;
    }
 
    
}
public void ExecuteBuy(String s, int stockAmt) {
    Stock curr = new Stock();
   curr.PullData(s, "d", "1");
   curr.PullInfo();
    if (curr.getCurrentPrice()* stockAmt > _spending)
    {
      new Sorry(); //DISPLAY SORRY NOT ENOUGH MONEY
      return;
    }
    if(isTradingTime()) {
     Order newOrd = new Order(s,stockAmt,dateToString(new Date()), curr.getCurrentPrice());
     _orders.add(newOrd);
     
     Writer writer = null;    

      try {
                        writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(_name + "//orders.txt", true), "utf-8"));
                        writer.append("\r\n" + newOrd.toString());
           } catch (IOException ex) {
  // report
       } finally {
     try {writer.close();} catch (Exception ex) {/*ignore*/}
}
        //Exectute Trade show Display Screen Saying order went through
        //Update Portfolio List
    }
    else {
     Order newOrd = new Order(s,stockAmt,dateToString(new Date()),curr.getCurrentPrice());
     _orderQueue.add(newOrd);
     Writer writer = null;
     try {
                        writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(_name + "//queue.txt", true), "utf-8"));
                        writer.append("\r\n" + newOrd.toString());
                } catch (IOException ex) {
                // report
                } finally {
                    try {writer.close();} catch (Exception ex) {/*ignore*/}
                }

}
}
public void ExecuteShort(String s, int stockAmt){
     Stock curr = new Stock();
   curr.PullData(s, "d", "1");
   curr.PullInfo();
   if (curr.getCurrentPrice()* stockAmt > _spending)
    {
        new Sorry();
    }
   if(isTradingTime()) {
     Order newOrd = new Order(s,-1*stockAmt,dateToString(new Date()),curr.getCurrentPrice());
     _orders.add(newOrd);
     Writer writer = null;
    

      try {
                        writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(_name + "//orders.txt", true), "utf-8"));
                        writer.append("\r\n" + newOrd.toString());
           } catch (IOException ex) {
  // report
       } finally {
     try {writer.close();} catch (Exception ex) {/*ignore*/}
}
        //Exectute Trade show Display Screen Saying order went through
        //Update Portfolio List
    }
   else{
     Order newOrd = new Order(s,-1*stockAmt,dateToString(new Date()),curr.getCurrentPrice());
     _orderQueue.add(newOrd);
     Writer writer = null;
     try {
                        writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(_name + "//queue.txt", true), "utf-8"));
                        writer.append("\r\n" + newOrd.toString());
                } catch (IOException ex) {
                // report
                } finally {
                    try {writer.close();} catch (Exception ex) {/*ignore*/}
                }

    // Put order in order queue 
    //Display Order in Queue
}
}

public boolean isTradingTime() {
Calendar myDate = Calendar.getInstance(); 
int dow = myDate.get (Calendar.DAY_OF_WEEK);
boolean isWeekday = ((dow >= Calendar.MONDAY) && (dow <= Calendar.FRIDAY));
int hour = myDate.get(Calendar.HOUR_OF_DAY); // Get hour in 24 hour format
int minute = myDate.get(Calendar.MINUTE);
Date date = parseDate(hour + ":" + minute);
Date dateCompareOne = parseDate("09:00");
 Date dateCompareTwo = parseDate("16:00");

 if (dateCompareOne.before(date) && dateCompareTwo.after(date) && isWeekday) {
     return true;
 }
 return false;
}
public boolean wasTradingTime(String s){
    Calendar cal = Calendar.getInstance();
  DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
  try {
   cal.setTime(dateFormat.parse(s));
  }
  catch (Exception e){
      return false;
  }
   Calendar myDate = Calendar.getInstance();
   int dow = myDate.get (Calendar.DAY_OF_WEEK);
   boolean isWeekday = ((dow >= Calendar.MONDAY) && (dow <= Calendar.FRIDAY));
   if(!isWeekday) return false;
   return (myDate.getTimeInMillis() - cal.getTimeInMillis()) > 8640000;
}

public void setSpending(Double d) {
    _spending = d;
}
public void setMargin(Double d) {
    _margin = d;
}
public Double getMargin() {
    return _margin;
}
public void setPortfolio(ArrayList<Order> p){
     _portfolio = p;
}
 private Date parseDate(String date) {

    final String inputFormat = "HH:mm";
    SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
    try {
         return inputParser.parse(date);
    } catch (java.text.ParseException e) {
         return new Date(0);
    }
 }
 public Queue<Order> getOrderQueue() {
     return _orderQueue;
 }
  public Queue<String> getSearches() {
     return _searches;
 }
 public String dateToString(Date d) {
  DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
    return dateFormat.format(d);
 }

}
