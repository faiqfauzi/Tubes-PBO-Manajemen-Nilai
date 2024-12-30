/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManajemenInputNilai;

/**
 *
 * @author ASUS
 */
import java.sql.*;


public class UserDatabase {
    private Connection con;

    public UserDatabase() {
        try {
            con = DatabaseConnection.getConnection(); // Menggunakan koneksi database yang sudah ada
        } catch (SQLException e) {
            e.printStackTrace(); // Menangani error jika koneksi gagal
        }
    }

    public User authenticate(String username, String password) {
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM pengguna WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                String role = rs.getString("role");
                if (role.equals("mahasiswa")) {
                    return new Mahasiswa(username, password, rs.getString("nim"));
                } else if (role.equals("dosen")) {
                    return new Dosen(username, password, rs.getString("id_dosen"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // jika login gagal
    }
    public boolean addUser(User newUser) {
        try {
            String sql = "INSERT INTO pengguna (username, password, role, nim, nip) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, newUser.getUsername());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser instanceof Mahasiswa ? "mahasiswa" : "dosen");
            
            // Tentukan nilai nim atau nip berdasarkan tipe pengguna
            if (newUser instanceof Mahasiswa) {
                pstmt.setString(4, ((Mahasiswa) newUser).getNim());
                pstmt.setNull(5, java.sql.Types.NULL);
            } else if (newUser instanceof Dosen) {
                pstmt.setNull(4, java.sql.Types.NULL);
                pstmt.setString(5, ((Dosen) newUser).getNip());
            }
            
            int result = pstmt.executeUpdate();
            return result > 0; // Mengembalikan true jika berhasil menambah pengguna
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

