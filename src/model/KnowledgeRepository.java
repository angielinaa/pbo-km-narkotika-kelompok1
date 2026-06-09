package model;

import java.util.ArrayList;

/**
 * Repository penyimpanan data putusan.
 */
public class KnowledgeRepository {

    private ArrayList<Putusan> daftarPutusan;

    public KnowledgeRepository() {
        daftarPutusan = new ArrayList<>();
    }

    public void simpan(Putusan putusan) {
        daftarPutusan.add(putusan);
    }

    public ArrayList<Putusan> getSemuaData() {
        return daftarPutusan;
    }

    public Putusan cariByNomor(String nomor) {
        for (Putusan p : daftarPutusan) {
            if (p.getNomorPerkara().equalsIgnoreCase(nomor)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Putusan> cariByNama(String nama) {
        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getNamaTerdakwa().toLowerCase()
                    .contains(nama.toLowerCase())) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    public ArrayList<Putusan> filterByJenis(String jenis) {

        ArrayList<Putusan> hasil = new ArrayList<>();

        for (Putusan p : daftarPutusan) {
            if (p.getJenisNarkotika()
                    .equalsIgnoreCase(jenis)) {
                hasil.add(p);
            }
        }

        return hasil;
    }

    public boolean hapus(String nomor) {

        Putusan p = cariByNomor(nomor);

        if (p != null) {
            daftarPutusan.remove(p);
            return true;
        }

        return false;
    }

    public int getTotalData() {
        return daftarPutusan.size();
    }
}