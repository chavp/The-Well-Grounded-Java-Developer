/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package introjava7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.login.Configuration;

/**
 *
 * @author MyParinya
 */
public class IntroJava7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        exceptionHandling();
    }
    
    //1.3.1 Strings in switch
    public static void printDay(String dayOfWeek) {
        switch (dayOfWeek) {
            case "Sunday": System.out.println("Dimanche"); break;
            case "Monday": System.out.println("Lundi"); break;
            case "Tuesday": System.out.println("Mardi"); break;
            case "Wednesday": System.out.println("Mercredi"); break;
            case "Thursday": System.out.println("Jeudi"); break;
            case "Friday": System.out.println("Vendredi"); break;
            case "Saturday": System.out.println("Samedi"); break;
            default: System.out.println("Error: '"+ dayOfWeek +"' is not a day of the week"); break;
        }
    }
    
    //1.3.2 Enhanced syntax for numeric literals
    public static void parseNumber(){
        String bit = "1100110";
        int x = Integer.parseInt(bit, 2);
        System.out.println(bit + " = " + x);
        
        //java 7
        int xBit = 0b1100110;
        System.out.println(bit + " = " + xBit);
        
        //UNDERSCORES IN NUMBERS
        double mysalary = 1_000_000;
        double mysalaryBit = 0b1111_0100_0010_0100_0000;
        
        System.out.println(mysalary + " = " + mysalaryBit);
    }
    
    //1.3.3 Improved exception handling
    public static void exceptionHandling(){
        try (TheJava act1 = new TheJava("N1"); 
                TheJava act2 = new TheJava("N2")) {
            act1.act();
            act2.act();
        }catch(final Exception e){
            
        }
    }
    
    //1.3.5 Diamond syntax
    public static void diamondSyntax(){
        Map<Integer, Map<String, String>> usersLists = new HashMap<>();
        
        HashMap<String, String> hm1 = new HashMap<>();
        HashMap<String, String> hm2 = new HashMap<>();
        Collection<HashMap<String, String>> coll = doSomething(hm1, hm2);
    }
    public static <T> Collection<T> doSomething(T... entries) {
        return null;
    }
}