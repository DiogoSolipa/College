package org.therestaurant.tweb.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewOrderController {

    @Autowired
    private ClientRepository repository;
    
	public String newOrder() 
	{
		return "new-order-view";
	}
}
