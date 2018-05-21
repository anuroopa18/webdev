package webdev.services;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;
	

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int uid) {
		 repository.deleteById(uid);
	}
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setUsername(newUser.getUsername());
			user.setLastName(newUser.getLastName());
			user.setPassword(newUser.getPassword());
			user.setRole(newUser.getRole());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/findUser/{username}")
	public User findUserByUsername(@PathVariable("username") String username) {
		Optional<User> data = repository.findUserByUsername(username);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user,HttpSession session) {
		if (findUserByUsername(user.getUsername()) == null)
		{
		   createUser(user);
		   session.setAttribute("currentUser", user);
		   return user;

		}
		
		return null;
		
	}
	
	@GetMapping("/api/findUserByCredentials/{user}")
	public User findUserByCredentails(@PathVariable("user") User user) {
		Optional<User> data = repository.findUserByCredentials(user.getUsername(),user.getPassword());
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user,HttpSession session) {
		
		User checkUser;
		if(findUserByCredentails(user) != null) {
			checkUser= findUserByUsername(user.getUsername());
			 session.setAttribute("currentUser", checkUser);
			 return checkUser;
		}
		return null;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser) {
		Optional<User> data = repository.findUserByUsername(newUser.getUsername());
		if(data.isPresent()) {
			User user = data.get();
			user.setUsername(newUser.getUsername());
			user.setPhone(newUser.getPhone());
			user.setEmail(newUser.getEmail());
			user.setRole(newUser.getRole());
			user.setDateOfBirth(newUser.getDateOfBirth());
			repository.save(user);
			return user;
		}
		return null;
	}
	
	@GetMapping("/api/findUser/profile")
	public User profile(HttpSession session) {
	   User currentUser = (User)session.getAttribute("currentUser");	
	   return currentUser;
	}
	
	@PostMapping("/api/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}


}
