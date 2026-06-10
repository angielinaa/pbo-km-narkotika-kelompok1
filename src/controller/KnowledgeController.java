package controller;

import java.util.Scanner;
import java.util.ArrayList;
import model.KnowledgeRepository;
import model.Putusan;
import model.StatistikPutusan;
import model.DataDummy;
import view.ConsoleView;

public class KnowledgeController {
    private KnowledgeRepository repository;
    private ConsoleView view;
    private Scanner scanner;

    public KnowledgeController(KnowledgeRepository repository, ConsoleView view) {
        this.repository = repository;
        this.view = view;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        if (repository.getSemuaData().isEmpty()) {
            DataDummy.loadData(repository);
        }

        boolean berjalan = true;
        while (berjalan) {
            int pilihan = view.tampilkanMenu(scanner);
            switch (pilihan) {
                case 1:
                    prosesTambahPutusan();
                    break;
                case 2:
                    tampilkanSemuaPutusan();
                    break;
                case 3:
                    prosesCariPutusan();
                    break;
                case 4:
                    prosesHapusPutusan();
                    break;
                case 5:
                    tampilkanStatistik();
                    break;
                case 0:
                    view.tampilkanPesan("Keluar dari aplikasi. Terima kasih.");
                    berjalan = false;
                    break;
                default:
                    view.tampilkanPesan("Pilihan menu tidak valid!");
            }
        }
    }

    private void prosesTambahPutusan() {
        try {
            Putusan putusanBaru = view.inputFormPutusan(scanner);
            repository.simpan(putusanBaru);
            view.tampilkanPesan("Data putusan baru berhasil disimpan ke repositori.");
        } catch (Exception e) {
            view.tampilkanPesan("Gagal menambah data: " + e.getMessage());
        }
    }

    private void tampilkanSemuaPutusan() {
        ArrayList<Putusan> listData = repository.getSemuaData();
        view.tampilkanDaftarPutusan(listData);
    }

    private void prosesCariPutusan() {
        System.out.print("Masukkan Kata Kunci (Nama Terdakwa): ");
        String keyword = scanner.nextLine();
        ArrayList<Putusan> hasil = repository.cariByNama(keyword);
        view.tampilkanDaftarPutusan(hasil);
    }

    private void prosesHapusPutusan() {
        System.out.print("Masukkan Nomor Perkara yang akan dihapus: ");
        String nomor = scanner.nextLine();
        boolean terhapus = repository.hapus(nomor);
        if (terhapus) {
            view.tampilkanPesan("Data putusan dengan nomor " + nomor + " berhasil dihapus.");
        } else {
            view.tampilkanPesan("Data putusan tidak ditemukan.");
        }
    }

    private void tampilkanStatistik() {
        ArrayList<Putusan> listData = repository.getSemuaData();
        StatistikPutusan statistik = new StatistikPutusan(listData);
        view.tampilkanStatistik(statistik);
    }
}