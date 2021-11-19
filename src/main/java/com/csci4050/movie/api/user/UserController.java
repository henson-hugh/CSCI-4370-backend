package com.csci4050.movie.api.user;

import com.csci4050.movie.api.EmailSenderService;
import com.csci4050.movie.api.admin.AdminService;
import com.csci4050.movie.api.customer.CustomerDto;
import com.csci4050.movie.api.customer.CustomerService;
import com.csci4050.movie.api.model.Admin;
import com.csci4050.movie.api.model.Customer;
import com.csci4050.movie.api.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSenderService emailService;

    // Login
    @PostMapping(value = "/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> userLogin(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        String email = user.getEmail();
        String password = user.getPassword();
        Optional<User> loggingUser = userService.getUserByEmail(email);
        if (loggingUser.equals(Optional.empty())) { // check if email exists in the system
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(userDto);
        }
        User match = loggingUser.get();


        // check login

        if (passwordEncoder.matches(password, match.getPassword())) { // check password with encoded password

            // package return object for login
            Map<String, Object> map = new HashMap<String, Object>();
            Customer customer;
            String privilege;
            if (adminService.getAdminByUid(match.getUid()).isPresent()) {
                privilege = "admin";
            } else {
                privilege = "customer";
                customer = customerService.getCustomerByUserid(match.getUid()).get();
                map.put("verified", customer.isVerified());
                map.put("suspended", customer.isSuspend());
                map.put("cid", customer.getCid());
            }
            map.put("privilege", privilege);

            return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(userDto);
        }
    }

    // Register
    @PostMapping(value = "/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) throws Exception {
        User user = modelMapper.map(userDto, User.class);
        Optional<User> userCheck = userService.getUserByEmail(user.getEmail());
        // checks
        if (userCheck.isPresent()) { // check if user exists
            System.out.println("****************User with: " + user.getEmail() + " already exists.**************");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(userDto);
        } else {
            userService.saveUser(user);
            userDto.setUid(user.getUid());
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userDto);
        }
    }

    // Verify password for edit
    @PostMapping(value = "/verifyPass")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> verifyPassword(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        String password = user.getPassword();
        Optional<User> loggingUser = userService.getUserById(user.getUid());
        if (loggingUser.equals(Optional.empty())) { // check if email exists in the system
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(userDto);
        }
        User match = loggingUser.get();
        // check login

        if (passwordEncoder.matches(password, match.getPassword())) { // check password with encoded password
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(userDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(modelMapper.map(match, UserDto.class));
        }
    }

    // Update password from edit
    @PostMapping(value = "/updatePass")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<UserDto> updatePassword(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userService.updatePassword(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto);
    }

    // Send Forgot Pass Email
    @PostMapping(value = "/forgotPass")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<UserDto> sendForgotPasswordEmail(@RequestBody String email) {

        Optional<User> userEmail = userService.getUserByEmail(email);

        // check if user with email exists
        if (userEmail.isPresent()) {
            User user = userEmail.get();
            emailService.sendForgotEmail(email, user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(user, UserDto.class));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserDto());
        }
    }

    // Redirect to Reset Password
    @RequestMapping(value = "/reset", method = {RequestMethod.GET, RequestMethod.POST})
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> redirectToReset(@RequestParam("id") int uid) {

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("http://localhost:4200/password-reset")).build();
    }

    // Reset Password
    @PostMapping(value = "/resetPass")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<UserDto> resetPassword(@RequestBody UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);


        // check if user exists
        if (userService.getUserById(user.getUid()).isPresent()) {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(modelMapper.map(user, UserDto.class));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(user, UserDto.class));
        }
    }


}
