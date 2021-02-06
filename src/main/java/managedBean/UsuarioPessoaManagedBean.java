package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import dao.DaoUsuario;
import model.UsuarioPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();
	private DaoUsuario daoUsuario = new DaoUsuario();
	
	@PostConstruct
	public void init() {
		list = daoGeneric.listar(UsuarioPessoa.class);
	}

	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public List<UsuarioPessoa> getList() {
		return list;
	}

	public String salvar() {
		daoGeneric.salvar(usuarioPessoa);
		list.add(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Cadastrado com sucesso!!!"));
		return "";
	}

	public void novo() {
		usuarioPessoa = new UsuarioPessoa();
	}

	public String deletar() {
		try {
		daoUsuario.removerUsuario(usuarioPessoa);
		list.remove(usuarioPessoa);
		usuarioPessoa = new UsuarioPessoa();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!!!"));
		
		}catch (Exception e) {
			if(e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("informação: ", "Existem telefones para o usuário!"));
			}else {
				e.printStackTrace();
			}
		}
		
		return "";

	}

}
