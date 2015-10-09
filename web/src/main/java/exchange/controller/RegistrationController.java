package exchange.controller;

import javax.validation.Valid;

import org.entity.User;
import org.service.spring.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String loginPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, Model model) {
		String path = "registration";
		if (result.hasErrors()) {
			path = "registration";
		} else {
			if (userService.addUser(user.getFirstName(), user.getLastName(), user.getLogin(), user.getPassword())) {

				User currentUser = userService.getUser(user.getLogin(), user.getPassword());
				model.addAttribute("currentUser", currentUser);
				model.addAttribute("id", currentUser.getiD());
				path = "index";
			}
		}
		return path;
	}

}