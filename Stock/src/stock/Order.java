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
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class Order {
 private String _stockTicker;
private Integer  _amount;
 private String  _time;
 private Double _cost;
 private Double _costOrder;
 public Order() {
     _stockTicker = null;
     _amount = null;
     _time = null;
 }

 
 public Order(String ticker, Integer amount, String time, Double cost) {
     _stockTicker = ticker;
     _amount = amount;
     _time = time;
     _cost = cost;
     _costOrder = _cost*_amount;
     
 }
 public void setAmount(int i){
     _amount = i;
 }
 public void setTime(String s){
     _time = s;
 }
 public String getStockTicker() {
     return _stockTicker;
 }
 public int getAmount () {
     return _amount;
 }
 public Double getStockCost(){
     return _cost;
 }
 public Double getCostOrder(){
     return _costOrder;
 }
 public void setCostOrder(Double d) {
     _costOrder = d;
 }
 public void setCost(double d){
     _cost = d;
 }
 public String getTime () {
     return _time;
 }
 public String toString() {
     return _stockTicker +" " +  _amount.toString() + " " + _time + " " + this.getCostOrder() + " ";
 }
    
}
