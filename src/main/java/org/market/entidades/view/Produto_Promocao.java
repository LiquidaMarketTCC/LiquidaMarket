package org.market.entidades.view;

import org.market.entidades.ItensPromocao;
import org.market.entidades.ProdutoComercializado;

public class Produto_Promocao {

	ProdutoComercializado produto = null;
	ItensPromocao promocao = null;

	public ProdutoComercializado getProduto() {
		return produto;
	}

	public void setProduto(ProdutoComercializado produto) {
		this.produto = produto;
	}

	public ItensPromocao getPromocao() {
		return promocao;
	}

	public void setPromocao(ItensPromocao promocao) {
		this.promocao = promocao;
	}

}
