/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testhashing;

import java.util.Scanner;

/**
 *
 * @author cwillard
 * @param <AnyType> Any object that extends comparable
 */
public class ClosedHashing<AnyType extends Comparable> {
    private int probes;
    private int size; //How many items are in the hash table (Excludes null values)
    private final int TABLE_SIZE;//Full size of the hash table
    private static Object[] array;//array var
    
    
    
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
     * Creates a closed addressing hash table of parameter size 
     * @param TableSize size of the desired hash table
     */
    
    public ClosedHashing(int TableSize) {
        size = 0;
        TABLE_SIZE = TableSize;
        this.array = new Object[TableSize];
    }
    
    /**
     * Inserts an item into a hash table
     * @param t Item that is being inserted
     * @throws FullHashException if the hash table is full throw FullHashException
     */
    
    public void insert(AnyType t) throws FullHashException{
        if(size == TABLE_SIZE){
            throw new FullHashException();
        }
        int index = hashFunction(t);
        int k = 1;
        for(int i = 0; i<TABLE_SIZE;i++){
            if(index > TABLE_SIZE){
                while(TABLE_SIZE < index){
                    index = index - TABLE_SIZE;
                }
            }
            if(index == TABLE_SIZE){
                index = 0;
            }
            if(t.equals(array[index])){
                return;
            }
            if(array[index] == null || array[index].equals(-1)){ //CHANGE -1 INTO DELTED OBJECT!!!!
                array[index] = t;
                size++;
                return;
            }
            else{
                index = hashFunction(t) + k * k;
                k++;
            }
        }
    }
    
    /**
     * Deletes item t from the hash table
     * @param t item that you are deleting from the hash
     * @return t the item that was deleted out of the hash
     * @throws NotInHashException if item t is not in hash table throw NotInHashException
     */
    
    public AnyType remove(AnyType t) throws NotInHashException{
        int index = hashFunction(t);
        int k = 1;
        for(int i = 0; i<TABLE_SIZE;i++){
            if(index > TABLE_SIZE){
                while(TABLE_SIZE < index){
                    index = index - TABLE_SIZE;
                }
            }
            if(index == TABLE_SIZE){
                index = 0;
            }
            if(array[index].equals(t)){ //CHANGE -1 INTO DELTED OBJECT!!!!
                array[index] = -1;
                size--;
                return t;
            }
            else{
                index = hashFunction(t) + k * k;
                k++;
            }
        }
        throw new NotInHashException();
    }
    
    
    public void display(){
        for(int i = 0; i<TABLE_SIZE;i++){
            System.out.println(array[i]);
        }
    }
    
    /**
     * 
     * Searches for t in the Hash Table
     * @param t item you looking for in Hash
     * @return Item passed in as parameter if found else returns null
     */
    
    public AnyType search(AnyType t){
        probes = 0;
        int index = hashFunction(t);
        int k = 1;
        for(int i = 0; i<TABLE_SIZE;i++){
            probes++;
            if(index == TABLE_SIZE){
                index = 0;
            }
            if(t.equals(array[index])){ 
                return t;
            }
            else if(array[index] == null){
                return null;
            }
            else{
                index = ((hashFunction(t) + k * k) % TABLE_SIZE);
                k++;
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
        return ((Math.abs(31 * t.hashCode() * t.hashCode())) % TABLE_SIZE);
    }
    
    /**
     * Creates a hash code for t and multiplies it by a prime number to try and keep it more unique
     * @param t Item that you are hashing
     * @return the hash code of t
     */
    
    public int hashFunction1(AnyType t){
        return ( 7 * Math.abs(t.hashCode()) % TABLE_SIZE) ;
    }
}
