import javax.swing.*;
import java.awt.*;

public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // 1. Atur Layout Manager ke BorderLayout
                // Sebenarnya ini tidak perlu
                // Karena BorderLayout adalah Layout Manager default
                frame.setLayout(new BorderLayout());

                // 2. Buat Komponen
                JLabel label = new JLabel("Label ada di Atas (NORTH");
                JButton button = new JButton("Tombl ada dibawah (SOUTH)");

                // 3. Tambahkan Aksi (ActionListener) ke tombol
                button.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });

                // 4. Tambahkan komponen ke frame DENGAN POSISI
                frame.add(label, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);

                //Kita bisa tambahkan komponen lain
                frame.add(new JButton("WEST"), BorderLayout.WEST);
                frame.add(new JButton("EAST"), BorderLayout.EAST);
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
