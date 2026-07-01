package util;

import model.Putusan;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PDFReader {

    public static Putusan importPutusan(File file) throws IOException {

        PDDocument document = PDDocument.load(file);

        PDFTextStripper stripper = new PDFTextStripper();

        String text = stripper.getText(document);

        document.close();

        String nomor = ambil(text, "Nomor Perkara");
        String pengadilan = ambil(text, "Pengadilan");
        String tanggal = ambil(text, "Tanggal Putusan");
        String nama = ambil(text, "Nama Terdakwa");

        int umur = Integer.parseInt(ambil(text, "Umur"));

        String jenis = ambil(text, "Jenis Narkotika");

        double berat = Double.parseDouble(
                ambil(text, "Berat Barang Bukti")
                        .replace("gram","")
                        .trim());

        String pasal = ambil(text, "Pasal");

        String peran = ambil(text, "Peran");

        int vonis = Integer.parseInt(
                ambil(text, "Vonis")
                        .replace("bulan","")
                        .trim());

        double denda = Double.parseDouble(
                ambil(text, "Denda"));

        String hakim = ambil(text, "Nama Hakim");

        return new Putusan(
                nomor,
                pengadilan,
                tanggal,
                nama,
                umur,
                jenis,
                berat,
                pasal,
                peran,
                vonis,
                denda,
                hakim
        );
    }

    private static String ambil(String text, String key){

        for(String baris : text.split("\\R")){

            if(baris.startsWith(key)){

                return baris.substring(
                        baris.indexOf(":")+1).trim();

            }

        }

        return "";
    }

}