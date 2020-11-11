/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zaldarriaga.crudapp;

/**
 *
 * @author jezre
 */
public class ZaldarriagaCrudApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        View_login vl = new View_login ();
        View_bank vb = new View_bank ();
        new Controller(vl, vb);
        vl.setTitle("Mini-Bank");
        vl.setVisible(true);
    }
    
}
