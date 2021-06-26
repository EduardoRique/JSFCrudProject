package br.com.tarefa.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
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
	
	private UploadedFile file;
	 
	public UploadedFile getFile() {
		return file;
	}
 
	public void setFile(UploadedFile file) {
		this.file = file;
	}
 
 
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
	
	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 * @throws ParseException 
	 */
	 public void UploadRegistros() throws ParseException {
 
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
 
		 try {
 
 
			 if(this.file.getFileName().equals("")){
				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }
 
			 DocumentBuilder builder = factory.newDocumentBuilder();
 
	                 Document doc = builder.parse(this.file.getInputstream());
 
	                 Element element = doc.getDocumentElement();
 
	                 NodeList nodes = element.getChildNodes();
 
	                for (int i = 0; i < nodes.getLength(); i++) {
 
	        	     Node node  = nodes.item(i);
 
	        	    if(node.getNodeType() == Node.ELEMENT_NODE){
 
	        		 Element elementTarefa =(Element) node;
 
	        		 //PEGANDO OS VALORES DO ARQUIVO XML
	        		 String titulo = elementTarefa.getElementsByTagName("titulo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String descricao = elementTarefa.getElementsByTagName("descricao").item(0).getChildNodes().item(0).getNodeValue();
	        		 String responsavel = elementTarefa.getElementsByTagName("responsavel").item(0).getChildNodes().item(0).getNodeValue();
	        		 String prioridade = elementTarefa.getElementsByTagName("prioridade").item(0).getChildNodes().item(0).getNodeValue();
	        		 String situacao = elementTarefa.getElementsByTagName("situacao").item(0).getChildNodes().item(0).getNodeValue();
	        		 String deadline = elementTarefa.getElementsByTagName("deadline").item(0).getChildNodes().item(0).getNodeValue();
	        		 
	        		 SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	        		 Date dataFormatada = formato.parse(deadline); 
	        		 
	        		 TarefaModel newTarefaModel = new TarefaModel();
 
	        		 newTarefaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
	        		 newTarefaModel.setTitulo(titulo);
	        		 newTarefaModel.setDescricao(descricao);
	        		 newTarefaModel.setResponsavel(responsavel);
	        		 newTarefaModel.setPrioridade(prioridade);
	        		 newTarefaModel.setSituacao(situacao);
	        		 newTarefaModel.setDeadline(dataFormatada);
	        		 newTarefaModel.setOrigemCadastro("X");
 
	        		 //SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
	        		 tarefaRepository.SalvarNovoRegistro(newTarefaModel);
	        	   }
	              }
 
 
 
		     Uteis.MensagemInfo("Registros cadastrados com sucesso!");
 
		} catch (ParserConfigurationException e) {
 
			e.printStackTrace();
 
		} catch (SAXException e) {
 
			e.printStackTrace();
 
		} catch (IOException e) {
 
			e.printStackTrace();
		}
 
 
	 }
 
}