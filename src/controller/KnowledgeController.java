package controller;

import app.DatasetManager;
import app.MainView;
import app.PutusanNarkotika;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import java.util.List;

public class KnowledgeController {
    private DatasetManager model;
    private MainView view;

    public KnowledgeController(DatasetManager model, MainView view) {
        this.model = model;
        this.view = view;
        hubungkanKomponen();
    }

    private void hubungkanKomponen() {
        muatUlangTabel(model.getAllPutusan());

        view.getRoleChoiceBox().setItems(FXCollections.observableArrayList(model.getKategoriPeran()));

        view.getBtnCari().setOnAction(e -> handlePencarian());

        view.getBtnRefresh().setOnAction(e -> {
            view.getTxtCari().clear();
            muatUlangTabel(model.getAllPutusan());
        });

        view.getBtnTambah().setOnAction(e -> handleInputBaru());
    }

    private void muatUlangTabel(List<PutusanNarkotika> data) {
        view.getTableView().setItems(FXCollections.observableArrayList(data));
        view.getLblTotalData().setText("Total Putusan Terindeks: " + data.size() + " Dokumen");
    }

    private void handlePencarian() {
        String kataKunci = view.getTxtCari().getText();
        if (kataKunci == null || kataKunci.trim().isEmpty()) {
            muatUlangTabel(model.getAllPutusan());
        } else {
            List<PutusanNarkotika> hasil = model.cariBerdasarkanKeyword(kataKunci);
            muatUlangTabel(hasil);
        }
    }

    private void handleInputBaru() {
        InputHandler.prosesInput(model, view, this);
    }

    public void refreshDashboard() {
        muatUlangTabel(model.getAllPutusan());
    }
}