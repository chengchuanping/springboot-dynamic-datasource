package io.springboot.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.springboot.demo.entity.User;
import io.springboot.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public Object list() {
		return this.userService.findAll();
	}
	
	@PostMapping
	public Object create (@RequestBody User user) {
		user.setCreateAt(LocalDateTime.now());
		
		log.info("添加用户: {}", user);
		
		this.userService.save(user);
		return user;
	}
	
	@DeleteMapping(value = "/{id}", produces = "text/plain; charset=utf-8")
	public Object delete (@PathVariable("id") Integer id) {
		this.userService.deleteById(id);
		return "删除成功";
	}
}
