/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testhashing;

import java.io.*;
import java.io.BufferedReader;
import java.util.Scanner;


/**
 *
 * @author cwillard
 */
public class TestHashing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
                    
            //Opt how big the hash table is
            System.out.println("OPEN HASH TABLE:");
            Scanner keyboard = new Scanner(System.in);
            System.out.println("How big would you like the hash table?");
            int n = keyboard.nextInt();
            System.out.println();
            
            
            File file = new File("/Users/cwillard/Desktop/CompSci230Project/Test1.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
           
            OpenHashing OH1 = new OpenHashing(n);
            
            String str = reader.readLine();
            
            while(str != null){
                OH1.insert(str);
                str = reader.readLine();
            }
            reader.close();
            
            double j = 200;
            double average = 0;
            double averageT = 0;
            double averageTT = 0;
            
        for(int k = 0; k<200;k++){
            File file3 = new File("/Users/cwillard/Desktop/CompSci230Project/Test2.txt");
            BufferedReader reader3 = new BufferedReader(new FileReader(file3));
            
            //Tests 200 Successful searches (Test2.txt only contains words found in Test1.txt)
            for(int i = 0; i < j; i++){
                long start = System.nanoTime();
                OH1.search(reader3.readLine());
                long elapsedTime = System.nanoTime() - start;
                averageT = averageT + elapsedTime;
                average = average + OH1.getProbes();
            }
            averageT = averageT/j;
            averageTT = averageT+averageTT;
        }
            
            System.out.println("Average Time: " + (averageTT)/200);
            System.out.println("Average probes S = " + average/j);
            System.out.println("Successful Search Probes: " + (1 + OH1.loadFactor()/2));
            System.out.println();
            
            
            //long start2 = System.nanoTime();
            
            File file4 = new File("/Users/cwillard/Desktop/CompSci230Project/Test4.txt");
            BufferedReader reader4 = new BufferedReader(new FileReader(file4));
            
            //Tests 2
            double q = 200;
            double average1 = 0;
            for(int i = 0; i<q;i++){
                OH1.search(reader4.readLine());
                average1 = average1 + OH1.getProbes();
            }
            System.out.println("Average probes U = " + average1/q);
            


            //long elapsedTime2 = System.nanoTime() - start2;
            //double eTime2 = elapsedTime2 / 1000000000.0;
            //System.out.println("Time Elapsed: "+ eTime2);
            System.out.println("Unsuccessful Search Probes: " + OH1.loadFactor());
            System.out.println();

            
            //Opt how big the hash table is
            
            //USE PRIME NUMBERS FOR ARRAY SIZE!!!!
            System.out.println("CLOSED HASH TABLE: ");
            Scanner keyboard1 = new Scanner(System.in);
            System.out.println("How big would you like the hash table?");
            int m = keyboard1.nextInt();
            System.out.println();
            //TEST METHODS
            ClosedHashing C1 = new ClosedHashing(m);
            
            File file2 = new File("/Users/cwillard/Desktop/CompSci230Project/Test1.txt");
            BufferedReader reader1 = new BufferedReader(new FileReader(file2));
           
            
            String str1 = reader1.readLine();
            while(str1 != null){
                C1.insert(str1);
                str1 = reader1.readLine();
            }
            reader1.close();
            
            File file5 = new File("/Users/cwillard/Desktop/CompSci230Project/Test2.txt");
            BufferedReader reader5 = new BufferedReader(new FileReader(file5));
            
            //Tests 200 Successful searches (Test2.txt only contains words found in Test1.txt)
            double p = 200;
            double average2 = 0;
            for(int i = 0; i < p; i++){
                C1.search(reader5.readLine());
                average2 = average2 + C1.getProbes();
            }
            System.out.println("Average probes S = " + average2/p);
            System.out.println("Successful Search Probes: " + C1.loadFactor());
            System.out.println();
            
            
            double y = 200;
            double averageT1 = 0;
            double average3 = 0;
            double averageT2 = 0;
            for(int k = 0; k<200;k++){
            
            //Tests 200 Unsuccessful searches (Test4.txt is random gibberish so Test1.txt does not contain anything from Test4.txt)
            File file6 = new File("/Users/cwillard/Desktop/CompSci230Project/Test4.txt");
            BufferedReader reader6 = new BufferedReader(new FileReader(file6));
            
            for(int i = 0; i < y; i++){
                long start = System.nanoTime();
                C1.search(reader6.readLine());
                long elapsedTime = System.nanoTime() - start;
                averageT1 = averageT1+elapsedTime;
                average3 = average3 + C1.getProbes();
            }
            averageT1 = averageT1/y;
            averageT2 = averageT1 + averageT2;
            }
            System.out.println("Average Time: " + averageT2/200);
            System.out.println("Average probes U = " + average3/y/200);
            System.out.println("Unsuccessful Search Load Factor: " + C1.loadFactor());
            System.out.println();
            
            
            //long start1 = System.nanoTime();
            //long elapsedTime1 = System.nanoTime() - start1;
            //double eTime1 = elapsedTime/1000000000.0;
            
            //System.out.println("Successfull Search Time: " + eTime1);
            
            
            //long start3 = System.nanoTime();
            //long elapsedTime3 = System.nanoTime() - start3;
            //double eTime3 = elapsedTime3 / 1000000000.0;
            //System.out.println("Unsuccessful Search Time: "+ eTime3);

            
            

        
        
        } catch (FullHashException ex) {
            System.err.println(ex);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
    }
}
