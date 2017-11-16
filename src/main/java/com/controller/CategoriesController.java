package com.controller;

import com.model.Category;
import com.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/categoryList")
public class CategoriesController {


	private final Logger log = LoggerFactory.getLogger(CategoriesController.class);

	private final ProductService productService;
	@Autowired
	public CategoriesController(ProductService productService) {
		this.productService = productService;
	}


	@RequestMapping( method = RequestMethod.GET, headers="Accept=application/json")
	public List<Category> getCategoryList() {
		log.debug("Getting All Category List From DB");
		return productService.getCategoryList();

	}
}