package admin_user.service;

import java.util.List;
import admin_user.dto.UserDto;
import admin_user.model.User;

public interface UserService {

    User save(UserDto userDto);  // Save a new user
    
    List<User> getUsersByRole(String role);  // Get users by their role

    User findByFullname(String fullname);  // Find a user by their fullname
    
    User findByEmail(String email);  // Find a user by their email

 
}
