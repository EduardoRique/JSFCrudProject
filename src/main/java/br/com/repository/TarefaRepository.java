package br.com.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
 
import br.com.model.TarefaModel;
import br.com.repository.entity.TarefaEntity;
import br.com.repository.entity.UsuarioEntity;
import br.com.uteis.Uteis;

public class TarefaRepository {
	
	@Inject
	TarefaEntity tarefaEntity;
 
	EntityManager entityManager;
	
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA PESSOA
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(TarefaModel tarefaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		tarefaEntity = new TarefaEntity();
		tarefaEntity.setDeadline(tarefaModel.getDeadline());
		tarefaEntity.setTitulo(tarefaModel.getTitulo());
		tarefaEntity.setDescricao(tarefaModel.getDescricao());
		tarefaEntity.setPrioridade(tarefaModel.getPrioridade());
		tarefaEntity.setResponsavel(tarefaModel.getResponsavel());
		tarefaEntity.setSituacao(tarefaModel.getSituacao());
		tarefaEntity.setOrigemCadastro(tarefaModel.getOrigemCadastro());
		System.out.println(tarefaModel.getUsuarioModel().getCodigo());
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, tarefaModel.getUsuarioModel().getCodigo()); 
		tarefaEntity.setUsuarioEntity(usuarioEntity);
 
		entityManager.persist(tarefaEntity);
 
	}
}