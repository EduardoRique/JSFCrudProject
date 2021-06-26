package br.com.repository;

import java.io.Serializable;

import javax.persistence.Query;
 
import br.com.model.UsuarioModel;
import br.com.repository.entity.UsuarioEntity;
import br.com.uteis.Uteis;
 
 
public class UsuarioRepository implements Serializable {
 
 
	private static final long serialVersionUID = 1L;
 
	public UsuarioEntity ValidaUsuario(UsuarioModel usuarioModel){
 
		try {
 
			//QUERY QUE VAI SER EXECUTADA (UsuarioEntity.findUser) 	
			Query query = Uteis.JpaEntityManager().createNamedQuery("UsuarioEntity.findUser");
 
			//PARÂMETROS DA QUERY
			query.setParameter("usuario", usuarioModel.getUsuario());
			query.setParameter("senha", usuarioModel.getSenha());
			
			//RETORNA O USUÁRIO SE FOR LOCALIZADO
			UsuarioEntity ret = (UsuarioEntity)query.getSingleResult();
			return ret;
 
		} catch (Exception e) {

			return null;
		}
 
 
 
	}
}
