package id.ac.unpas.modul10;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        try {
            //URL Database (Ganti 'root' dan '' sesuai user/pass database lokal anda)
            String url ="jdbc:mysql://localhost:3306/db_pp2_latihan";
            String user = "root";
            String pass = "";

            //Regristasi Driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            //Buat koneksi
            mysqlconfig = DriverManager.getConnection(url, user, pass);

        }   catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }
}
