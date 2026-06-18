package view;

import java.util.Scanner;
import java.util.ArrayList;

import model.Putusan;
import model.StatistikPutusan;
import util.InputHandler;

public class ConsoleView {

    // 1. Desain Menu Utama
    public int tampilkanMenu(Scanner sc) {
        System.out.println("\n==================================================");
        System.out.println("   KMS PUTUSAN PENGADILAN NARKOTIKA");
        System.out.println("==================================================");
        System.out.println(" 1. Tambah Data Putusan Baru");
        System.out.println(" 2. Tampilkan Semua Data Putusan");
        System.out.println(" 3. Cari Data Putusan (By Nomor/Nama)");
        System.out.println(" 4. Hapus Data Putusan");
        System.out.println(" 5. Lihat Statistik Ringkas");
        System.out.println(" 0. Keluar Aplikasi");
        System.out.println("--------------------------------------------------");
        return InputHandler.validasiInt(" Pilihan Anda: ", sc);
    }

    // 2. Desain Output Pesan
    public void tampilkanPesan(String pesan) {
        System.out.println("\n[INFO] " + pesan);
    }

    // 3. Desain Form Input Minimalis
    public Putusan inputFormPutusan(Scanner sc) {
        System.out.println("\n   TAMBAH DATA PUTUSAN BARU");
        System.out.println("   ------------------------");

        String nomor = InputHandler.validasiString("   Nomor Perkara       : ", sc);
        String pengadilan = InputHandler.validasiString("   Pengadilan          : ", sc);
        String tanggal = InputHandler.validasiString("   Tanggal Putusan     : ", sc);
        String nama = InputHandler.validasiString("   Nama Terdakwa       : ", sc);
        int umur = InputHandler.validasiInt("   Umur Terdakwa       : ", sc);
        String jenis = InputHandler.validasiString("   Jenis Narkotika     : ", sc);
        double berat = InputHandler.validasiDouble("   Berat Barang (gram) : ", sc);
        String pasal = InputHandler.validasiString("   Pasal Dilanggar     : ", sc);
        String peran = InputHandler.validasiString("   Peran Terdakwa      : ", sc);
        int vonis = InputHandler.validasiInt("   Vonis Hukuman (bln) : ", sc);
        double denda = InputHandler.validasiDouble("   Vonis Denda (Rp)    : ", sc);
        String hakim = InputHandler.validasiString("   Nama Hakim          : ", sc);

        // Langsung lempar ke backend, biar Rina & Visca yang urus datanya
        return new Putusan(nomor, pengadilan, tanggal, nama, umur, jenis, berat, pasal, peran, vonis, denda, hakim);
    }

    // 4. Desain Tabel Output
    public void tampilkanDaftarPutusan(ArrayList<Putusan> list) {
        System.out.println("\n=========================================================================================");
        System.out.printf("%-25s | %-20s | %-15s | %-15s\n", "Nomor Perkara", "Nama Terdakwa", "Jenis", "Vonis (Bulan)");
        System.out.println("-----------------------------------------------------------------------------------------");

        if (list.isEmpty()) {
            System.out.println("   (Data putusan kosong)");
        } else {
            for (Putusan p : list) {
                System.out.printf("%-25s | %-20s | %-15s | %-15d\n",
                        p.getNomorPerkara(), p.getNamaTerdakwa(), p.getJenisNarkotika(), p.getVonisHukuman());
            }
        }
        System.out.println("=========================================================================================\n");
    }

    // 5. Desain Laporan Statistik
    public void tampilkanStatistik(StatistikPutusan stat) {
        System.out.println("\n========================================");
        System.out.println("          STATISTIK PUTUSAN");
        System.out.println("========================================");
        System.out.println("   Total Data          : " + stat.getTotalPutusan());
        System.out.println("   Rata-rata Vonis     : " + String.format("%.1f", stat.getRataRataVonis()) + " bulan");
        System.out.println("   Rata-rata Denda     : Rp " + String.format("%,.2f", stat.getRataRataDenda()));
        System.out.println("----------------------------------------\n");
    }
}