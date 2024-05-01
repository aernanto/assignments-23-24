/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

// Class Menu, memuat namaMakanan dan harga
public class Menu {
    private String namaMakanan;
    private double harga;

    public Menu(String namaMakanan, double harga) {
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public double getHarga() {
        return harga;
    }
}