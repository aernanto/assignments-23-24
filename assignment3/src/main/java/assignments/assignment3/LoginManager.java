/*  Nama    : Aimee Callista Ferlintera Prudence Ernanto
    NPM     : 2306165963
    Kelas   : DDP 2-B
 */

package assignments.assignment3;

import assignments.assignment3.systemCLI.UserSystemCLI;

public class LoginManager {
    private UserSystemCLI adminSystem;
    private UserSystemCLI customerSystem;

    public LoginManager(UserSystemCLI adminSystem, UserSystemCLI customerSystem) {
        this.adminSystem = adminSystem;
        this.customerSystem = customerSystem;
    }

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