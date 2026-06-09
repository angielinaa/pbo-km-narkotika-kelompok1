package controller;

import app.DatasetManager;
import app.MainView;
import app.PutusanNarkotika;
import javafx.scene.control.Alert;

public class InputHandler {

    public static void prosesInput(DatasetManager model, MainView view, KnowledgeController controller) {
        try {
            String nomor = view.getTxtNomor().getText();
            String nama = view.getTxtNama().getText();
            String tanggal = view.getTxtTanggal().getText();
            String hakim = view.getTxtHakim().getText();
            String zat = view.getTxtZat().getText();
            String peran = view.getRoleChoiceBox().getValue();

            if (nomor.trim().isEmpty() || nama.trim().isEmpty() || tanggal.trim().isEmpty() ||
                    hakim.trim().isEmpty() || zat.trim().isEmpty()) {
                throw new IllegalArgumentException("Semua kolom input data putusan wajib diisi!");
            }

            if (peran == null) {
                throw new IllegalArgumentException("Anda harus memilih Peran Hukum Terdakwa!");
            }

            double berat;
            int hukuman;
            try {
                berat = Double.parseDouble(view.getTxtBerat().getText());
                hukuman = Integer.parseInt(view.getTxtHukuman().getText());
            } catch (NumberFormatException nfe) {
                throw new IllegalArgumentException("Format input Berat (Gram) atau Vonis (Tahun) harus berupa angka murni!");
            }

            if (berat <= 0 || hukuman < 0) {
                throw new IllegalArgumentException("Nilai Berat Barang Bukti harus lebih dari 0 dan Vonis tidak boleh minus!");
            }

            PutusanNarkotika dataBaru = new PutusanNarkotika(nomor, nama, tanggal, hakim, zat, berat, peran, hukuman);
            model.tambahPutusan(dataBaru);

            controller.refreshDashboard();
            bersihkanForm(view);
            tampilkanNotifikasi(Alert.AlertType.INFORMATION, "Sukses", "Data putusan hukum baru berhasil divalidasi dan masuk indeks KMS.");

        } catch (IllegalArgumentException ex) {
            tampilkanNotifikasi(Alert.AlertType.ERROR, "Validasi Gagal", ex.getMessage());
        }
    }

    private static void bersihkanForm(MainView view) {
        view.getTxtNomor().clear();
        view.getTxtNama().clear();
        view.getTxtTanggal().clear();
        view.getTxtHakim().clear();
        view.getTxtZat().clear();
        view.getTxtBerat().clear();
        view.getTxtHukuman().clear();
        view.getRoleChoiceBox().setValue(null);
    }

    private static void tampilkanNotifikasi(Alert.AlertType tipe, String judul, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }
}