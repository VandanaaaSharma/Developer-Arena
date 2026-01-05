import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

/*
=========================================================
 SMART LIBRARY DESKTOP APPLICATION
 Week 8 – Capstone Project
 Single-file Swing + JDBC Application
=========================================================
*/

public class SmartLibraryDesktop extends JFrame {

    // ---------------- DATABASE ----------------
    private static final String DB_URL = "jdbc:sqlite:smart_library.db";

    // ---------------- UI COMPONENTS ----------------
    private JTable bookTable;
    private DefaultTableModel bookModel;
    private JTextField txtTitle, txtAuthor, txtSearch;

    // ---------------- CONSTRUCTOR ----------------
    public SmartLibraryDesktop() {
        setTitle("Smart Library Management System");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initDatabase();
        initUI();
        loadBooks();
    }

    // ---------------- DATABASE SETUP ----------------
    private void initDatabase() {
        try (Connection con = DriverManager.getConnection(DB_URL);
             Statement st = con.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS books (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT,
                    author TEXT,
                    available INTEGER
                )
            """);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "DB Error: " + e.getMessage());
        }
    }

    // ---------------- UI SETUP ----------------
    private void initUI() {
        setLayout(new BorderLayout());

        // Top Panel
        JPanel top = new JPanel();
        txtTitle = new JTextField(10);
        txtAuthor = new JTextField(10);
        JButton btnAdd = new JButton("Add Book");

        top.add(new JLabel("Title"));
        top.add(txtTitle);
        top.add(new JLabel("Author"));
        top.add(txtAuthor);
        top.add(btnAdd);

        add(top, BorderLayout.NORTH);

        // Table
        bookModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Available"}, 0);
        bookTable = new JTable(bookModel);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        // Bottom Panel
        JPanel bottom = new JPanel();
        txtSearch = new JTextField(15);
        JButton btnSearch = new JButton("Search");
        JButton btnBorrow = new JButton("Borrow");
        JButton btnReturn = new JButton("Return");

        bottom.add(new JLabel("Search"));
        bottom.add(txtSearch);
        bottom.add(btnSearch);
        bottom.add(btnBorrow);
        bottom.add(btnReturn);

        add(bottom, BorderLayout.SOUTH);

        // ---------------- BUTTON ACTIONS ----------------
        btnAdd.addActionListener(e -> addBook());
        btnSearch.addActionListener(e -> searchBooks());
        btnBorrow.addActionListener(e -> updateAvailability(false));
        btnReturn.addActionListener(e -> updateAvailability(true));
    }

    // ---------------- CRUD OPERATIONS ----------------
    private void addBook() {
        if (txtTitle.getText().isEmpty()) return;

        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(
                     "INSERT INTO books(title, author, available) VALUES (?, ?, 1)")) {

            ps.setString(1, txtTitle.getText());
            ps.setString(2, txtAuthor.getText());
            ps.executeUpdate();

            txtTitle.setText("");
            txtAuthor.setText("");
            loadBooks();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void loadBooks() {
        bookModel.setRowCount(0);
        try (Connection con = DriverManager.getConnection(DB_URL);
             ResultSet rs = con.createStatement().executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                bookModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1 ? "Yes" : "No"
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void searchBooks() {
        bookModel.setRowCount(0);
        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?")) {

            String key = "%" + txtSearch.getText() + "%";
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("available") == 1 ? "Yes" : "No"
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void updateAvailability(boolean available) {
        int row = bookTable.getSelectedRow();
        if (row == -1) return;

        int id = (int) bookModel.getValueAt(row, 0);

        try (Connection con = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = con.prepareStatement(
                     "UPDATE books SET available=? WHERE id=?")) {

            ps.setInt(1, available ? 1 : 0);
            ps.setInt(2, id);
            ps.executeUpdate();
            loadBooks();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartLibraryDesktop().setVisible(true));
    }
}

