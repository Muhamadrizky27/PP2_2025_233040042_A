package id.ac.unpas.modul09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Latihan4 extends JFrame {

    private JTextArea textArea;
    private JButton btnOpenText, btnSaveText, btnAppendText; // Tambah btnAppendText
    private JButton btnSaveBinary, btnLoadBinary;
    private JFileChooser fileChooser;

    public Latihan4() {
        super("Tutorial File IO & Exception Handling");
        setSize(600, 450); // Tinggiin dikit biar muat
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea = new JTextArea();
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        JPanel buttonPanel = new JPanel();
        // Layout grid biar rapi (2 baris) karena tombol makin banyak
        buttonPanel.setLayout(new GridLayout(2, 3));

        btnOpenText = new JButton("Buka Text");
        btnSaveText = new JButton("Simpan (Overwrite)");
        btnAppendText = new JButton("Tambah (Append)"); // Tombol Baru
        btnSaveBinary = new JButton("Simpan Config");
        btnLoadBinary = new JButton("Muat Config");

        buttonPanel.add(btnOpenText);
        buttonPanel.add(btnSaveText);
        buttonPanel.add(btnAppendText); // Masukin ke panel
        buttonPanel.add(btnSaveBinary);
        buttonPanel.add(btnLoadBinary);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // --- Event Handling ---
        btnOpenText.addActionListener(e -> bukaFileTeks());
        btnSaveText.addActionListener(e -> simpanFileTeks());
        btnAppendText.addActionListener(e -> tambahFileTeks()); // Event baru
        btnSaveBinary.addActionListener(e -> simpanConfigBinary());
        btnLoadBinary.addActionListener(e -> muatConfigBinary());

        // Fitur Auto-Load & Auto-Save
        bacaLastNotes();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                simpanLastNotes();
                System.exit(0);
            }
        });
    }

    // --- LATIHAN 4: Method Append ---
    private void tambahFileTeks() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            // PERHATIKAN: ada parameter 'true' di sini
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

                writer.newLine(); // (Opsional) Kasih enter dulu biar gak nempel sama teks lama
                writer.write(textArea.getText());

                JOptionPane.showMessageDialog(this, "Teks berhasil ditambahkan di bawah!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Gagal append: " + ex.getMessage());
            }
        }
    }

    // --- Method Lain Tetap Sama ---

    private void bukaFileTeks() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    private void simpanFileTeks() { // Ini Mode Overwrite (Menimpa)
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            // Tidak ada 'true', berarti default-nya false (overwrite)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil ditimpa (Overwrite)!");
            } catch (IOException ex) { ex.printStackTrace(); }
        }
    }

    private void bacaLastNotes() {
        File file = new File("last_notes.txt");
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {}
    }

    private void simpanLastNotes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("last_notes.txt"))) {
            writer.write(textArea.getText());
        } catch (IOException ex) {}
    }

    private void simpanConfigBinary() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("config.bin"))) {
            dos.writeInt(textArea.getFont().getSize());
        } catch (IOException ex) {}
    }

    private void muatConfigBinary() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("config.bin"))) {
            textArea.setFont(new Font("Monospaced", Font.PLAIN, dis.readInt()));
        } catch (IOException ex) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplikasiFileIO().setVisible(true));
    }
}