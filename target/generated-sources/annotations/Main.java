package app;

import model.KnowledgeRepository;
import view.GUIView;
import controller.KnowledgeController;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
// IMPORT INI WAJIB SETELAH LIBRARY DIMASUKKAN
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Font;

public class Main {
    public static void main(String[] args) {
        // 1. Setup Look and Feel FlatLaf yang Modern
        try {
            // Kita pakai tema Light yang clean.
            // Kalau mau gelap, ganti jadi FlatDarkLaf()
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Pengaturan Font Global agar lebih halus (Anti-aliasing default)
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));

        } catch (Exception e) {
            System.err.println("Gagal mengaktifkan FlatLaf. Tampilan akan kembali ke jadul.");
            e.printStackTrace();
        }

        // 2. Jalankan GUI
        SwingUtilities.invokeLater(() -> {
            KnowledgeRepository repository = new KnowledgeRepository();
            GUIView view = new GUIView();
            KnowledgeController controller = new KnowledgeController(repository, view);
            controller.start();
        });
    }
}