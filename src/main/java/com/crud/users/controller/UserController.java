package com.crud.users.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.users.exception.ResourceNotFoundException;
import com.crud.users.model.User;
import com.crud.users.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getAllusers() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		User employee = userRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id : " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/users")
	public User createEmployee(@Valid @RequestBody User employee) {
		return userRepository.save(employee);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody User userDetail) throws ResourceNotFoundException {
		User user = userRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id : " + employeeId));

		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		user.setPassword(userDetail.getPassword());
		final User updatedEmployee = userRepository.save(user);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		User employee = userRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado para id : " + employeeId));

		userRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
