package exchange.controller;

import org.service.spring.MetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/delete-{title}-metal")
public class DeleteMetalController {
	
	@Autowired
	MetalService metalService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String deleteMetal(@PathVariable String title) {
		metalService.deleteMetal(title);
        return "redirect:/viewMetal";
    }
}
