/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

// Definis kelas Menu
public class Menu {
    private String namaMakanan;
    private double harga; 

    // Constructor Menu
    public Menu(String namaMakanan, double harga){
        this.namaMakanan = namaMakanan;
        this.harga = harga;
    }

    // Getter harga
    public double getHarga() {
        return harga;
    }

    // Getter nama makanan
    public String getNamaMakanan() {
        return namaMakanan;
    }
}