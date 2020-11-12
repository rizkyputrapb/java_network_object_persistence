/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network_object_persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rizky
 */
public class Praktikum2 {
    public static void main(String[] args) {
         MataKuliah mk = new MataKuliah("001", "Pemrograman Jaringan", (byte) 3);
         try {
             System.out.println("" + mk);
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mk.obj"));
             oos.writeObject(mk);
             oos.flush();
             oos.close();
             mk.setSks((byte) 2);

             ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mk.obj"));
             try {
                 MataKuliah mk1 = (MataKuliah) ois.readObject();
                 System.out.println("" + mk1);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(Praktikum2.class.getName()).log(Level.SEVERE, null, ex);
             }
         } catch (FileNotFoundException ex) {
             Logger.getLogger(Praktikum2.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Praktikum2.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    
}
