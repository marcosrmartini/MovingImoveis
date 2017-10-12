package br.com.movingimoveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.movingimoveis.model.Imovel;

public interface ImovelRepository {

	@Query("select i from Imovel i where i.id_imobiliaria = :id")
	public List<Imovel> pesquisa(@Param(value = "id") Long id);
	
	
}
