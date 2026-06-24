package controller;

import model.*;
import view.GUIView;
import javafx.application.Platform;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class KnowledgeController {
    private KnowledgeRepository repository;
    private GUIView view;

    public KnowledgeController(KnowledgeRepository repository, GUIView view) {
        this.repository = repository;
        this.view = view;
        initController();
    }

    private void initController() {
        DataDummy.loadData(repository);

        view.getBtnCari().setOnAction(e -> {
            String keyword = view.inputString("Masukkan Nama Terdakwa:");
            if (keyword != null && !keyword.trim().isEmpty()) {
                ArrayList<Putusan> hasil = repository.cariByNama(keyword);
                view.tampilkanDaftarPutusan(hasil);
                if (hasil.isEmpty()) view.tampilkanPesan("Data tidak ditemukan.");
            } else {
                refreshTable();
            }
        });

        view.getBtnSort().setOnAction(e -> {
            Collections.sort(repository.getSemuaData());
            refreshTable();
            view.tampilkanPesan("Data berhasil diurutkan berdasarkan Vonis Tertinggi!");
        });

        view.getBtnExport().setOnAction(e -> {
            try (PrintWriter pw = new PrintWriter(new File("Statistik_KMS.txt"))) {
                StatistikPutusan s = new StatistikPutusan(repository.getSemuaData());
                pw.println("Total: " + s.getTotalPutusan() + "\nRata Vonis: " + s.getRataRataVonis());
                view.tampilkanPesan("Ekspor berhasil ke Statistik_KMS.txt");
            } catch (Exception ex) { view.tampilkanPesan("Gagal Ekspor!"); }
        });

        view.getBtnTambah().setOnAction(e -> {
            Putusan p = view.showInputForm();
            if(p != null) { repository.simpan(p); refreshTable(); }
        });

        refreshTable();
    }

    public void start() { view.setVisible(true); }
    private void refreshTable() { view.tampilkanDaftarPutusan(repository.getSemuaData()); }
}