package model;

/**
 * Class untuk menyimpan data putusan perkara narkotika.
 *
 * @author Kelompok 1
 */
public class Putusan {

    private String nomorPerkara;
    private String pengadilan;
    private String tanggalPutusan;
    private String namaTerdakwa;
    private int umurTerdakwa;
    private String jenisNarkotika;
    private double beratBarangBukti;
    private String pasalDilanggar;
    private String peranTerdakwa;
    private int vonisHukuman;
    private double vonisDenda;
    private String namaHakim;

    private static int jumlahDibuat = 0;

    public Putusan() {
        jumlahDibuat++;
    }

    public Putusan(String nomorPerkara, String pengadilan,
                   String tanggalPutusan, String namaTerdakwa,
                   int umurTerdakwa, String jenisNarkotika,
                   double beratBarangBukti, String pasalDilanggar,
                   String peranTerdakwa, int vonisHukuman,
                   double vonisDenda, String namaHakim) {

        setNomorPerkara(nomorPerkara);
        setPengadilan(pengadilan);
        setTanggalPutusan(tanggalPutusan);
        setNamaTerdakwa(namaTerdakwa);
        setUmurTerdakwa(umurTerdakwa);
        setJenisNarkotika(jenisNarkotika);
        setBeratBarangBukti(beratBarangBukti);
        setPasalDilanggar(pasalDilanggar);
        setPeranTerdakwa(peranTerdakwa);
        setVonisHukuman(vonisHukuman);
        setVonisDenda(vonisDenda);
        setNamaHakim(namaHakim);

        jumlahDibuat++;
    }

    public String getNomorPerkara() {
        return nomorPerkara;
    }

    public void setNomorPerkara(String nomorPerkara) {
        if (nomorPerkara != null && !nomorPerkara.isBlank()) {
            this.nomorPerkara = nomorPerkara;
        }
    }

    public String getPengadilan() {
        return pengadilan;
    }

    public void setPengadilan(String pengadilan) {
        if (pengadilan != null && !pengadilan.isBlank()) {
            this.pengadilan = pengadilan;
        }
    }

    public String getTanggalPutusan() {
        return tanggalPutusan;
    }

    public void setTanggalPutusan(String tanggalPutusan) {
        this.tanggalPutusan = tanggalPutusan;
    }

    public String getNamaTerdakwa() {
        return namaTerdakwa;
    }

    public void setNamaTerdakwa(String namaTerdakwa) {
        if (namaTerdakwa != null && !namaTerdakwa.isBlank()) {
            this.namaTerdakwa = namaTerdakwa;
        }
    }

    public int getUmurTerdakwa() {
        return umurTerdakwa;
    }

    public void setUmurTerdakwa(int umurTerdakwa) {
        if (umurTerdakwa > 0) {
            this.umurTerdakwa = umurTerdakwa;
        }
    }

    public String getJenisNarkotika() {
        return jenisNarkotika;
    }

    public void setJenisNarkotika(String jenisNarkotika) {
        this.jenisNarkotika = jenisNarkotika;
    }

    public double getBeratBarangBukti() {
        return beratBarangBukti;
    }

    public void setBeratBarangBukti(double beratBarangBukti) {
        if (beratBarangBukti >= 0) {
            this.beratBarangBukti = beratBarangBukti;
        }
    }

    public String getPasalDilanggar() {
        return pasalDilanggar;
    }

    public void setPasalDilanggar(String pasalDilanggar) {
        this.pasalDilanggar = pasalDilanggar;
    }

    public String getPeranTerdakwa() {
        return peranTerdakwa;
    }

    public void setPeranTerdakwa(String peranTerdakwa) {
        this.peranTerdakwa = peranTerdakwa;
    }

    public int getVonisHukuman() {
        return vonisHukuman;
    }

    public void setVonisHukuman(int vonisHukuman) {
        if (vonisHukuman >= 0) {
            this.vonisHukuman = vonisHukuman;
        }
    }

    public double getVonisDenda() {
        return vonisDenda;
    }

    public void setVonisDenda(double vonisDenda) {
        if (vonisDenda >= 0) {
            this.vonisDenda = vonisDenda;
        }
    }

    public String getNamaHakim() {
        return namaHakim;
    }

    public void setNamaHakim(String namaHakim) {
        this.namaHakim = namaHakim;
    }

    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

    public void tampilkan() {
        System.out.println(nomorPerkara + " - " + namaTerdakwa);
    }

    public void tampilkan(boolean detail) {
        if (detail) {
            System.out.println(this);
        } else {
            tampilkan();
        }
    }

    @Override
    public String toString() {
        return "Putusan{" +
                "nomorPerkara='" + nomorPerkara + '\'' +
                ", namaTerdakwa='" + namaTerdakwa + '\'' +
                ", jenisNarkotika='" + jenisNarkotika + '\'' +
                ", vonisHukuman=" + vonisHukuman +
                '}';
    }
}