package org.market.negocios;

import java.util.List;

import org.market.dao.MarcaDAO;
import org.market.entidades.Marca;

public class MarcaNegocios {
	
	private MarcaDAO marcaDAO = new MarcaDAO();
	
	public Marca salvarMarca(long idMarca, String nome) {
			
		try {								
			Marca marca = new Marca();
			marca.setIdMarca(idMarca);
			marca.setNome(nome);
			marca = marcaDAO.salvarMarca(marca);					
			return marca;			
		} catch(Exception e)	{
			e.printStackTrace();
			return null;
		}
	}
	
	public Marca recuperarMarca(String nome){
		try {
			return marcaDAO.recuperarMarca(nome);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Marca recuperarMarcaPorId(long idMarca){
		try {
			return marcaDAO.recuperarMarcaPorId(idMarca);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Marca> recuperarTodasMarcas() {
		try {
			return marcaDAO.recuperarTodasMarcas();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
