package br.com.tarefa.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
 
import br.com.model.TarefaModel;
import br.com.repository.TarefaRepository;
import br.com.usuario.controller.UsuarioController;
import br.com.uteis.Uteis;
 
@Named(value="cadastrarTarefaController")
@RequestScoped
public class CadastrarTarefaController {
 
	@Inject
	TarefaModel tarefaModel;
 
	@Inject
	UsuarioController usuarioController;
 
	@Inject
	TarefaRepository tarefaRepository;
 
 
	public TarefaModel getTarefaModel() {
		return tarefaModel;
	}
 
	public void setTarefaModel(TarefaModel pessoaModel) {
		this.tarefaModel = pessoaModel;
	}
 
	/**
	 *SALVA UM NOVO REGISTRO VIA INPUT 
	 */
	public void SalvarNovaTarefa(){
 
		tarefaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
		
		//INFORMANDO QUE O CADASTRO FOI VIA INPUT
		tarefaModel.setOrigemCadastro("I");
 
		tarefaRepository.SalvarNovoRegistro(this.tarefaModel);
		
		this.tarefaModel = null;
 
		Uteis.MensagemInfo("Registro cadastrado com sucesso");
 
	}
 
}