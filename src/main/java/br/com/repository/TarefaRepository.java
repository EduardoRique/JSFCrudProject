package br.com.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.model.TarefaModel;
import br.com.model.UsuarioModel;
import br.com.repository.entity.TarefaEntity;
import br.com.repository.entity.UsuarioEntity;
import br.com.uteis.Uteis;

public class TarefaRepository {
	
	@Inject
	TarefaEntity tarefaEntity;
 
	EntityManager entityManager;
	
	/***
	 * MÉTODO RESPONSÁVEL POR SALVAR UMA NOVA TAREFA
	 * @param tarefaModel
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
	
	/***
	 * MÉTODO PARA CONSULTAR A TAREFA
	 * @return
	 */
	public List<TarefaModel> GetTarefas(){
		
		List<TarefaModel> tarefasModel = new ArrayList<TarefaModel>();
 
		entityManager =  Uteis.JpaEntityManager();
		Query query = entityManager.createNamedQuery("TarefaEntity.findAll");
		
		@SuppressWarnings("unchecked")
		Collection<TarefaEntity> tarefasEntity = (Collection<TarefaEntity>)query.getResultList();
 
		TarefaModel tarefaModel = null;
 
		for (TarefaEntity tarefaEntity : tarefasEntity) {
 
			tarefaModel = new TarefaModel();
			tarefaModel.setCodigo(tarefaEntity.getCodigo());
			tarefaModel.setDeadline(tarefaEntity.getDeadline());
			tarefaModel.setDescricao(tarefaEntity.getDescricao());
			tarefaModel.setPrioridade(tarefaEntity.getPrioridade());
			tarefaModel.setResponsavel(tarefaEntity.getResponsavel());
			tarefaModel.setSituacao(tarefaEntity.getSituacao());
			tarefaModel.setTitulo(tarefaEntity.getTitulo());
			
 
			if(tarefaEntity.getOrigemCadastro().equals("X"))
				tarefaModel.setOrigemCadastro("XML");
			else
				tarefaModel.setOrigemCadastro("INPUT");
 
			UsuarioEntity usuarioEntity =  tarefaEntity.getUsuarioEntity();			
 
			UsuarioModel usuarioModel = new UsuarioModel();
			usuarioModel.setUsuario(usuarioEntity.getUsuario());
 
			tarefaModel.setUsuarioModel(usuarioModel);
 
			tarefasModel.add(tarefaModel);
		}
 
		return tarefasModel;
	}
	
	/***
	 * CONSULTA UMA TAREFA CADASTRADA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	private TarefaEntity GetTarefa(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();
 
		return entityManager.find(TarefaEntity.class, codigo);
	}
 
	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param tarefaModel
	 */
	public void AlterarRegistro(TarefaModel tarefaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		TarefaEntity tarefaEntity = this.GetTarefa(tarefaModel.getCodigo());

		tarefaEntity.setDeadline(tarefaModel.getDeadline());
		tarefaEntity.setDescricao(tarefaModel.getDescricao());
		tarefaEntity.setPrioridade(tarefaModel.getPrioridade());
		tarefaEntity.setResponsavel(tarefaModel.getResponsavel());
		tarefaEntity.setSituacao(tarefaModel.getSituacao());
		tarefaEntity.setTitulo(tarefaModel.getTitulo());

 
		entityManager.merge(tarefaEntity);
	}
	
	/***
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param tarefaModel
	 */
	public void ConcluirRegistro(TarefaModel tarefaModel){
 
		entityManager =  Uteis.JpaEntityManager();
 
		TarefaEntity tarefaEntity = this.GetTarefa(tarefaModel.getCodigo());

		tarefaEntity.setDeadline(tarefaModel.getDeadline());
		tarefaEntity.setSituacao("Concluida");
		
		entityManager.merge(tarefaEntity);
	}
	
	/***
	 * EXCLUI UM REGISTRO DO BANCO DE DADOS
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo){
 
		entityManager =  Uteis.JpaEntityManager();		
 
		TarefaEntity pessoaEntity = this.GetTarefa(codigo);
 
		entityManager.remove(pessoaEntity);
	}
}