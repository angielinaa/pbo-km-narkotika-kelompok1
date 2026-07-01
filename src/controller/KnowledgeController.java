package controller;

import model.*;
import view.GUIView;
import javafx.application.Platform;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import util.PDFReader;
import java.io.File;

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

        view.getBtnHapus().setOnAction(e -> {

            String nomor = view.inputString(
                    "Masukkan Nomor Perkara yang akan dihapus");

            if (nomor == null || nomor.isEmpty())
                return;

            boolean berhasil = repository.hapus(nomor);

            if (berhasil) {

                refreshTable();

                view.tampilkanPesan("Data berhasil dihapus.");

            } else {

                view.tampilkanPesan("Data tidak ditemukan.");

            }

        });

        view.getBtnSort().setOnAction(e -> {
            Collections.sort(repository.getSemuaData());
            refreshTable();
            view.tampilkanPesan("Data berhasil diurutkan berdasarkan Vonis Tertinggi!");
        });

        view.getBtnExport().setOnAction(e -> {

            try (PrintWriter pw = new PrintWriter("Statistik_KMS.txt")) {

                StatistikPutusan statistik =
                        new StatistikPutusan(repository.getSemuaData());

                pw.println("======================================");
                pw.println(" KMS PUTUSAN PENGADILAN NARKOTIKA");
                pw.println("======================================");
                pw.println();

                pw.println("Jumlah Putusan      : "
                        + statistik.getTotalPutusan());

                pw.println("Rata-rata Vonis     : "
                        + statistik.getRataRataVonis() + " bulan");

                pw.println("Rata-rata Denda     : Rp "
                        + statistik.getRataRataDenda());

                pw.println("Jenis Terbanyak     : "
                        + statistik.getJenisNarkotikaTerbanyak());

                pw.println();
                pw.println("===== DISTRIBUSI JENIS NARKOTIKA =====");

                for (var entry : statistik.getDistribusiJenis().entrySet()) {

                    pw.println(entry.getKey() + " : " + entry.getValue());

                }

                pw.println();
                pw.println("========== DATA PUTUSAN ==========");

                for (Putusan p : repository.getSemuaData()) {

                    pw.println("----------------------------------");
                    pw.println("Nomor Perkara : " + p.getNomorPerkara());
                    pw.println("Pengadilan    : " + p.getPengadilan());
                    pw.println("Tanggal       : " + p.getTanggalPutusan());
                    pw.println("Terdakwa      : " + p.getNamaTerdakwa());
                    pw.println("Jenis         : " + p.getJenisNarkotika());
                    pw.println("Vonis         : " + p.getVonisHukuman() + " bulan");
                    pw.println("Denda         : Rp " + p.getVonisDenda());
                    pw.println("Hakim         : " + p.getNamaHakim());
                }

                pw.println();
                pw.println("======================================");
                pw.println(" Laporan selesai dibuat.");
                pw.println("======================================");

                view.tampilkanPesan("Export TXT berhasil!");

            } catch (Exception ex) {

                view.tampilkanPesan("Gagal Export!\n" + ex.getMessage());

            }

        });

        view.getBtnImportPDF().setOnAction(e -> {

            File file = view.pilihFile();

            if (file == null) {
                return;
            }

            try {

                Putusan putusan = PDFReader.importPutusan(file);

                // ===== VALIDASI DUPLIKAT =====
                if (repository.cariByNomor(putusan.getNomorPerkara()) != null) {

                    view.tampilkanPesan("Nomor perkara sudah ada.");

                    return;

                }

                repository.simpan(putusan);

                refreshTable();

                view.tampilkanPesan("Import PDF berhasil!");

            } catch (Exception ex) {

                view.tampilkanPesan("Import gagal!\n" + ex.getMessage());

            }

        });

        view.getBtnTambah().setOnAction(e -> {
            Putusan p = view.showInputForm();
            if(p != null) { repository.simpan(p); refreshTable(); }
        });

        view.getBtnStatistik().setOnAction(e -> {

            StatistikPutusan statistik =
                    new StatistikPutusan(repository.getSemuaData());

            String hasil =

                    "===== STATISTIK =====\n\n"

                            + "Jumlah Putusan : "
                            + statistik.getTotalPutusan()

                            + "\n\nRata-rata Vonis : "
                            + statistik.getRataRataVonis()

                            + "\n\nRata-rata Denda : Rp "
                            + statistik.getRataRataDenda()

                            + "\n\nJenis Terbanyak : "
                            + statistik.getJenisNarkotikaTerbanyak();

            view.tampilkanPesan(hasil);

        });

        refreshTable();
    }

    public void start() { view.setVisible(true); }
    private void refreshTable() { view.tampilkanDaftarPutusan(repository.getSemuaData()); }
}