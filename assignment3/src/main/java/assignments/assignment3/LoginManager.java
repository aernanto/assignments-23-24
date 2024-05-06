/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import assignments.assignment3.systemCLI.UserSystemCLI;

// Definisi Login Manager
public class LoginManager {
    private UserSystemCLI adminSystem;
    private UserSystemCLI customerSystem;

    // Constructor Login Manager
    public LoginManager(UserSystemCLI adminSystem, UserSystemCLI customerSystem) {
        this.adminSystem = adminSystem;
        this.customerSystem = customerSystem;
    }

    public UserSystemCLI getSystem(String role) {
<<<<<<< HEAD
        if (role.equalsIgnoreCase("admin")) {
            return adminSystem;
        } else if (role.equalsIgnoreCase("customer")) {
=======
        if (role.equals("Customer")) {
>>>>>>> 2de87934941926ea08452f88727b4221a5edf9d5
            return customerSystem;
        } else {
            return null;
        }

        return adminSystem;
    }
}