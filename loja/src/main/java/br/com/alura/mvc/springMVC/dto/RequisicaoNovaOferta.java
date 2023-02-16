package br.com.alura.mvc.springMVC.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.springMVC.model.Oferta;

public class RequisicaoNovaOferta {

	private static final DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //atributo imutável para formatar data
	
	//atributos que representam campos de preenchimento da oferta pelo usuário na view
	
	@Pattern(regexp = "^\\d+(\\.\\d+{2})?$") //começa com 1 ou mais digotos e depois do ., mais dois digitos
	@NotNull
	private Long pedidoId;
	@NotNull
	private String valor;
	@Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}$") //começa com 2 digitos, depois precisa ter uma /, + 2 digitos e outra / e + 4 digitos e mais nada
	@NotNull
	private String dataDaEntrega;
	private String comentarios;
	
	
	public Long getPedidoId() {
		return pedidoId;
	}
	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDataDaEntrega() {
		return dataDaEntrega;
	}
	public void setDataDaEntrega(String dataDaEntrega) {
		this.dataDaEntrega = dataDaEntrega;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public Oferta toOferta() { //seto os valores dos atributos da classe RequisicaoNovaOferta para os atributos da classe oferta
		Oferta oferta = new Oferta();
		oferta.setComentario(this.comentarios);
		oferta.setDataDaEntrega(LocalDate.parse(this.dataDaEntrega, formatacao));
		oferta.setValor(new BigDecimal(this.valor)); 
		return oferta;
	}
	
}
