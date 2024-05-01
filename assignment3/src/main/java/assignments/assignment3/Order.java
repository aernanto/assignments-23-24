/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

// Definisi kelas Order
public class Order {
    private String OrderId;
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

    // Constructor Order
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items){
        this.OrderId = orderId;
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }
    
    // Getter restaurant
    public Restaurant getRestaurant() {
        return restaurant;
    }

    // Getter finished order
    public boolean getOrderFinished(){
        return this.orderFinished;
    }

    // Setter finished order
    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

    // Getter Order ID
    public String getOrderId() {
        return OrderId;
    }

    // Getter Tanggal
    public String getTanggal() {
        return tanggal;
    }

    // Getter ongkir
    public int getOngkir() {
        return ongkir;
    }

    // Getter items
    public Menu[] getItems() {
        return items;
    }

    // Getter sorted menu
    public Menu[] getSortedMenu(){
        Menu[] menuArr = new Menu[getItems().length];
        for(int i=0; i < getItems().length;i++){
            menuArr[i] = getItems()[i];
        }
        int n = menuArr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (menuArr[j].getHarga() > menuArr[j+1].getHarga()) {
                    
                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j+1];
                    menuArr[j+1] = temp;
                }
            }
        }
        return menuArr;
    }

    // Getter total harga
    public double getTotalHarga(){
        double sum = 0;
        for(Menu menu: getItems()){
            sum += menu.getHarga();
        }
        return sum += getOngkir();
    }
}