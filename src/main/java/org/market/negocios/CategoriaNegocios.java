package org.market.negocios;

import java.util.List;

import org.market.dao.CategoriaDAO;
import org.market.entidades.Categoria;

public class CategoriaNegocios{
	
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	public Categoria salvarCategoria(String nome) {
			
		try {								
			Categoria categoria = new Categoria();
			categoria.setNome(nome);
			categoria = categoriaDAO.salvarCategoria(categoria);					
			return categoria;			
		} catch(Exception e)	{
			e.printStackTrace();
			return null;
		}
	}
	
	public Categoria recuperarCategoria(String nome){
		try {
			return categoriaDAO.recuperarCategoria(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Categoria> recuperarTodasCategorias() {
		try {
			return categoriaDAO.recuperarTodasCategorias();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
