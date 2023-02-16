package br.com.alura.mvc.springMVC.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.springMVC.dto.RequisicaoNovaOferta;
import br.com.alura.mvc.springMVC.model.Oferta;
import br.com.alura.mvc.springMVC.model.Pedido;
import br.com.alura.mvc.springMVC.repository.PedidoRepository;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@PostMapping //requisição POST
	public Oferta criaNovaOferta(@Valid @RequestBody RequisicaoNovaOferta requisicao) { //@RequestBody pega os dados da requisição(preenchios pelo usuário nos campos) e coloca no objeto requisicao. @Valid é para aceitar as validações dos atributos que receberá de RequisicaoNovaOferta
		Optional<Pedido> pedidoEncontrado = pedidoRepository.findById(requisicao.getPedidoId());
		
		if(!pedidoEncontrado.isPresent()) {
			return null;
		}
		
		Pedido pedido = pedidoEncontrado.get(); //pega o objeto do tipo Optional com o get
		
		Oferta novaOferta = requisicao.toOferta(); //retorno a oferta(com os valores dos atributos RequisicaoNovaOferta(que são convertidos para Oferta, setando os atributos nos métodos set da classe Oferta)) para objeto de Oferta
		novaOferta.setPedido(pedido); //pedido tem o pedido filtrado pelo id no banco e é setado no atributo pedido da classe Oferta
		pedido.getOfertas().add(novaOferta); //adiciono a oferta no pedido, como o pedido foi adicionado na oferta
		pedidoRepository.save(pedido); //salvo no banco o pedido(que salva a oferta também)
		
		return novaOferta;
	}
}
