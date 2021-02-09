package dao;

import java.io.Serializable;

import model.UsuarioPessoa;

public class DaoUsuario extends DaoGeneric<UsuarioPessoa> implements Serializable{

	private static final long serialVersionUID = 1L;

	public void removerUsuario(UsuarioPessoa pessoa) throws Exception  {
		
		getEntityManager().getTransaction().begin();
		String sqlDelteFone = "delete from telefoneuser where usuariopessoa_id = " + pessoa.getId();
		getEntityManager().createNativeQuery(sqlDelteFone).executeUpdate();
		getEntityManager().getTransaction().commit();

		super.deletarPoId(pessoa);

	}

}
