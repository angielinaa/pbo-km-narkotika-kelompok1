package model;

public class Putusan implements Comparable<Putusan> {
    private String nomorPerkara, pengadilan, tanggalPutusan, namaTerdakwa, jenisNarkotika, pasalDilanggar, peranTerdakwa, namaHakim;
    private int umurTerdakwa, vonisHukuman;
    private double beratBarangBukti, vonisDenda;

    public Putusan(String nomorPerkara, String pengadilan, String tanggalPutusan, String namaTerdakwa, int umurTerdakwa, String jenisNarkotika, double beratBarangBukti, String pasalDilanggar, String peranTerdakwa, int vonisHukuman, double vonisDenda, String namaHakim) {
        this.nomorPerkara = nomorPerkara; this.pengadilan = pengadilan; this.tanggalPutusan = tanggalPutusan;
        this.namaTerdakwa = namaTerdakwa; this.umurTerdakwa = umurTerdakwa; this.jenisNarkotika = jenisNarkotika;
        this.beratBarangBukti = beratBarangBukti; this.pasalDilanggar = pasalDilanggar; this.peranTerdakwa = peranTerdakwa;
        this.vonisHukuman = vonisHukuman; this.vonisDenda = vonisDenda; this.namaHakim = namaHakim;
    }

    @Override
    public int compareTo(Putusan other) {
        // Bonus Sorting: Mengurutkan dari vonis tertinggi ke terendah
        return Integer.compare(other.vonisHukuman, this.vonisHukuman);
    }

    // Getters wajib agar TableView bisa menampilkan data
    public String getNomorPerkara() { return nomorPerkara; }
    public String getNamaTerdakwa() { return namaTerdakwa; }
    public String getJenisNarkotika() { return jenisNarkotika; }
    public int getVonisHukuman() { return vonisHukuman; }
    public double getVonisDenda() { return vonisDenda; }
}