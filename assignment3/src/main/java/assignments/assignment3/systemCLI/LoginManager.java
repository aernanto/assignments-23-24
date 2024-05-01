/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3.systemCLI;

public class LoginManager {
    private AdminSystemCLI adminSystem;
    private UserSystemCLI customerSystem;

    // Constructor Login Manager
    public LoginManager(AdminSystemCLI adminSystem, UserSystemCLI customerSystem) {
        this.adminSystem = adminSystem;
        this.customerSystem = customerSystem;
    }

    // Role
    public UserSystemCLI getSystem(String role) {
        if (role.equalsIgnoreCase("admin")) {
            return adminSystem;
        } else if (role.equalsIgnoreCase("customer")) {
            return customerSystem;
        } else {
            return null;
        }
    }
}