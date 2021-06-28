package br.com.tarefa.controller;

import java.io.Serializable;
import java.util.List;
 
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import br.com.model.TarefaModel;
import br.com.repository.TarefaRepository;
 
@Named(value="consultarTarefaController")
@ViewScoped
public class ConsultarTarefaController implements Serializable {
 
	private static final long serialVersionUID = 1L;
 
	@Inject transient
	private TarefaModel tarefaModel;
 
	@Produces 
	private List<TarefaModel> tarefas;
 
	@Inject transient
	private TarefaRepository tarefaRepository;
 
	public List<TarefaModel> getTarefas() {
		return tarefas;
	}
	public void setTarefas(List<TarefaModel> tarefas) {
		this.tarefas = tarefas;
	}		
	public TarefaModel getTarefaModel() {
		return tarefaModel;
	}
	public void setTarefaModel(TarefaModel tarefaModel) {
		this.tarefaModel = tarefaModel;
	}
 
	/***
	 * CARREGA AS TAREFAS NA INICIALIZAÇÃO 
	 */
	@PostConstruct
	public void init(){
		//RETORNAR AS TAREFAS CADASTRADAS	
		this.tarefas = tarefaRepository.GetTarefas();
	}
	
	/***
	 * CARREGA INFORMAÇÕES DE UMA TAREFA PARA SER EDITADA
	 * @param tarefaModel
	 */
	public void Editar(TarefaModel tarefaModel){
 
		this.tarefaModel = tarefaModel;
 
	}
 
	/***
	 * ATUALIZA O REGISTRO QUE FOI ALTERADO
	 */
	public void AlterarRegistro(){
 
		this.tarefaRepository.AlterarRegistro(this.tarefaModel);
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	/***
	 * CONCLUIR O REGISTRO QUE FOI ALTERADO
	 */
	public void ConcluirRegistro(TarefaModel tarefaModel){
		this.tarefaModel = tarefaModel;
		this.tarefaRepository.ConcluirRegistro(this.tarefaModel);
		/*RECARREGA OS REGISTROS*/
		this.init();
	}
	
	/***
	 * EXCLUINDO UM REGISTRO
	 * @param tarefaModel
	 */
	public void ExcluirTarefa(TarefaModel tarefaModel){
 
		//EXCLUI A TAREFA DO BANCO DE DADOS
		this.tarefaRepository.ExcluirRegistro(tarefaModel.getCodigo());
 
		//REMOVENDO A TAREFA DA LISTA
		//ASSIM QUE É A TAREFA É REMOVIDA DA LISTA O DATATABLE É ATUALIZADO
		this.tarefas.remove(tarefaModel);
 
	}
 
 
}