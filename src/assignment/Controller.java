/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rizky
 */
public class Controller {

    List<Mahasiswa> listMahasiswa;
    MahasiswaGUI view;

    Controller(MahasiswaGUI view) {
        this.view = view;
    }

    public void init() {
        try {
            if (readObject() != null) {
                listMahasiswa = (List<Mahasiswa>) readObject();
                view.getjTable1().setModel(viewTable(listMahasiswa));
            } else {
                listMahasiswa = new ArrayList<>();
                writeObject(listMahasiswa);
            }
        } catch (Exception ex) {
            Logger.getLogger(MahasiswaGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeObject(Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mhs.obj"));
            oos.writeObject(o);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public Object readObject() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("mhs.obj"));
            Object readObject = ois.readObject();
            ois.close();
            return readObject;
        } catch (ClassNotFoundException ex) {
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public DefaultTableModel viewTable(List<Mahasiswa> mhs) {
        DefaultTableModel table = new DefaultTableModel(new String[]{"No", "NIM", "Nama", "Jurusan", "Prodi", "IPK"}, 0);
        for (int i = 0; i < mhs.size(); i++) {
            Mahasiswa m = mhs.get(i);
            Vector v = new Vector();
            v.add(i + 1);
            v.add(m.getNim());
            v.add(m.getNama());
            v.add(m.getJurusan());
            v.add(m.getProdi());
            v.add(m.getIpk());
            table.addRow(v);
        }
        return table;
    }

    public void simpan() {
        if (!view.getTxtNIM().getText().isEmpty() && !view.getTxtNama().getText().isEmpty()) {
            Mahasiswa mhs = new Mahasiswa(view.getTxtNIM().getText(), view.getTxtNama().getText(), view.getTxtJurusan().getText(), view.getTxtProdi().getText(), Double.parseDouble(view.getTxtIPK().getText()));
            listMahasiswa.add(mhs);
            try {
                writeObject(listMahasiswa);
                List<Mahasiswa> newList = (List<Mahasiswa>) readObject();
                view.getjTable1().setModel(viewTable(newList));
            } catch (Exception ex) {
                Logger.getLogger(MahasiswaGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(view, "NIM/ Name should not be empty!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update() {
        if (view.getjTable1().getSelectedRow() >= 0) {
            Mahasiswa mhs = listMahasiswa.get(view.getjTable1().getSelectedRow());
            mhs.setIpk(Double.parseDouble(view.getTxtIPK().getText()));
            mhs.setJurusan(view.getTxtJurusan().getText());
            mhs.setNama(view.getTxtNama().getText());
            mhs.setNim(view.getTxtNIM().getText());
            mhs.setProdi(view.getTxtProdi().getText());
            listMahasiswa.set(view.getjTable1().getSelectedRow(), mhs);
        } else {
            JOptionPane.showMessageDialog(view, "Select the row!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        view.getjTable1().setModel(viewTable(listMahasiswa));
    }

    public void hapus() {
        if (view.getjTable1().getSelectedRow() >= 0) {
            listMahasiswa.remove(view.getjTable1().getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(view, "Select the row!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        view.getjTable1().setModel(viewTable(listMahasiswa));
    }

    public void clear() {
        view.getTxtIPK().setText("");
        view.getTxtJurusan().setText("");
        view.getTxtNama().setText("");
        view.getTxtNIM().setText("");
        view.getTxtProdi().setText("");
    }
}
