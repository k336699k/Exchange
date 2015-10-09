package exchange.controller;

import javax.validation.Valid;

import org.entity.Metal;

import org.service.spring.MetalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addMetal")
public class AddMetalController {
	@Autowired
	MetalService metalService;

	@RequestMapping(method = RequestMethod.GET)
	public String addMetal(Model model) {
		Metal metal = new Metal();
		model.addAttribute("metal", metal);
		return "addMetal";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String saveMetal(@Valid Metal metal, BindingResult result, Model model) {
		String path = "addMetal";
		if (result.hasErrors()) {
			path = "addMetal";
		} else {
			if (metalService.addMetal(metal.getTitle(), metal.getQuantity(), metal.getPriceString())) {

				Metal currentMetal = metalService.getMetal(metal.getTitle());
				model.addAttribute("currentMetal", currentMetal);
				model.addAttribute("id", currentMetal.getiD());
				path = "main";
			}
		}
		return path;
	}

}