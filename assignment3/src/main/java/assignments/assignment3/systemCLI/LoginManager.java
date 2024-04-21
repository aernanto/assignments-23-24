package assignments.assignment3.systemCLI;

import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;
import assignments.assignment3.systemCLI.UserSystemCLI;

public class LoginManager {
    private AdminSystemCLI adminSystem;
    private CustomerSystemCLI customerSystem;

    public LoginManager(AdminSystemCLI adminSystem, CustomerSystemCLI customerSystem) {
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