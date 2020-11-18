package com.example.todocalendar.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.todocalendar.domain.SignupForm;
import com.example.todocalendar.domain.UserEntity;
import com.example.todocalendar.domain.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repository;

	//show registration form
	@GetMapping("/registration")
	public String showRegistrationForm(Model model) {
		model.addAttribute("signupform", new SignupForm());
		return "registration";
	}

	//save new user
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String save(@Valid @ModelAttribute("signupform") SignupForm signupForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) { // validation errors
			if (signupForm.getPassword().equals(signupForm.getPasswordmatch())) { // check password match
				String pwd = signupForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);

				UserEntity newUser = new UserEntity();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signupForm.getUsername());
				newUser.setEmail(signupForm.getEmail());
				newUser.setRole("USER");
				if (repository.findByUsername(signupForm.getUsername()) == null) { // Check if user exists
					repository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "register";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "register";
			}
		} else {
			return "register";
		}
		return "redirect:/login";
	}

}
