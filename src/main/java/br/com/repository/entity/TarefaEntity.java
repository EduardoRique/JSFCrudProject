package br.com.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
 
 
@Entity
@Table(name="tb_tarefa")
 
@NamedQueries({
 
	@NamedQuery(name = "TarefaEntity.findAll",query= "SELECT p FROM TarefaEntity p")
 
})
public class TarefaEntity {
 
	@Id
	@GeneratedValue
	@Column(name = "id_tarefa")
	private Integer codigo;
 
	@Column(name = "titulo")
	private String  titulo;
 
	@Column(name = "descricao")
	private String  descricao;
	
	@Column(name = "responsavel")
	private String  responsavel;
 
	@Column(name = "prioridade")
	private String  prioridade;
	
	@Column(name = "situacao")
	private String  situacao;
 
	@Column(name = "deadline")
	@Temporal(TemporalType.DATE)
	private Date deadline;
 
	@Column(name = "fl_origemCadastro")
	private String  origemCadastro;
 
	@OneToOne
	@JoinColumn(name="id_usuario_cadastro")
	private UsuarioEntity usuarioEntity;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getOrigemCadastro() {
		return origemCadastro;
	}

	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}
 
}