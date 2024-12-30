/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManajemenInputNilai;

/**
 *
 * @author ASUS
 */
public class Mahasiswa extends User {
    private String nim;

    public Mahasiswa(String username, String password, String nim) {
        super(username, password, "mahasiswa");
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }
}

