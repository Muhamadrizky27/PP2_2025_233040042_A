package id.ac.unpas.modul06.contoh;

import javax.swing.*;

public class ContohFlowLayout {
    public static void main (String[] args) {
        // 1. Buat Frame (Window)
        JFrame frame = new JFrame("Contoh FlowLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        // 2.Buat Panel (Container)
        // JPanel secara default sudah menggunakan FlowLayout
        JPanel panel = new JPanel();
        // Anda juga bisa mengaturnya secara eksplisit:
        // panel.setLayout (new FlowLayout(FlowLayout.CENTER)); // CENTER, LEFT, atau RIGHT

        // 3. Buat dan tambahkan komponen
        panel.add(new JButton("Tombol 1"));
        panel.add(new JButton("Tombol 2"));
        panel.add(new JButton("Tombol tiga"));
        panel.add(new JButton("Tombol Empat Panjang"));
        panel.add(new JButton("Tombol 5"));

        // 4. Tambahkan panel ke frame
        frame.add(panel);

        // 5. Tampilkan frame
        frame.setVisible(true);
    }
}
