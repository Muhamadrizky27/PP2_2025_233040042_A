import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenNilaiSiswaApp extends JFrame {
    private JTextField txtNama;
    private JTextField txtNilai;
    private JComboBox<String> cmbMatkul;;
    private JTable tableData;
    private DefaultTableModel tableModel;
    private JTabbedPane tabbedPane;

    private JPanel createInputPanel(){
        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
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

        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        return panel;
    }
    private JPanel createTabPanel(){
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"Nama Siswa", "Mata Pelajaran", "Nilai", "Grade"};
        tableModel = new DefaultTableModel(kolom, 0);
        tableData = new JTable(tableModel);

        JScrollPane scrollPane =  new JScrollPane(tableData);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void prosesSimpan(){
        String nama = txtNama.getText();
        String matkul = (String) cmbMatkul.getSelectedItem();
        String strNilai = txtNilai.getText();

        if(nama.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Error Validasi", JOptionPane.ERROR_MESSAGE);
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
        if (nilai >= 80) grade = "A";
        else if (nilai >= 70) grade = "AB";
        else if (nilai >= 60) grade = "B";
        else if (nilai >= 50) grade = "BC";
        else if (nilai >= 40) grade = "C";
        else if (nilai >= 30) grade = "D";
        else grade = "E";

        Object[] dataBaris = {nama, matkul, nilai, grade};
        tableModel.addRow(dataBaris);

        txtNama.setText("");
        txtNilai.setText("");
        cmbMatkul.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
        tabbedPane.setSelectedIndex(1);
    }
    public ManajemenNilaiSiswaApp(){
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
            new ManajemenNilaiSiswaApp().setVisible(true);
        });
    }
}