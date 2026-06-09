package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Menghitung statistik data putusan.
 */
public class StatistikPutusan {

    private int totalPutusan;
    private double rataRataVonis;
    private double rataRataDenda;
    private String jenisNarkotikaTerbanyak;

    public StatistikPutusan(ArrayList<Putusan> data) {

        totalPutusan = data.size();

        hitungRataRataVonis(data);
        hitungRataRataDenda(data);
        hitungJenisTerbanyak(data);
    }

    private void hitungRataRataVonis(ArrayList<Putusan> data) {

        if (data.isEmpty()) return;

        double total = 0;

        for (Putusan p : data) {
            total += p.getVonisHukuman();
        }

        rataRataVonis = total / data.size();
    }

    private void hitungRataRataDenda(ArrayList<Putusan> data) {

        if (data.isEmpty()) return;

        double total = 0;

        for (Putusan p : data) {
            total += p.getVonisDenda();
        }

        rataRataDenda = total / data.size();
    }

    private void hitungJenisTerbanyak(ArrayList<Putusan> data) {

        HashMap<String, Integer> map = new HashMap<>();

        for (Putusan p : data) {

            String jenis = p.getJenisNarkotika();

            map.put(jenis,
                    map.getOrDefault(jenis, 0) + 1);
        }

        int max = 0;

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if (entry.getValue() > max) {

                max = entry.getValue();
                jenisNarkotikaTerbanyak = entry.getKey();
            }
        }
    }

    /**
     * Menghitung distribusi jenis narkotika.
     */
    public HashMap<String, Integer> getDistribusiJenis(
            ArrayList<Putusan> data) {

        HashMap<String, Integer> distribusi = new HashMap<>();

        for (Putusan p : data) {

            String jenis = p.getJenisNarkotika();

            distribusi.put(
                    jenis,
                    distribusi.getOrDefault(jenis, 0) + 1
            );
        }

        return distribusi;
    }

    public int getTotalPutusan() {
        return totalPutusan;
    }

    public double getRataRataVonis() {
        return rataRataVonis;
    }

    public double getRataRataDenda() {
        return rataRataDenda;
    }

    public String getJenisNarkotikaTerbanyak() {
        return jenisNarkotikaTerbanyak;
    }

    @Override
    public String toString() {
        return "StatistikPutusan{" +
                "totalPutusan=" + totalPutusan +
                ", rataRataVonis=" + rataRataVonis +
                ", rataRataDenda=" + rataRataDenda +
                ", jenisNarkotikaTerbanyak='" + jenisNarkotikaTerbanyak + '\'' +
                '}';
    }
}