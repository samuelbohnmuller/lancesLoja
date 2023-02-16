package br.com.alura.mvc.springMVC.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/home") //todas as requisições pra home pegarão aqui
public class HomeController {

	@Autowired //autoriza a instancia da classe
	private PedidoRepository pedidoRepository; //para se conectar com o modelo com conexão ao banco
	
	@GetMapping //chegará nesse método primeiro de tudo
	public String home(Model model, Principal principal) { //Principal injeta os dados do usuário logado
		Sort sort = Sort.by("dataDaEntrega").ascending(); //ordenar pela data de entrega, ascendente
		PageRequest paginacao = PageRequest.of(0, 20, sort);  //mostrará a página 0 a 20 páginas em ascendencia 
		
		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE, paginacao); //busca pedidos por status no banco, com ordenação ascendente na data de entrega com 10 páginas  
		
		model.addAttribute("pedidos", pedidos); //adicionado pedidos na resposta, que será preenchido pelos objetos na view
		return "home"; 
	}
	
	/*
	 * @GetMapping("/{status}") //o usuário digita na url qual o status que ele quer
	 * public String porStatus(@PathVariable("status") String status, Model model) {
	 * //recebo na requisição o valor do endereço e passo para objeto status
	 * List<Pedido> pedidos = pedidoRepository.
	 * findByStatus(StatusPedido.valueOf(status.toUpperCase())); //busca no banco
	 * pedidos com status aguardando(parse de String para retornar um StatusPedido
	 * no objeto status). necessário converter para caixa baixa o enum.
	 * 
	 * model.addAttribute("pedidos", pedidos); model.addAttribute("status", status);
	 * return "home"; }
	 * 
	 * @ExceptionHandler(IllegalArgumentException.class) //indicando qual excessão
	 * chamará esse método public String onError() { //se der erro de endereço,
	 * volta para home return "redirect:/home"; }
	 */
	
}
