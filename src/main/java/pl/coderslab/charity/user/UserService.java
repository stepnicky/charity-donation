package pl.coderslab.charity.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteAdminRights(Long adminId) {
        User user = getUserById(adminId);
        List<Role> roles = user.getRoles();
        roles.removeIf(r -> r.getRole().equals("ROLE_ADMIN"));
        user.setRoles(roles);
        updateUser(user);
    }

    public void addAdminRights(Long userId) {
        User user = getUserById(userId);
        Role adminRole = roleService.getRoleByRole("ROLE_ADMIN");
        List<Role> roles = user.getRoles();
        roles.add(adminRole);
        user.setRoles(roles);
        updateUser(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
