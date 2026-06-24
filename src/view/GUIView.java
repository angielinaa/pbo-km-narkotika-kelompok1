package view;

import javafx.collections.FXCollections;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.*;
import model.Putusan;
import java.io.File;
import java.util.ArrayList;

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
        root.setStyle("-fx-background-color: #FAFBFC;");

        // Header
        VBox header = new VBox(5);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: white; -fx-border-color: #E0E0E0; -fx-border-width: 0 0 1 0;");
        Label title = new Label("KMS Putusan Narkotika v2.0");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        header.getChildren().add(title);
        root.setTop(header);

        // Table
        table = new TableView<>();
        setupColumns();
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
        btnSort = new Button("Urutkan Vonis");
        btnExport = new Button("Export .txt");
        btnImportPDF = new Button("Import PDF");
        btnStatistik = new Button("Statistik");

        toolbar.getChildren().addAll(btnTambah, btnCari, btnHapus, btnSort, btnExport, btnImportPDF, btnStatistik);
        root.setBottom(toolbar);

        stage.setScene(new Scene(root, 1150, 650));
        stage.setTitle("KMS Narkotika Kelompok 1");
    }

    private void setupColumns() {
        TableColumn<Putusan, String> colNomor = new TableColumn<>("Nomor Perkara");
        colNomor.setCellValueFactory(new PropertyValueFactory<>("nomorPerkara"));
        TableColumn<Putusan, String> colNama = new TableColumn<>("Terdakwa");
        colNama.setCellValueFactory(new PropertyValueFactory<>("namaTerdakwa"));
        TableColumn<Putusan, Integer> colVonis = new TableColumn<>("Vonis (Bulan)");
        colVonis.setCellValueFactory(new PropertyValueFactory<>("vonisHukuman"));
        table.getColumns().addAll(colNomor, colNama, colVonis);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void setVisible(boolean v) { stage.show(); }
    public Button getBtnTambah() { return btnTambah; }
    public Button getBtnCari() { return btnCari; }
    public Button getBtnHapus() { return btnHapus; }
    public Button getBtnStatistik() { return btnStatistik; }
    public Button getBtnSort() { return btnSort; }
    public Button getBtnExport() { return btnExport; }
    public Button getBtnImportPDF() { return btnImportPDF; }

    public void tampilkanDaftarPutusan(ArrayList<Putusan> list) {
        table.setItems(FXCollections.observableArrayList(list));
    }

    public void tampilkanPesan(String p) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText(p); a.showAndWait();
    }

    public File pilihFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        return fc.showOpenDialog(stage);
    }

    public Putusan showInputForm() {
        // Dialog input sederhana
        TextInputDialog d = new TextInputDialog("Nomor Baru");
        d.setHeaderText("Masukkan Nomor Perkara:");
        return d.showAndWait().map(n -> new Putusan(n, "PN Jaksel", "2025-01-01", "Terdakwa", 25, "Sabu", 1.0, "Pasal 112", "Pemakai", 12, 1000000, "Hakim")).orElse(null);
    }
}