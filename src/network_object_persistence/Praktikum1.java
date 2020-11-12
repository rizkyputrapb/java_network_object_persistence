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
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rizky
 */
public class Praktikum1{
     private static void writeObject(Object o) throws Exception {
         try {
             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mhs.obj"));
             oos.writeObject(o);
             oos.flush();
             oos.close();
         } catch (FileNotFoundException ex) {
             throw ex;
         } catch (IOException ex) {
             throw ex;
         }
     }

     private static Object readObject() throws Exception {
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mhs.obj"));
         try {
             Object readObject = ois.readObject();
             ois.close();
             return readObject;
         } catch (ClassNotFoundException ex) {
             throw ex;
         }
     }

     public static void main(String[] args) {
         Mahasiswa m = new Mahasiswa("075410099", "AL Ayubbi");
         m = new Mahasiswa("1841720111", "Dhana", "Teknik", "Teknik Kedokteran", 4.0);
         try {
             writeObject(m);
             Mahasiswa readObject = (Mahasiswa) readObject();
             System.out.println("" + readObject);
         } catch (Exception ex) {
             Logger.getLogger(Praktikum1.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
