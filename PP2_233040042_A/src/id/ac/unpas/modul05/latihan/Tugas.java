package id.ac.unpas.modul05.latihan;

import javax.swing.*;
import java.awt.*;

public class Tugas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater((new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLaayout dengan Aksi Lengkap");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                //Label utama di bagian atas
                JLabel label = new JLabel("Label ada di Atas (NORTH)", JLabel.CENTER);

                //Tombol-tombol di posisi BorderLayout
                JButton btnSouth = new JButton("Tombol di Bawah(SOUTH");
                JButton btnWest = new JButton("(WEST)");
                JButton btnEast = new JButton("(EAST)");
                JButton btnCenter = new JButton("(CENTER)");

                //Aksi masing-masing tombol
                btnSouth.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                btnWest.addActionListener(e -> {
                    label.setText("Tombol di WEST diklik!");
                });

                btnEast.addActionListener(e -> {
                    label.setText("Tombol di EAST diklik!");
                });

                btnCenter.addActionListener(e -> {
                    label.setText("Tombol di CENTER diklik!");
                });

                //Tambahkan semua komponen ke frame
                frame.add(label,BorderLayout.NORTH);
                frame.add(btnSouth, BorderLayout.SOUTH);
                frame.add(btnWest, BorderLayout.WEST);
                frame.add(btnEast, BorderLayout.EAST);
                frame.add(btnCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        }));
    }
}


