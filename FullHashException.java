/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testhashing;

/**
 *
 * @author cwillard
 */
public class FullHashException extends Exception{
    public FullHashException(){
        super("Hash is full");
    }
}
