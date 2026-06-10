package util;

import java.util.Scanner;

public class InputHandler {

    public static String validasiString(String label, Scanner sc) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("   [Error] Input tidak boleh kosong! Silakan coba lagi.");
        }
    }

    public static int validasiInt(String label, Scanner sc) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();
            try {
                int nilai = Integer.parseInt(input);
                if (nilai < 0) {
                    System.out.println("   [Error] Angka tidak boleh bernilai negatif! Silakan coba lagi.");
                    continue;
                }
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("   [Error] Input wajib berupa angka bulat murni! Silakan coba lagi.");
            }
        }
    }

    public static double validasiDouble(String label, Scanner sc) {
        while (true) {
            System.out.print(label);
            String input = sc.nextLine();
            try {
                double nilai = Double.parseDouble(input);
                if (nilai < 0) {
                    System.out.println("   [Error] Angka tidak boleh bernilai negatif! Silakan coba lagi.");
                    continue;
                }
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("   [Error] Input wajib berupa angka desimal murni! Silakan coba lagi.");
            }
        }
    }
}