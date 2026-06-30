package view;

import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.Putusan;
import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class GUIView {
    private Stage stage;
    private TableView<Putusan> table;
    private Button btnTambah, btnCari, btnHapus, btnStatistik, btnSort, btnExport, btnImportPDF;

    public GUIView(Stage stage) {
        this.stage = stage;
        setupUI();
    }

    private void setupUI() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #FAFBFC; -fx-font-family: 'Segoe UI';");

        // Header
        VBox header = new VBox(5);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0; -fx-border-width: 0 0 1 0;");
        Label title = new Label("KMS Putusan Narkotika v2.0");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        header.getChildren().add(title);
        root.setTop(header);

        // Tabel
        table = new TableView<>();
        setupTableColumns();
        VBox center = new VBox(table);
        center.setPadding(new Insets(20));
        VBox.setVgrow(table, Priority.ALWAYS);
        root.setCenter(center);

        // Toolbar
        HBox toolbar = new HBox(10);
        toolbar.setPadding(new Insets(10, 20, 20, 20));
        btnTambah = new Button("Tambah");
        btnCari = new Button("Cari");
        btnHapus = new Button("Hapus");
        btnSort = new Button("Urutkan");
        btnExport = new Button("Export .txt");
        btnImportPDF = new Button("Import PDF");
        btnStatistik = new Button("Statistik");

        toolbar.getChildren().addAll(btnTambah, btnCari, btnHapus, btnSort, btnExport, btnImportPDF, btnStatistik);
        root.setBottom(toolbar);

        stage.setScene(new Scene(root, 1200, 700));
        stage.setTitle("KMS Narkotika Kelompok 1");
    }

    private void setupTableColumns() {
        TableColumn<Putusan, String> colNomor = new TableColumn<>("Nomor Perkara");
        colNomor.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));
        TableColumn<Putusan, String> colNama = new TableColumn<>("Terdakwa");
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));
        TableColumn<Putusan, Integer> colVonis = new TableColumn<>("Vonis (Bulan)");
        colVonis.setCellValueFactory(new PropertyValueFactory<>("vonisHukuman"));
        table.getColumns().addAll(colNomor, colNama, colVonis);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // FIX POPUP CARI/HAPUS
    public String inputString(String pesan) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.initOwner(stage); // AGAR TIDAK MUNCUL DI BELAKANG
        dialog.setTitle("Pencarian/Input");
        dialog.setHeaderText(null);
        dialog.setContentText(pesan);
        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    // FIX POPUP PESAN
    public void tampilkanPesan(String pesan) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(stage); // AGAR TIDAK MUNCUL DI BELAKANG
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public File pilihFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        return fc.showOpenDialog(stage);
    }

    // GETTERS
    public Button getBtnTambah() { return btnTambah; }
    public Button getBtnCari() { return btnCari; }
    public Button getBtnHapus() { return btnHapus; }
    public Button getBtnStatistik() { return btnStatistik; }
    public Button getBtnSort() { return btnSort; }
    public Button getBtnExport() { return btnExport; }
    public Button getBtnImportPDF() { return btnImportPDF; }
    public void setVisible(boolean v) { if(v) stage.show(); }
    public void tampilkanDaftarPutusan(ArrayList<Putusan> list) { table.setItems(FXCollections.observableArrayList(list)); }

    // Tambah Form Dialog (Sederhana)
    public Putusan showInputForm() {

        TextInputDialog dialog;

        dialog = new TextInputDialog();
        dialog.initOwner(stage);
        dialog.setHeaderText("Masukkan Nomor Perkara:");
        String nomor = dialog.showAndWait().orElse("");
        if (nomor.isBlank()) return null;

        dialog = new TextInputDialog();
        dialog.initOwner(stage);
        dialog.setHeaderText("Masukkan Nama Pengadilan:");
        String pengadilan = dialog.showAndWait().orElse("");
        if (pengadilan.isBlank()) return null;

        dialog = new TextInputDialog();
        dialog.initOwner(stage);
        dialog.setHeaderText("Masukkan Tanggal Putusan (yyyy-mm-dd):");
        String tanggal = dialog.showAndWait().orElse("");
        if (tanggal.isBlank()) return null;

        dialog = new TextInputDialog();
        dialog.initOwner(stage);
        dialog.setHeaderText("Masukkan Nama Terdakwa:");
        String terdakwa = dialog.showAndWait().orElse("");
        if (terdakwa.isBlank()) return null;

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Usia Terdakwa:");
        int usia;
        try {
            usia = Integer.parseInt(dialog.showAndWait().orElse("0"));
        } catch (Exception e) {
            tampilkanPesan("Usia harus berupa angka.");
            return null;
        }

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Jenis Narkotika:");
        String jenis = dialog.showAndWait().orElse("");
        if (jenis.isBlank()) return null;

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Berat Barang Bukti (gram):");
        double berat;
        try {
            berat = Double.parseDouble(dialog.showAndWait().orElse("0"));
        } catch (Exception e) {
            tampilkanPesan("Berat harus berupa angka.");
            return null;
        }

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Pasal:");
        String pasal = dialog.showAndWait().orElse("");

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Peran (Pemakai/Pengedar/Kurir):");
        String peran = dialog.showAndWait().orElse("");

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Lama Vonis (bulan):");
        int vonis;
        try {
            vonis = Integer.parseInt(dialog.showAndWait().orElse("0"));
        } catch (Exception e) {
            tampilkanPesan("Vonis harus berupa angka.");
            return null;
        }

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Denda:");
        double denda;
        try {
            denda = Double.parseDouble(dialog.showAndWait().orElse("0"));
        } catch (Exception e) {
            tampilkanPesan("Denda harus berupa angka.");
            return null;
        }

        dialog = new TextInputDialog();
        dialog.setHeaderText("Masukkan Nama Hakim:");
        String hakim = dialog.showAndWait().orElse("");

        return new Putusan(
                nomor,
                pengadilan,
                tanggal,
                terdakwa,
                usia,
                jenis,
                berat,
                pasal,
                peran,
                vonis,
                denda,
                hakim
        );
    }
}