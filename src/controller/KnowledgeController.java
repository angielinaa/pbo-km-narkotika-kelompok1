package controller;

import model.KnowledgeRepository;
import model.Putusan;
import model.StatistikPutusan;
import model.DataDummy;
import view.GUIView;
import java.util.ArrayList;

public class KnowledgeController {
    private KnowledgeRepository repository;
    private GUIView view;

    public KnowledgeController(KnowledgeRepository repository, GUIView view) {
        this.repository = repository;
        this.view = view;
        initController();
    }

    private void initController() {
        // Load data dummy jika kosong
        if (repository.getSemuaData().isEmpty()) {
            DataDummy.loadData(repository);
        }

        // Menghubungkan klik tombol dengan fungsi (Event Listeners)
        view.getBtnTambah().addActionListener(e -> prosesTambahPutusan());
        view.getBtnCari().addActionListener(e -> prosesCariPutusan());
        view.getBtnHapus().addActionListener(e -> prosesHapusPutusan());
        view.getBtnStatistik().addActionListener(e -> tampilkanStatistik());

        // Tampilkan semua data saat pertama kali aplikasi dibuka
        tampilkanSemuaPutusan();
    }

    public void start() {
        // Menampilkan jendela GUI ke layar
        view.setVisible(true);
    }

    private void prosesTambahPutusan() {
        Putusan putusanBaru = view.showInputForm();
        if (putusanBaru != null) {
            repository.simpan(putusanBaru);
            view.tampilkanPesan("Data putusan baru berhasil disimpan!");
            tampilkanSemuaPutusan(); // Refresh tabel
        }
    }

    private void tampilkanSemuaPutusan() {
        view.tampilkanDaftarPutusan(repository.getSemuaData());
    }

    private void prosesCariPutusan() {
        String keyword = view.inputString("Masukkan Nama Terdakwa:");
        if (keyword != null && !keyword.trim().isEmpty()) {
            ArrayList<Putusan> hasil = repository.cariByNama(keyword);
            view.tampilkanDaftarPutusan(hasil);
            if (hasil.isEmpty()) {
                view.tampilkanPesan("Data tidak ditemukan.");
            }
        } else {
            tampilkanSemuaPutusan(); // Reset tabel jika pencarian dibatalkan/kosong
        }
    }

    private void prosesHapusPutusan() {
        String nomor = view.inputString("Masukkan Nomor Perkara yang akan dihapus:");
        if (nomor != null && !nomor.trim().isEmpty()) {
            boolean terhapus = repository.hapus(nomor);
            if (terhapus) {
                view.tampilkanPesan("Data berhasil dihapus.");
                tampilkanSemuaPutusan(); // Refresh tabel
            } else {
                view.tampilkanPesan("Data tidak ditemukan.");
            }
        }
    }

    private void tampilkanStatistik() {
        StatistikPutusan stat = new StatistikPutusan(repository.getSemuaData());
        String info = "STATISTIK RINGKAS\n\n"
                + "Total Data: " + stat.getTotalPutusan() + "\n"
                + "Rata-rata Vonis: " + String.format("%.1f", stat.getRataRataVonis()) + " bulan\n"
                + "Rata-rata Denda: Rp " + String.format("%,.2f", stat.getRataRataDenda());
        view.tampilkanPesan(info);
    }
}