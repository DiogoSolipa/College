package com.example.poorstore;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NewOrderController {

	private static final Logger log = LoggerFactory.getLogger(NewProductController.class);

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private OrdersRepository ordersRepository;

	@RequestMapping("/product/{id}/add-to-cart")
	public void newOrder(Model model, @PathVariable("id") long id)
	{

		ordersRepository.save(new Orders(id));


		log.info("Orders found with findAll():");
		log.info("-------------------------------");
		for (Orders aOrder : ordersRepository.findAll()) {
			log.info(aOrder.toString());
		}
		log.info("");

		model.addAttribute("id", id);
	}
}
