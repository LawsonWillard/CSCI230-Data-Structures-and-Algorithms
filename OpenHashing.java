/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testhashing;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author cwillard
 */
public class OpenHashing<AnyType extends Comparable> {

    private int probes;
    private int size; //How many items are in the hash table (Excludes null values)
    private final int TABLE_SIZE; //Full size of the hash table
    private SinglyLinkedList<AnyType>[] S1; //hash table variable

    
    
    /**
     * Finds the load factor of the hash table (number of used cells over the umber of total cells)
     * @return Load factor of hash table
     */
    
    public double loadFactor(){
        double n = size;
        double k = TABLE_SIZE;
        return n/k;
    }
    
    
    /**
     * Creates a chaining hash table out of singly linked lists of parameter size 
     * @param TableSize size of the desired hash table
     */
    
    public OpenHashing(int TableSize) {
        size = 0;
        TABLE_SIZE = TableSize;
        this.S1 = new SinglyLinkedList[TableSize];
        for (int i = 0; i < TableSize; i++) {
            S1[i] = new SinglyLinkedList();
        }
    }
    
    /**
     * Inserts an item into a hash table
     * @param t Item that is being inserted
     */

    public void insert(AnyType t){
        int index = hashFunction(t);
        //System.out.println(index);
        SinglyLinkedList<AnyType> listAtIndex = S1[index];
        
        for(int i = 0; i < listAtIndex.size(); i++){
            if(listAtIndex.get(i).compareTo(t) == 0){
                return;
            }
        }
        listAtIndex.add(t);
        size++;
    }
    
    /*public void display(){
        for(int i = 0; i < TABLE_SIZE;i++){
            SinglyLinkedList<AnyType> listAtIndex = S1[i];
            for(int k = 0; k<listAtIndex.size();k++){
                System.out.println(listAtIndex.get(k));
            }
        }
    }
    */
    
    /**
     * Deletes item t from the hash table
     * @param t item that you are deleting from the hash
     * @return t the item that was deleted out of the hash
     * @throws NotInHashException if item t is not in hash table throw NotInHashException
     */

    public AnyType delete(AnyType t) throws NotInHashException {
        int index = hashFunction(t);
        SinglyLinkedList<AnyType> listAtIndex = S1[index];
        for (int i = 0; i < listAtIndex.size(); i++) {
            if (listAtIndex.get(i).compareTo(t) == 0) {
                size--;
                return listAtIndex.remove(i);
            }
        }
        throw new NotInHashException();
    }
    
    /**
     * 
     * Searches for t in the Hash Table
     * @param t item you looking for in Hash
     * @return Item passed in as parameter if found else returns null
     */
    
    public AnyType search(AnyType t) {
        probes = 0;
        int index = hashFunction(t);
        SinglyLinkedList<AnyType> listAtIndex = S1[index];
        for (int i = 0; i < listAtIndex.size(); i++) {
            probes++;
            if (listAtIndex.get(i).compareTo(t) == 0) {
                return listAtIndex.get(i);
            }
        }
        return null;
    }
    
    public int getProbes(){
        return probes;
    }

    /**
     * Creates a hash code for t
     * @param t Item that you are hashing
     * @return the Hash code of t
     */
    
    public int hashFunction(AnyType t) {
        return Math.abs(t.hashCode()) % TABLE_SIZE;
    }
    
    /**
     * Creates a hash code for t and multiplies it by a prime number to try and keep it more unique
     * @param t Item that you are hashing
     * @return the hash code of t
     */
    
    public int hashFunction1(AnyType t){
        return (Math.abs(t.hashCode()) % TABLE_SIZE) ;
    }
}
