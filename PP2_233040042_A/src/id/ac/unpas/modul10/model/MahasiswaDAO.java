package id.ac.unpas.modul10.model;


import id.ac.unpas.modul10.KoneksiDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {

    public List<Mahasiswa> getAllMahasiswa() throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet res = null;

        try {
            conn = KoneksiDB.configDB();
            stm = conn.createStatement();
            res = stm.executeQuery("SELECT * FROM mahasiswa ORDER BY nama");

            while (res.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNama(res.getString("nama"));
                mhs.setNim(res.getString("nim"));
                mhs.setJurusan(res.getString("jurusan"));
                list.add(mhs);
            }
        } finally {
            if (res != null) res.close();
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        }

        return list;
    }

    public boolean isNimExists(String nim) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            conn = KoneksiDB.configDB();
            String sql = "SELECT nim FROM mahasiswa WHERE nim = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, nim);

            rs = pst.executeQuery();
            exists = rs.next();
        } finally {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }

        return exists;
    }

    public void insertMahasiswa(Mahasiswa mhs) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
            conn = KoneksiDB.configDB();
            pst = conn.prepareStatement(sql);

            pst.setString(1, mhs.getNama());
            pst.setString(2, mhs.getNim());
            pst.setString(3, mhs.getJurusan());

            pst.executeUpdate();
        } finally {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }
    }

    public void updateMahasiswa(Mahasiswa mhs) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
            conn = KoneksiDB.configDB();
            pst = conn.prepareStatement(sql);

            pst.setString(1, mhs.getNama());
            pst.setString(2, mhs.getJurusan());
            pst.setString(3, mhs.getNim());

            pst.executeUpdate();
        } finally {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }
    }

    public void deleteMahasiswa(String nim) throws SQLException {
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            String sql = "DELETE FROM mahasiswa WHERE nim = ?";
            conn = KoneksiDB.configDB();
            pst = conn.prepareStatement(sql);

            pst.setString(1, nim);

            pst.executeUpdate();
        } finally {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }
    }

    public List<Mahasiswa> searchMahasiswaByNama(String keyword) throws SQLException {
        List<Mahasiswa> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet res = null;

        try {
            conn = KoneksiDB.configDB();
            String sql = "SELECT * FROM mahasiswa WHERE nama LIKE ? ORDER BY nama";
            pst = conn.prepareStatement(sql);

            pst.setString(1, "%" + keyword + "%");
            res = pst.executeQuery();

            while (res.next()) {
                Mahasiswa mhs = new Mahasiswa();
                mhs.setNama(res.getString("nama"));
                mhs.setNim(res.getString("nim"));
                mhs.setJurusan(res.getString("jurusan"));
                list.add(mhs);
            }
        } finally {
            if (res != null) res.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        }

        return list;
    }
}