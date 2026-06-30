package model;

import java.util.ArrayList;

/**
 * Repository untuk menyimpan dan mengelola data putusan.
 *
 * Berfungsi sebagai Model pada arsitektur MVC.
 */
public class KnowledgeRepository {

    // Menyimpan seluruh data putusan
    private ArrayList<Putusan> daftarPutusan;

    /**
     * Constructor
     */
    public KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    /**
     * Menyimpan data putusan baru.
     */
    public void simpan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }

    /**
     * Mengambil seluruh data putusan.
     */
    public ArrayList<Putusan> getSemuaData() {
        return daftarPutusan;
    }

    /**
     * Mencari berdasarkan nomor perkara.
     */
    public Putusan cariByNomor(String nomor) {

        for (Putusan p : daftarPutusan) {

            if (p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }

        }

        return null;
    }

    /**
     * Mencari berdasarkan nama terdakwa.
     */
    public ArrayList<Putusan> cariByNama(String nama) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getNamaTerdakwa()
                    .toLowerCase()
                    .contains(nama.toLowerCase())) {

                hasil.add(p);

            }

        }

        return hasil;
    }

    /**
     * Filter berdasarkan jenis narkotika.
     */
    public ArrayList<Putusan> filterByJenis(String jenis) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getJenisNarkotika().equalsIgnoreCase(jenis)) {

                hasil.add(p);

            }

        }

        return hasil;
    }

    /**
     * Filter berdasarkan nama pengadilan.
     */
    public ArrayList<Putusan> filterByPengadilan(String pengadilan) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {

            if (p.getPengadilan().equalsIgnoreCase(pengadilan)) {

                hasil.add(p);

            }

        }

        return hasil;
    }

    /**
     * Update data berdasarkan nomor perkara.
     */
    public boolean update(String nomor, Putusan dataBaru) {

        for (int i = 0; i < daftarPutusan.size(); i++) {

            if (daftarPutusan.get(i)
                    .getNomorPerkara()
                    .equalsIgnoreCase(nomor)) {

                daftarPutusan.set(i, dataBaru);

                return true;

            }

        }

        return false;
    }

    /**
     * Menghapus data berdasarkan nomor perkara.
     */
    public boolean hapus(String nomor) {

        Putusan p = cariByNomor(nomor);

        if (p != null) {

            daftarPutusan.remove(p);

            return true;

        }

        return false;
    }

    /**
     * Mengembalikan jumlah data.
     */
    public int getTotalData() {

        return daftarPutusan.size();

    }

}