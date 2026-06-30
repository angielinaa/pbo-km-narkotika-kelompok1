package model;

/**
 * Class Putusan merepresentasikan satu data putusan pengadilan narkotika.
 *
 * Menerapkan:
 * - Encapsulation (field private)
 * - Constructor Overloading
 * - Comparable untuk sorting
 * - Static field jumlahDibuat
 *
 * @author Kelompok
 */
public class Putusan implements Comparable<Putusan> {

    /*==========================
      STATIC FIELD
    ==========================*/
    private static int jumlahDibuat = 0;

    /*==========================
      ATTRIBUTES
    ==========================*/
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

    /*==========================
      CONSTRUCTOR KOSONG
    ==========================*/
    public Putusan() {
        jumlahDibuat++;
    }

    /*==========================
      CONSTRUCTOR BERPARAMETER
    ==========================*/
    public Putusan(String nomorPerkara,
                   String pengadilan,
                   String tanggalPutusan,
                   String namaTerdakwa,
                   int umurTerdakwa,
                   String jenisNarkotika,
                   double beratBarangBukti,
                   String pasalDilanggar,
                   String peranTerdakwa,
                   int vonisHukuman,
                   double vonisDenda,
                   String namaHakim) {

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

    /*==========================
      GETTER
    ==========================*/

    public String getNomorPerkara() {
        return nomorPerkara;
    }

    public String getPengadilan() {
        return pengadilan;
    }

    public String getTanggalPutusan() {
        return tanggalPutusan;
    }

    public String getNamaTerdakwa() {
        return namaTerdakwa;
    }

    public int getUmurTerdakwa() {
        return umurTerdakwa;
    }

    public String getJenisNarkotika() {
        return jenisNarkotika;
    }

    public double getBeratBarangBukti() {
        return beratBarangBukti;
    }

    public String getPasalDilanggar() {
        return pasalDilanggar;
    }

    public String getPeranTerdakwa() {
        return peranTerdakwa;
    }

    public int getVonisHukuman() {
        return vonisHukuman;
    }

    public double getVonisDenda() {
        return vonisDenda;
    }

    public String getNamaHakim() {
        return namaHakim;
    }

    public static int getJumlahDibuat() {
        return jumlahDibuat;
    }

    /*==========================
      SETTER + VALIDASI
    ==========================*/

    public void setNomorPerkara(String nomorPerkara) {
        if (nomorPerkara != null && !nomorPerkara.trim().isEmpty())
            this.nomorPerkara = nomorPerkara;
    }

    public void setPengadilan(String pengadilan) {
        if (pengadilan != null && !pengadilan.trim().isEmpty())
            this.pengadilan = pengadilan;
    }

    public void setTanggalPutusan(String tanggalPutusan) {
        if (tanggalPutusan != null && !tanggalPutusan.trim().isEmpty())
            this.tanggalPutusan = tanggalPutusan;
    }

    public void setNamaTerdakwa(String namaTerdakwa) {
        if (namaTerdakwa != null && !namaTerdakwa.trim().isEmpty())
            this.namaTerdakwa = namaTerdakwa;
    }

    public void setUmurTerdakwa(int umurTerdakwa) {
        if (umurTerdakwa >= 0)
            this.umurTerdakwa = umurTerdakwa;
    }

    public void setJenisNarkotika(String jenisNarkotika) {
        if (jenisNarkotika != null && !jenisNarkotika.trim().isEmpty())
            this.jenisNarkotika = jenisNarkotika;
    }

    public void setBeratBarangBukti(double beratBarangBukti) {
        if (beratBarangBukti >= 0)
            this.beratBarangBukti = beratBarangBukti;
    }

    public void setPasalDilanggar(String pasalDilanggar) {
        if (pasalDilanggar != null && !pasalDilanggar.trim().isEmpty())
            this.pasalDilanggar = pasalDilanggar;
    }

    public void setPeranTerdakwa(String peranTerdakwa) {
        if (peranTerdakwa != null && !peranTerdakwa.trim().isEmpty())
            this.peranTerdakwa = peranTerdakwa;
    }

    public void setVonisHukuman(int vonisHukuman) {
        if (vonisHukuman >= 0)
            this.vonisHukuman = vonisHukuman;
    }

    public void setVonisDenda(double vonisDenda) {
        if (vonisDenda >= 0)
            this.vonisDenda = vonisDenda;
    }

    public void setNamaHakim(String namaHakim) {
        if (namaHakim != null && !namaHakim.trim().isEmpty())
            this.namaHakim = namaHakim;
    }

    /*==========================
      SORTING
    ==========================*/

    @Override
    public int compareTo(Putusan other) {
        return Integer.compare(other.vonisHukuman, this.vonisHukuman);
    }

    /*==========================
      TOSTRING
    ==========================*/

    @Override
    public String toString() {
        return "Putusan{" +
                "nomorPerkara='" + nomorPerkara + '\'' +
                ", pengadilan='" + pengadilan + '\'' +
                ", tanggalPutusan='" + tanggalPutusan + '\'' +
                ", namaTerdakwa='" + namaTerdakwa + '\'' +
                ", umurTerdakwa=" + umurTerdakwa +
                ", jenisNarkotika='" + jenisNarkotika + '\'' +
                ", beratBarangBukti=" + beratBarangBukti +
                ", pasalDilanggar='" + pasalDilanggar + '\'' +
                ", peranTerdakwa='" + peranTerdakwa + '\'' +
                ", vonisHukuman=" + vonisHukuman +
                ", vonisDenda=" + vonisDenda +
                ", namaHakim='" + namaHakim + '\'' +
                '}';
    }
}