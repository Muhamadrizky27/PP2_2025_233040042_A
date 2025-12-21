package id.ac.unpas.modul10.view;

import id.ac.unpas.modul10.model.Mahasiswa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MahasiswaView extends JFrame {

    private JTextField txtNama, txtNIM, txtJurusan, txtCari;
    private JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    private JTable tableMahasiswa;
    private DefaultTableModel model;

    public MahasiswaView() {
        setTitle("Aplikasi CRUD Mahasiswa - MVC Architecture");
        setSize(750, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Form Data Mahasiswa"));
        panelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelForm.add(new JLabel("Nama:"));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM:"));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan:"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        btnSimpan = new JButton("Simpan");

        btnEdit = new JButton("Edit");

        btnHapus = new JButton("Hapus");

        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelForm, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);
        add(panelAtas, BorderLayout.NORTH);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        tableMahasiswa.getTableHeader().setReorderingAllowed(false);
        tableMahasiswa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tableMahasiswa.getColumnModel().getColumn(0).setPreferredWidth(50);
        tableMahasiswa.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableMahasiswa.getColumnModel().getColumn(2).setPreferredWidth(100);
        tableMahasiswa.getColumnModel().getColumn(3).setPreferredWidth(200);

        tableMahasiswa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tableMahasiswa.getSelectedRow();
                if (row != -1) {
                    txtNama.setText(model.getValueAt(row, 1).toString());
                    txtNIM.setText(model.getValueAt(row, 2).toString());
                    txtJurusan.setText(model.getValueAt(row, 3).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Data Mahasiswa"));
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelCari.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        panelCari.add(new JLabel(" Cari Nama:"));
        txtCari = new JTextField(25);
        panelCari.add(txtCari);

        btnCari = new JButton("Cari");
        btnCari.setFocusPainted(false);
        panelCari.add(btnCari);

        add(panelCari, BorderLayout.SOUTH);
    }

    public String getNama() {
        return txtNama.getText();
    }

    public String getNim() {
        return txtNIM.getText();
    }

    public String getJurusan() {
        return txtJurusan.getText();
    }

    public String getKeywordCari() {
        return txtCari.getText();
    }

    public void showMahasiswaList(List<Mahasiswa> list) {
        model.setRowCount(0);
        int no = 1;
        for (Mahasiswa mhs : list) {
            model.addRow(new Object[]{
                    no++,
                    mhs.getNama(),
                    mhs.getNim(),
                    mhs.getJurusan()
            });
        }
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public void clearForm() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
        txtCari.setText("");
        tableMahasiswa.clearSelection();
    }

    public void addSimpanListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addHapusListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void addCariListener(ActionListener listener) {
        btnCari.addActionListener(listener);
    }
}