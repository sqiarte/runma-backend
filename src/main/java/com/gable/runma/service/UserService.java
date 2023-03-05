package com.gable.runma.service;

import com.gable.runma.exception.DataIntegrityViolationException;
import com.gable.runma.exception.ResourceNotFoundException;
import com.gable.runma.model.Event;
import com.gable.runma.model.RaceType;
import com.gable.runma.model.User;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gable.runma.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService {
	@Autowired
	private UserRepository repo;

	//get User by Id
	public User getUser(Integer id) {
//		Optional<User> op = repo.findById(id);
//		User result = op.orElseThrow();
//		return result;

		User user = repo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("User with id: " + id + " does not exist"));
		return ResponseEntity.ok(user).getBody();
	}

	//crete new user
	public User createUser(User user) {
		repo.save(user);
		return user;
	}

	//Update user
//	public User updateUser(User data) {
//		User user = repo.findById(data.getId()).orElseThrow(()-> new ResourceNotFoundException("User with id: " + data.getId() + " does not exist"));
//		if (user != null) {
//			ResponseEntity.ok(user).getBody();
//			return repo.save(data);
//		} else {
//			throw new ResourceNotFoundException("The email address " + user.getEmail() + " is already taken.");
//		}
////		return ResponseEntity.ok(user).getBody();
//	}

	// update user data
	public User updateUser(User data) {
		User user = repo.findById(data.getId()).orElseThrow(() -> new ResourceNotFoundException("User with id: " + data.getId() + " does not exist"));

		// Check if the email already exists in the database
		User existingUser = repo.findByEmail(data.getEmail());
		if (existingUser != null && !existingUser.getId().equals(user.getId())) {
			throw new DataIntegrityViolationException("The email address " + data.getEmail() + " is already taken.");
		}
		ResponseEntity.ok(user).getBody();
		return repo.save(data);
	}




//	@GetMapping("{id}")
//	public ResponseEntity<Employee> getUserById(@PathVariable long id) {
//		User user = userRepository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
//		return ResponseEntity.ok(employee);
//	}

}
