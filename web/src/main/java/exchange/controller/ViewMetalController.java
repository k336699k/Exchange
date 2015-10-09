package exchange.controller;

import java.util.List;

import org.entity.Metal;
import org.service.spring.MetalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/viewMetal")
public class ViewMetalController {
	
	@Autowired
	MetalService metalService;
	
	@RequestMapping(method = RequestMethod.GET)
    public String listMetals(ModelMap model) {
 
        List<Metal> metals = metalService.getAllMetals();
        model.addAttribute("metals", metals);
        return "viewMetal";
    }
	
}
