package pl.coderslab.charity.admin;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.Role;
import pl.coderslab.charity.user.RoleService;
import pl.coderslab.charity.user.User;

import java.util.List;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final RoleService roleService;

    public AdminService(AdminRepository adminRepository,
                        RoleService roleService) {
        this.adminRepository = adminRepository;
        this.roleService = roleService;
    }

    public List<User> getAllAdmins() {
        Role adminRole = roleService.getRoleByRole("ROLE_ADMIN");
        return adminRepository.getAllByRolesContaining(adminRole);
    }
}
