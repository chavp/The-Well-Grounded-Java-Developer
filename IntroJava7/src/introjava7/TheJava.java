/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package introjava7;

/**
 *
 * @author MyParinya
 */
public class TheJava implements AutoCloseable {
    private String _n;
    public TheJava(String n){
        _n = n;
    }
    public void act(){
        System.out.println(_n + ".act");
    }
    @Override
    public void close() throws Exception {
        System.out.println(_n + ".close");
    }
    
}
