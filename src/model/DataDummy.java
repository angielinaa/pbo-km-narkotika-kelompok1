package model;

/**
 * Menyediakan data sampel untuk demo aplikasi.
 *
 * @author Kelompok 1
 */
public class DataDummy {

    public static void loadData(KnowledgeRepository repository) {

        String[] jenis = {
                "Sabu",
                "Ganja",
                "Ekstasi",
                "Heroin",
                "Tembakau Sintetis"
        };

        String[] peran = {
                "Pemakai",
                "Pengedar",
                "Kurir",
                "Bandar"
        };

        String[] hakim = {
                "Hakim Andi",
                "Hakim Budi",
                "Hakim Sari",
                "Hakim Dewi",
                "Hakim Rudi"
        };

        for (int i = 1; i <= 50; i++) {

            Putusan putusan = new Putusan(
                    "PN-" + i + "/2025",
                    "Pengadilan Negeri Jakarta",
                    "2025-01-" + ((i % 28) + 1),
                    "Terdakwa " + i,
                    20 + (i % 25),
                    jenis[i % jenis.length],
                    1.5 + (i * 0.25),
                    "Pasal 112 UU Narkotika",
                    peran[i % peran.length],
                    1 + (i % 10),
                    1000000 + (i * 500000),
                    hakim[i % hakim.length]
            );

            repository.simpan(putusan);
        }
    }
}