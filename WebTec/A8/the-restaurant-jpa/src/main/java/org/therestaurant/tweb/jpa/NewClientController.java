package org.therestaurant.tweb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewClientController {

	private static final Logger log = LoggerFactory.getLogger(NewClientController.class);

    @Autowired
    private ClientRepository repository;
    
	@PostMapping("/new-client")
	public String newClient(
			@RequestParam(name="name", required=false, defaultValue="World") String name,
			@RequestParam(name="last_name", required = false, defaultValue ="none")  String lastName,
			@RequestParam(name="email", required = false, defaultValue ="none") String email,
			Model model)
	{
		
		repository.save(new Client(name, lastName, email));
		
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Client aClient : repository.findAll()) {
			log.info(aClient.toString());
		}
		log.info("");
		
		model.addAttribute("name", name);
		model.addAttribute("last_name", lastName);
		model.addAttribute("email", email);
		return "new-client-view";
	}
}
