package id.ac.unpas.modul10;

import id.ac.unpas.modul10.controller.MahasiswaController;
import id.ac.unpas.modul10.view.MahasiswaView;



import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            MahasiswaView view = new MahasiswaView();

            MahasiswaController controller = new MahasiswaController(view);

            view.setVisible(true);
        });
    }
}