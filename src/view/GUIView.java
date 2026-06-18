package view;

import model.Putusan;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;

// Kita pakai library border khusus FlatLaf untuk padding yang gampang
import com.formdev.flatlaf.FlatClientProperties;

public class GUIView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnTambah, btnCari, btnHapus, btnStatistik;

    // Warna Aksen Biru Modern
    private final Color COLOR_ACCENT = new Color(0, 122, 255);
    private final Color COLOR_BG = new Color(250, 251, 252);

    public GUIView() {
        setTitle("KMS Putusan Narkotika v2.0 - Modern Edition");
        setSize(1000, 650); // Sedikit lebih besar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_BG);

        // Gunakan BorderLayout dengan gap yang pas
        setLayout(new BorderLayout(0, 0));

        setupHeader();
        setupTableArea();
        setupToolbar();
    }

    private void setupHeader() {
        JPanel pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(Color.WHITE);
        // Padding: Top, Left, Bottom, Right
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        // Garis tipis di bawah header
        pnlHeader.putClientProperty(FlatClientProperties.STYLE, "border: 0,0,1,0,solid,#E0E0E0");

        JLabel lblTitle = new JLabel("Knowledge Management System");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(50, 50, 50));

        JLabel lblSub = new JLabel("Repositori Putusan Pengadilan Perkara Narkotika");
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(120, 120, 120));

        JPanel pnlText = new JPanel(new GridLayout(2, 1, 0, 2));
        pnlText.setBackground(Color.WHITE);
        pnlText.add(lblTitle);
        pnlText.add(lblSub);

        pnlHeader.add(pnlText, BorderLayout.WEST);

        // Bisa tambah logo di BorderLayout.EAST jika punya

        add(pnlHeader, BorderLayout.NORTH);
    }

    private void setupTableArea() {
        JPanel pnlTable = new JPanel(new BorderLayout());
        pnlTable.setBackground(COLOR_BG);
        pnlTable.setBorder(BorderFactory.createEmptyBorder(25, 25, 10, 25));

        String[] kolom = {"NOMOR PERKARA", "NAMA TERDAKWA", "JENIS NARKOTIKA", "VONIS HUKUMAN"};

        // Tabel Model Non-Editable secara default
        tableModel = new DefaultTableModel(kolom, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);

        // --- STYLING TABEL FLATLAF (Ini kunci modernnya) ---
        table.setRowHeight(40); // Baris tinggi dan lega
        table.setShowVerticalLines(false); // Hapus garis vertikal ala excel 97
        table.setGridColor(new Color(230, 230, 230));
        table.setSelectionBackground(new Color(232, 242, 255)); // Warna seleksi biru muda lembut
        table.setSelectionForeground(Color.BLACK);
        table.setIntercellSpacing(new Dimension(0, 0)); // Rapatkan cell

        // Styling Header Tabel
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 12));
        header.setBackground(Color.WHITE);
        header.setForeground(new Color(100, 100, 100));
        header.setReorderingAllowed(false);
        // Garis bawah header
        header.putClientProperty(FlatClientProperties.STYLE, "separatorColor: #E0E0E0");

        // Perataan teks di dalam cell
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Kolom Vonis di tengah, sisanya kiri
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        pnlTable.add(scrollPane, BorderLayout.CENTER);
        add(pnlTable, BorderLayout.CENTER);
    }

    private void setupToolbar() {
        // Toolbar di bagian bawah, tapi didesain seperti action bar atas
        JPanel pnlAction = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnlAction.setBackground(COLOR_BG);
        pnlAction.setBorder(BorderFactory.createEmptyBorder(10, 25, 25, 25));

        btnTambah = createModernButton("Tambah Data Baru", true);
        btnCari = createModernButton("Cari", false);
        btnHapus = createModernButton("Hapus", false);
        btnStatistik = createModernButton("Lihat Statistik", false);

        pnlAction.add(btnTambah);
        // Beri jarak sedikit
        pnlAction.add(Box.createHorizontalStrConstraint(15));
        pnlAction.add(btnCari);
        pnlAction.add(btnHapus);
        pnlAction.add(Box.createHorizontalGlue()); // Dorong statistik ke kanan
        pnlAction.add(btnStatistik);

        add(pnlAction, BorderLayout.SOUTH);
    }

    // Helper untuk bikin tombol style modern
    private JButton createModernButton(String text, boolean isPrimary) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (isPrimary) {
            // Tombol Utama (Biru)
            btn.setBackground(COLOR_ACCENT);
            btn.setForeground(Color.WHITE);
            // Pakai styling FlatLaf untuk rounded corner
            btn.putClientProperty(FlatClientProperties.STYLE, "borderColor: #007AFF; hoverBackground: #0062CC; arc: 8");
        } else {
            // Tombol Sekunder (Putih/Abu)
            btn.setBackground(Color.WHITE);
            btn.setForeground(new Color(60, 60, 60));
            btn.putClientProperty(FlatClientProperties.STYLE, "borderColor: #C0C0C0; hoverBackground: #F0F0F0; arc: 8");
        }

        // Margin dalam tombol (Top, Left, Bottom, Right)
        btn.setMargin(new Insets(8, 16, 8, 16));

        return btn;
    }

    // --- Getter, Tabel Fill, dan Dialog Form tetap sama logikanya ---
    // --- (Hanya styling dialog input yang otomatis ikut FlatLaf) ---

    public JButton getBtnTambah() { return btnTambah; }
    public JButton getBtnCari() { return btnCari; }
    public JButton getBtnHapus() { return btnHapus; }
    public JButton getBtnStatistik() { return btnStatistik; }

    public void tampilkanDaftarPutusan(ArrayList<Putusan> list) {
        tableModel.setRowCount(0);
        for (Putusan p : list) {
            // Memformat Vonis agar lebih rapi (misal: "12 Bulan")
            String vonisStr = p.getVonisHukuman() + " Bulan";
            tableModel.addRow(new Object[]{
                    p.getNomorPerkara(), p.getNamaTerdakwa().toUpperCase(), p.getJenisNarkotika(), vonisStr
            });
        }
    }

    public void tampilkanPesan(String pesan) {
        // Dialog otomatis pake style FlatLaf yang modern
        JOptionPane.showMessageDialog(this, pesan, "Informasi", JOptionPane.INFORMATION_MESSAGE);
    }

    public String inputString(String pesan) {
        return JOptionPane.showInputDialog(this, pesan);
    }

    public Putusan showInputForm() {
        // Membuat Panel Input yang Rapi dengan GridBagLayout (lebih presisi)
        JPanel pnlInputs = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Gap antar input

        JTextField txtNomor = new JTextField(20);
        JTextField txtPengadilan = new JTextField(20);
        JTextField txtTanggal = new JTextField(20); // Harusnya DatePicker, tapi samakan dulu
        JTextField txtNama = new JTextField(20);
        JSpinner spinUmur = new JSpinner(new SpinnerNumberModel(20, 1, 100, 1)); // Pakai spinner untuk umur
        JTextField txtJenis = new JTextField(20);
        JTextField txtBerat = new JTextField(20);
        JTextField txtPasal = new JTextField(20);
        JComboBox<String> cbPeran = new JComboBox<>(new String[]{"Pemakai", "Kurir", "Pengedar", "Bandar"});
        JSpinner spinVonis = new JSpinner(new SpinnerNumberModel(1, 0, 1200, 1)); // Vonis dalam bulan
        JTextField txtDenda = new JTextField(20);
        JTextField txtHakim = new JTextField(20);

        // Helper untuk tambah row ke panel input
        int row = 0;
        addInputRow(pnlInputs, gbc, row++, "Nomor Perkara:", txtNomor);
        addInputRow(pnlInputs, gbc, row++, "Pengadilan:", txtPengadilan);
        addInputRow(pnlInputs, gbc, row++, "Tanggal Putusan (YYYY-MM-DD):", txtTanggal);
        addInputRow(pnlInputs, gbc, row++, "Nama Terdakwa:", txtNama);
        addInputRow(pnlInputs, gbc, row++, "Umur Terdakwa:", spinUmur);
        addInputRow(pnlInputs, gbc, row++, "Jenis Narkotika:", txtJenis);
        addInputRow(pnlInputs, gbc, row++, "Berat (gram):", txtBerat);
        addInputRow(pnlInputs, gbc, row++, "Pasal Dilanggar:", txtPasal);
        addInputRow(pnlInputs, gbc, row++, "Peran Terdakwa:", cbPeran);
        addInputRow(pnlInputs, gbc, row++, "Vonis Hukuman (Bulan):", spinVonis);
        addInputRow(pnlInputs, gbc, row++, "Vonis Denda (Rp):", txtDenda);
        addInputRow(pnlInputs, gbc, row++, "Nama Hakim:", txtHakim);

        int option = JOptionPane.showConfirmDialog(this, pnlInputs, "Tambah Data Putusan Baru", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            try {
                // Ambil data dari input, handle parser angka
                return new Putusan(
                        txtNomor.getText().trim(),
                        txtPengadilan.getText().trim(),
                        txtTanggal.getText().trim(),
                        txtNama.getText().trim(),
                        (Integer) spinUmur.getValue(),
                        txtJenis.getText().trim(),
                        Double.parseDouble(txtBerat.getText().trim().replace(",", ".")), // Handle koma/titik
                        txtPasal.getText().trim(),
                        (String) cbPeran.getSelectedItem(),
                        (Integer) spinVonis.getValue(),
                        Double.parseDouble(txtDenda.getText().trim().replace(",", ".")),
                        txtHakim.getText().trim()
                );
            } catch (Exception e) {
                tampilkanPesan("Gagal! Pastikan format input benar (Berat dan Denda harus angka).");
            }
        }
        return null;
    }

    private void addInputRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JComponent inputComp) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.gridy = row; gbc.weightx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(inputComp, gbc);
    }
}