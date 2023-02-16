package br.com.alura.mvc.springMVC.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.springMVC.model.Pedido;
import br.com.alura.mvc.springMVC.model.StatusPedido;
import br.com.alura.mvc.springMVC.repository.PedidoRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired //autoriza a instancia da classe
	private PedidoRepository pedidoRepository;

	@GetMapping("pedido") 
	public String home(Model model, Principal principal) { 
		List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName()); 
		
		model.addAttribute("pedidos", pedidos); 
		return "usuario/home"; 
	}
	
	@GetMapping("pedido/{status}") //mapeamento da url
	public String porStatus(@PathVariable("status") String status, Model model, Principal principal) { //recebo na requisição o valor do endereço e passo para objeto status
		List<Pedido> pedidos = pedidoRepository.
				findByStatusAndUser(StatusPedido.valueOf(status.toUpperCase()), principal.getName()); //busca no banco pedidos com status aguardando(parse de String para retornar um StatusPedido no objeto status). necessário converter para caixa baixa o enum.
		
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home"; //mapeamento da pasta
	}
	
	@ExceptionHandler(IllegalArgumentException.class) 
	public String onError() { 
		return "redirect:usuario/home";
	}
	
}
