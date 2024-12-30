/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManajemenInputNilai;

/**
 *
 * @author ASUS
 */
public class Dosen extends User {
    private String nip;

    public Dosen(String username, String password, String nip) {
        super(username, password, "dosen");
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }
}

