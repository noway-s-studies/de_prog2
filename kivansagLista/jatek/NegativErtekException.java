/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jatek;

/**
 *
 * @author noway0032
 */
public class NegativErtekException extends Exception {

    public NegativErtekException(String negatív_érték) {
        super(negatív_érték);
    }
    
}