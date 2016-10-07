package br.com.portaria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.portaria.model.Funcionario;

@Stateless
public class FuncionarioDAO {

	@PersistenceContext
	EntityManager manager;

	public void adicionar(Funcionario funcionario) {
		manager.persist(funcionario);
	}

	public void alterar(Funcionario funcionario) {
		manager.merge(funcionario);
	}

	public void remover(Funcionario funcionario) {
		Funcionario remover = buscaPorId(funcionario.getId());
		manager.remove(remover);
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> listar() {
		return manager.createNamedQuery("Funcionario.listar").getResultList();
	}

	public Funcionario buscaPorId(Long id) {
		Funcionario funcionario = (Funcionario) manager.createNamedQuery("Funcionario.buscaPorId").
		setParameter("id", id).getSingleResult();	
		return funcionario;
	}

	public Funcionario autenticar(String login, String senha) {
		Funcionario funcionario = (Funcionario) manager.createNamedQuery("Funcionario.login").
		setParameter("login", login).
		setParameter("senha", senha).getSingleResult();
		return funcionario;
	}
}
