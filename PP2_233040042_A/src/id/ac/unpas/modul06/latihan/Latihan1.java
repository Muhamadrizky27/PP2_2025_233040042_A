package id.ac.unpas.modul06.latihan;

import javax.swing.*;
import java.awt.*;

public class Latihan1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalkulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout(10, 10));

        JTextField field = new JTextField();
        frame.add(field,BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,5,5));

        panel.add(new JButton("7"));
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        panel.add(new JButton("/"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("*"));
        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("-"));
        panel.add(new JButton("0"));
        panel.add(new JButton("."));
        panel.add(new JButton("="));
        panel.add(new JButton("+"));



        frame.add(panel,BorderLayout.CENTER);

        frame.setVisible(true);
    }
}