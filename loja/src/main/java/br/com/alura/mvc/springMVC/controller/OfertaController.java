package br.com.alura.mvc.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oferta")
public class OfertaController {

	@GetMapping
	public String getFormularioDeOfertas() {
		return "oferta/home"; //redicionará para a view com HTML de ofertas
	}
	
}
