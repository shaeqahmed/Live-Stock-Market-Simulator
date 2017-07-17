
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
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;	
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.json.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class Stock {
    
    
ArrayList<String> prices;
ArrayList<String> dates;
String range;
Integer amt;
String _stockInfo;
String _profile;
String _stock; 
public Stock() {
	//prices = new ArrayList<String>();
	//dates = new ArrayList<String>();
        range = "";
        amt = 0;
        _stockInfo = null;
        _profile = null;
        _stock = "";
        
}
public Stock(String stock, String _range, String _amt) { 
    _stock = stock;
    range = _range;
    Integer temp = Integer.parseInt(_amt);
    PullData(stock,_range,_amt);
    amt = temp;
    PullInfo();
}	/*public static void main(String[] args) {
		StockQuote AAPL = new StockQuote();
		AAPL.PullData("AAPL", "y", "1");
		System.out.println(AAPL.getPrices());
		} */
//public ArrayList<String> getDates() {
//	return dates;
//}
public String getProfile() {
    return _profile;
}
public String getInfo() { 
    return _stockInfo;
}
public int getAmt() {
	return amt;
}
public String getRange() {
	return range;
}
//public ArrayList<Double> getPrices() {
//	ArrayList<String> temp = prices;
//	ArrayList<Double> intprices = new ArrayList<Double>();
//	while(!temp.isEmpty())
//	 intprices.add(Double.parseDouble(temp.remove(0)));
//	return intprices;
//}
public Double getCurrentPrice() {
String TICKER = "http://finance.google.com/finance/info?client=ig&q=" + _stock;
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


public String getName() {
    return _stock;
}
public void PullData(String stock, String _range, String _amt) {
        _stock = stock;
        range = _range;
        amt = Integer.parseInt(_amt);
	//Prices("http://chartapi.finance.yahoo.com/instrument/1.0/" + stock + "/chartdata;type=quote;range=" + _amt + _range + "/csv");
       
}
//public void Prices(String url) {
//	try {
//	URL finance = new URL(url);
//	URLConnection quotes = finance.openConnection();
//	Scanner newData = new Scanner(quotes.getInputStream());
//	if (newData.hasNext()){
//		for (int i = 0; i < 18; i++) {
//			newData.nextLine();
//			}
//	}
//	while (newData.hasNextLine()) {
//		String line = newData.nextLine();
//		ArrayList<String> oneline = 
//		new  ArrayList<String>(Arrays.asList(line.split(",")));
//		dates.add(oneline.get(0));
//		prices.add(oneline.get(1));
//	}
//	}
//	catch (IOException e) {
//		System.out.println(e);
//	}
//}
public  void PullInfo() {
        // TODO code application logic here
        String BASE_URL = "http://finviz.com/quote.ashx?t=";
        Document doc = new Document("test");
        try {
        doc = Jsoup.connect(BASE_URL + this.getName()).get();  
        }
        catch (Exception e) {
       
        }
       
 Elements profile = doc.select("td[class=fullview-profile]");
 Elements stockInfo = doc.select("table[class=snapshot-table2]"); 
 String title = profile.get(0).text();
 String temp = stockInfo.toString();
 temp = temp.substring(87);
 _profile = title;
_stockInfo = temp;


    }
    
}
