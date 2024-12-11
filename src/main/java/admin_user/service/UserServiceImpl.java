package admin_user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import admin_user.dto.UserDto;
import admin_user.model.User;
import admin_user.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(UserDto userDto) {
		User user = new User(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()) , userDto.getRole(), userDto.getFullname());
		return userRepository.save(user);
	}

	 @Override
	    public List<User> getUsersByRole(String role) {
	        return userRepository.findByRole(role);  // Fetch users by role from the database
	    }

	@Override
	public User findByFullname(String fullname) {
		return null;
	}

	@Override
	public User findByEmail(String email) {
		return null;
	}
	 
}
