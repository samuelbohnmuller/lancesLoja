package br.com.alura.mvc.springMVC.dto;

import javax.validation.constraints.NotBlank;

import br.com.alura.mvc.springMVC.model.Pedido;
import br.com.alura.mvc.springMVC.model.StatusPedido;

public class RequisicaoNovoPedido { //transfere objetos na requisição. Transfere dados nos objetos(que serão os campos no formulário)

	@NotBlank //não pode ser nulo nem vazio no formulário
	private String nomeProduto; //atributos são os nomes(names) dos campos no formulário
	@NotBlank
	private String urlProduto;
	@NotBlank
	private String urlImagem;
	private String descricao;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getUrlProduto() {
		return urlProduto;
	}
	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}
	public String getUrlImagem() {
		return urlImagem;
	}
	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Pedido toPedido() {
		Pedido pedido = new Pedido();
		pedido.setDescricao(descricao);
		pedido.setNomeProduto(nomeProduto);
		pedido.setUrlImagem(urlImagem);
		pedido.setUrlProduto(urlProduto);
		pedido.setUrlProduto(urlProduto);
		pedido.setStatus(StatusPedido.AGUARDANDO); //começa por aguardando
		
		return pedido;
	}
	
}
