
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TugasManajemenNilaiSiswaApp extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(5,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new Label("Nama Siswa"));
        txtNama = new JTextField();
        panel.add(txtNama);

        panel.add(new JLabel("Mata Pelajaran"));
        String[] matkul = {"Matematika Dasar", "Bahasa Indonesia", "Algoritma dan Pemrograman 1", "Praktikum Pemrograman II"};
        cmbMatkul = new JComboBox<>(matkul);
        panel.add(cmbMatkul);

        panel.add(new JLabel("Nilai 1 - 100"));
        txtNilai = new JTextField();
        panel.add(txtNilai);

        JButton btnSimpan = new JButton("Simpan Data");
        panel.add(new JLabel(" "));
        panel.add(btnSimpan);

        JButton btnReset = new JButton("Reset");
        panel.add(btnReset);

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });

        btnReset.addActionListener(e -> {
            txtNama.setText("");
            txtNilai.setText("");
            cmbMatkul.setSelectedIndex(0);
        });
        return panel;
    }
    private JPanel createTabPanel(){
        JPanel panel = new JPanel(new BorderLayout());

        JButton btnHapus = new JButton("Hapus Data");
        panel.add(btnHapus, BorderLayout.SOUTH);

        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scrollPane =  new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        btnHapus.addActionListener(e -> {
            int index = tableData.getSelectedRow();
            if(index > -1){
                tableModel.removeRow(index);
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        return panel;
    }

    private void prosesSimpan(){
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        if(nama.trim().length() <3 ){
            JOptionPane.showMessageDialog(this, "Nama minimal 3 karakter!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try{
            nilai = Integer.parseInt(strNilai);
            if (nilai < 0 || nilai > 100){
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 - 100!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String grade;
        switch (nilai / 10) {
            case 10:
            case 9:
            case 8:
                grade = "A";
                break;
            case 7:
                grade = "AB";
                break;
            case 6:
                grade = "B";
                break;
            case 5:
                grade = "BC";
                break;
            case 4:
                grade = "C";
                break;
            case 3:
                grade = "D";
                break;
            default:
                grade = "E";
        }


        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        tabbedPane.setSelectedIndex(1);
    }
    public TugasManajemenNilaiSiswaApp(){
        setTitle("Aplikasi Manajemen Nilai Siswa");
        setSize(500,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel panelInput = createInputPanel();
        tabbedPane.addTab("Input Data", panelInput);

        JPanel panelTable = createTabPanel();
        tabbedPane.addTab("Daftar Nilai", panelTable);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TugasManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}