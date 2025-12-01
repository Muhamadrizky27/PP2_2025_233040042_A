package id.ac.unpas.modul06.latihan;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Latihan2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Konverter Suhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel lblCelcius = new JLabel("Celcius:");
        JTextField txtCelcius = new JTextField(10);
        JButton btnKonversi = new JButton("Konversi");
        JLabel lblFahrenheit = new JLabel("Fahrenheit:");
        JLabel hasilFahrenheit = new JLabel("");

        frame.add(lblCelcius);
        frame.add(txtCelcius);
        frame.add(new JLabel(""));
        frame.add(btnKonversi);
        frame.add(lblFahrenheit);
        frame.add(hasilFahrenheit);


        btnKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double celcius = Double.parseDouble(txtCelcius.getText());
                    double fahrenheit = (celcius * 9 / 5) + 32;
                    hasilFahrenheit.setText(String.format("%.2f", fahrenheit));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Masukkan angka yang valid!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}