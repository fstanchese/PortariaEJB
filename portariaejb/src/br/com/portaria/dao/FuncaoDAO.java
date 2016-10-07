package br.com.portaria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.portaria.model.Funcao;

@Stateless
public class FuncaoDAO {

	@PersistenceContext
	EntityManager manager;

	public void adicionar(Funcao funcao) {
		manager.persist(funcao);
	}

	public void alterar(Funcao funcao) {
		manager.merge(funcao);
	}

	public void remover(Funcao funcao) {
		Funcao remover = buscaPorId(funcao.getId());
		manager.remove(remover);
	}

	@SuppressWarnings("unchecked")
	public List<Funcao> listar() {
		return manager.createNamedQuery("Funcao.listar").getResultList();
	}

	public Funcao buscaPorId(Long id) {
		return manager.find(Funcao.class, id);
	}
}
