package br.com.alura.mvc.springMVC.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.springMVC.model.Pedido;
import br.com.alura.mvc.springMVC.model.StatusPedido;
import br.com.alura.mvc.springMVC.repository.PedidoRepository;

@RestController //API rest controlador
@RequestMapping("/api/pedidos") //endereço
public class PedidosRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	//dados apresentados no JSON
	@GetMapping("aguardando") //url devolve o retorno do método com JSON
	public List<Pedido> getPedidosAguardandoPedidos(){
		Sort sort = Sort.by("id").ascending(); 
		PageRequest paginacao = PageRequest.of(0, 20, sort);
		
		return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao); //busca no banco os pedidos com status AGUARDANDO por ordenação de ID, retornando os dados em JSON
	}
	
}
