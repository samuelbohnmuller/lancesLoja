package br.com.alura.mvc.springMVC.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.springMVC.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.springMVC.model.Pedido;
import br.com.alura.mvc.springMVC.model.User;
import br.com.alura.mvc.springMVC.repository.PedidoRepository;
import br.com.alura.mvc.springMVC.repository.UserRepository;

@Controller
@RequestMapping("pedido") //aceita requisições do tipo post e get
public class PedidoController {
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@GetMapping("formulario") //requisição get para /formulario buscará esse método
	public String formulario(RequisicaoNovoPedido requisicao) { //acesso a requisição aos atributos da classe, que tem os mesmos nomes dos imputs
		return "pedido/formulario"; //retornará uma um HTML na pasta pedido, no arquivo formulario
	}
	
	@PostMapping("novo") //requisição POST para esse endereço, que primeiro passará por pedido e depois para novo
	public String novo(@Valid RequisicaoNovoPedido requisicao, BindingResult result) { //valida os campos com notblank no @valid e nos dá o resultado da validação por result
		if(result.hasErrors()) { //se conter erros na validação(não foi preenchido algum campo)
			return "pedido/formulario"; //formulário é apresentado novamente para o usuário
		}
		
		//pegar o usuário que cadastrou o pedido
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); //pega o usuário logado
		User user = userRepository.findByUsername(username); //pesquisa o usuário logado no banco
		
		Pedido pedido = requisicao.toPedido(); //convertendo uma requisição de RequisicaoNovoPedido para Pedido
		pedido.setUser(user); //associa o usuário logado ao pedido
		pedidoRepository.save(pedido); //salvo no banco os atributos passados nos inputs(que contém os mesmos nomes dos atributos da classe RequisicaoNovoPedido)
		
		return "redirect:/home"; //redireciona para uma url
	}

}
