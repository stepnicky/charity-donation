package pl.coderslab.charity.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    private static final String PASS_LENGTH = ".{8,}";
    private static final String PASS_UPPERCASE = "[A-Z]+";
    private static final String PASS_LOWERCASE = "[a-z]+";
    private static final String PASS_DIGIT = "\\d+";
    private static final String PASS_SPECIAL = "\\W+";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final PasswordTokenRepository passwordTokenRepository;
    private final ActivationTokenRepository activationTokenRepository;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       RoleService roleService,
                       PasswordTokenRepository passwordTokenRepository,
                       ActivationTokenRepository activationTokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.passwordTokenRepository = passwordTokenRepository;
        this.activationTokenRepository = activationTokenRepository;
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

    public void createPasswordResetToken(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(user, token);
        passwordTokenRepository.save(myToken);
    }

    public boolean validatePasswordResetToken(String token) {
        PasswordResetToken passToken = passwordTokenRepository.getByToken(token);
        return passToken != null;
    }

    public void createAccountActivationToken(User user, String token) {
        AccountActivationToken activationToken = new AccountActivationToken(user, token);
        activationTokenRepository.save(activationToken);
    }

    public boolean validateAccountActivationToken(String token) {
        return activationTokenRepository.getByToken(token) != null;
    }

    public boolean validatePassword(String password) {

        Pattern lengthPattern = Pattern.compile(PASS_LENGTH);
        Pattern uppercasePattern = Pattern.compile(PASS_UPPERCASE);
        Pattern lowercasePattern = Pattern.compile(PASS_LOWERCASE);
        Pattern digitPattern = Pattern.compile(PASS_DIGIT);
        Pattern specialPattern = Pattern.compile(PASS_SPECIAL);

        boolean lengthMatch = lengthPattern.matcher(password).matches();
        boolean uppercaseMatch = uppercasePattern.matcher(password).find();
        boolean lowercaseMatch = lowercasePattern.matcher(password).find();
        boolean digitMatch = digitPattern.matcher(password).find();
        boolean specialMatch = specialPattern.matcher(password).find();

        return lengthMatch && uppercaseMatch && lowercaseMatch && digitMatch && specialMatch;
    }

}
