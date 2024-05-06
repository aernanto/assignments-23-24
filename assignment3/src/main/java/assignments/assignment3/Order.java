<<<<<<< HEAD
/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

// Definisi kelas Order
public class Order {
    private String OrderId;
=======
package assignments.assignment3;

public class Order {

    private String orderId;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    private String tanggal;
    private int ongkir;
    private Restaurant restaurant;
    private boolean orderFinished;
    private Menu[] items;

<<<<<<< HEAD
    // Constructor Order
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items){
        this.OrderId = orderId;
=======
    public Order(String orderId, String tanggal, int ongkir, Restaurant resto, Menu[] items) {
        this.orderId = orderId;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
        this.tanggal = tanggal;
        this.ongkir = ongkir;
        this.restaurant = resto;
        this.orderFinished = false;
        this.items = items;
    }
<<<<<<< HEAD
    
    // Getter restaurant
=======

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public Restaurant getRestaurant() {
        return restaurant;
    }

<<<<<<< HEAD
    // Getter finished order
    public boolean getOrderFinished(){
        return this.orderFinished;
    }

    // Setter finished order
=======
    public boolean getOrderFinished() {
        return this.orderFinished;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public void setOrderFinished(boolean orderFinished) {
        this.orderFinished = orderFinished;
    }

<<<<<<< HEAD
    // Getter Order ID
    public String getOrderId() {
        return OrderId;
    }

    // Getter Tanggal
=======
    public String getOrderId() {
        return orderId;
    }

>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public String getTanggal() {
        return tanggal;
    }

<<<<<<< HEAD
    // Getter ongkir
=======
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public int getOngkir() {
        return ongkir;
    }

<<<<<<< HEAD
    // Getter items
=======
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
    public Menu[] getItems() {
        return items;
    }

<<<<<<< HEAD
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
=======
    public Menu[] getSortedMenu() {
        Menu[] menuArr = new Menu[getItems().length];
        for (int i = 0; i < getItems().length; i++) {
            menuArr[i] = getItems()[i];
        }
        int n = menuArr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (menuArr[j].getHarga() > menuArr[j + 1].getHarga()) {

                    Menu temp = menuArr[j];
                    menuArr[j] = menuArr[j + 1];
                    menuArr[j + 1] = temp;
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
                }
            }
        }
        return menuArr;
    }

<<<<<<< HEAD
    // Getter total harga
    public double getTotalHarga(){
        double sum = 0;
        for(Menu menu: getItems()){
=======
    public double getTotalHarga() {
        double sum = 0;
        for (Menu menu : getItems()) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            sum += menu.getHarga();
        }
        return sum += getOngkir();
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
