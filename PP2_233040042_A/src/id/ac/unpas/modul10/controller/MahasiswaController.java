package id.ac.unpas.modul10.controller;

import id.ac.unpas.modul10.model.Mahasiswa;
import id.ac.unpas.modul10.model.MahasiswaDAO;
import id.ac.unpas.modul10.view.MahasiswaView;

import javax.swing.*;
import java.util.List;

public class MahasiswaController {
    private MahasiswaView view;
    private MahasiswaDAO dao;


    public MahasiswaController(MahasiswaView view) {
        this.view = view;
        this.dao = new MahasiswaDAO();

        initController();
    }

    private void initController() {
        view.addSimpanListener(e -> tambahMahasiswa());
        view.addEditListener(e -> ubahMahasiswa());
        view.addHapusListener(e -> hapusMahasiswa());
        view.addClearListener(e -> view.clearForm());
        view.addCariListener(e -> cariMahasiswa());

        loadAllMahasiswa();
    }

    public void loadAllMahasiswa() {
        try {
            List<Mahasiswa> list = dao.getAllMahasiswa();
            view.showMahasiswaList(list);
        } catch (Exception e) {
            view.showMessage(
                    "Gagal memuat data: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }


    private void tambahMahasiswa() {
        String nama = view.getNama().trim();
        String nim = view.getNim().trim();
        String jurusan = view.getJurusan().trim();

        if (nama.isEmpty() || nim.isEmpty()) {
            view.showMessage(
                    "Nama dan NIM tidak boleh kosong!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!nim.matches("\\d+")) {
            view.showMessage(
                    "NIM harus berupa angka!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            if (dao.isNimExists(nim)) {
                view.showMessage(
                        "NIM " + nim + " sudah terdaftar!\nGunakan NIM lain.",
                        "Data Duplikat",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
            dao.insertMahasiswa(mhs);

            view.showMessage(
                    "Data mahasiswa berhasil disimpan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAllMahasiswa();
            view.clearForm();

        } catch (Exception e) {
            view.showMessage(
                    "Gagal menyimpan data: " + e.getMessage(),
                    "Error Database",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void ubahMahasiswa() {
        String nama = view.getNama().trim();
        String nim = view.getNim().trim();
        String jurusan = view.getJurusan().trim();

        if (nama.isEmpty() || nim.isEmpty()) {
            view.showMessage(
                    "Nama dan NIM tidak boleh kosong!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (!nim.matches("\\d+")) {
            view.showMessage(
                    "NIM harus berupa angka!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            if (!dao.isNimExists(nim)) {
                view.showMessage(
                        "Data dengan NIM " + nim + " tidak ditemukan!",
                        "Data Tidak Ada",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda yakin ingin mengubah data ini?",
                    "Konfirmasi Update",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                Mahasiswa mhs = new Mahasiswa(nama, nim, jurusan);
                dao.updateMahasiswa(mhs);

                view.showMessage(
                        "Data mahasiswa berhasil diubah!",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadAllMahasiswa();
                view.clearForm();
            }

        } catch (Exception e) {
            view.showMessage(
                    "Gagal mengubah data: " + e.getMessage(),
                    "Error Database",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void hapusMahasiswa() {
        String nim = view.getNim().trim();

        if (nim.isEmpty()) {
            view.showMessage(
                    "Pilih data mahasiswa yang akan dihapus terlebih dahulu!",
                    "Validasi Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            if (!dao.isNimExists(nim)) {
                view.showMessage(
                        "Data dengan NIM " + nim + " tidak ditemukan!",
                        "Data Tidak Ada",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Apakah Anda yakin ingin menghapus data mahasiswa dengan NIM " + nim + "?\n" +
                            "Nama: " + view.getNama() + "\n" +
                            "Jurusan: " + view.getJurusan() + "\n\n" +
                            "Data yang dihapus tidak dapat dikembalikan!",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                dao.deleteMahasiswa(nim);

                view.showMessage(
                        "Data mahasiswa berhasil dihapus!",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadAllMahasiswa();
                view.clearForm();
            }

        } catch (Exception e) {
            view.showMessage(
                    "Gagal menghapus data: " + e.getMessage(),
                    "Error Database",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    private void cariMahasiswa() {
        String keyword = view.getKeywordCari().trim();

        if (keyword.isEmpty()) {
            loadAllMahasiswa();
            return;
        }

        try {
            List<Mahasiswa> list = dao.searchMahasiswaByNama(keyword);

            if (list.isEmpty()) {
                view.showMessage(
                        "Data dengan nama '" + keyword + "' tidak ditemukan!",
                        "Hasil Pencarian",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            view.showMahasiswaList(list);

        } catch (Exception e) {
            view.showMessage(
                    "Gagal mencari data: " + e.getMessage(),
                    "Error Database",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }
}
