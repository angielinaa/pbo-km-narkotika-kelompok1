package controller;

import model.*;
import view.GUIView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.*;
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

        view.getBtnTambah().setOnAction(e -> {
            Putusan p = view.showInputForm();
            if (p != null) { repository.simpan(p); refreshTable(); }
        });

        view.getBtnSort().setOnAction(e -> {
            Collections.sort(repository.getSemuaData());
            refreshTable();
            view.tampilkanPesan("Data berhasil diurutkan berdasarkan Vonis Tertinggi!");
        });

        view.getBtnExport().setOnAction(e -> exportKeTxt());

        view.getBtnImportPDF().setOnAction(e -> importPDF());

        view.getBtnStatistik().setOnAction(e -> {
            StatistikPutusan s = new StatistikPutusan(repository.getSemuaData());
            view.tampilkanPesan("Total Putusan: " + s.getTotalPutusan() + "\nRata-rata Vonis: " + s.getRataRataVonis() + " bulan");
        });

        refreshTable();
    }

    public void start() { view.setVisible(true); }
    private void refreshTable() { view.tampilkanDaftarPutusan(repository.getSemuaData()); }

    private void exportKeTxt() {
        try (PrintWriter pw = new PrintWriter(new File("Laporan_KMS.txt"))) {
            pw.println("LAPORAN KMS NARKOTIKA");
            for (Putusan p : repository.getSemuaData()) {
                pw.println(p.getNomorPerkara() + " | " + p.getNamaTerdakwa() + " | " + p.getVonisHukuman() + " Bln");
            }
            view.tampilkanPesan("Berhasil ekspor ke Laporan_KMS.txt");
        } catch (Exception e) { view.tampilkanPesan("Gagal ekspor!"); }
    }

    private void importPDF() {
        File f = view.pilihFile();
        if (f != null) {
            try (PDDocument doc = PDDocument.load(f)) {
                String teks = new PDFTextStripper().getText(doc);
                // Bonus PDF Parsing: Membaca teks mentah
                view.tampilkanPesan("PDF Berhasil dibaca! (Awal teks: " + teks.substring(0, 30) + ")");
            } catch (Exception e) { view.tampilkanPesan("Error membaca PDF"); }
        }
    }
}