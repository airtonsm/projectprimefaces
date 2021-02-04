package managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoGeneric;
import model.UsuarioPessoa;

@ManagedBean(name = "usuarioPessoaManagedBean")
@ViewScoped
public class UsuarioPessoaManagedBean {

	private UsuarioPessoa usuarioPessoa = new UsuarioPessoa();
	private DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
	private List<UsuarioPessoa> list = new ArrayList<UsuarioPessoa>();

	public UsuarioPessoa getUsuarioPessoa() {
		return usuarioPessoa;
	}

	public void setUsuarioPessoa(UsuarioPessoa usuarioPessoa) {
		this.usuarioPessoa = usuarioPessoa;
	}

	public List<UsuarioPessoa> getList() {
		list = daoGeneric.listar(UsuarioPessoa.class);
		return list;
	}

	public String salvar() {
		daoGeneric.salvar(usuarioPessoa);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Cadastrado com sucesso!!!"));
		return "";
	}

	public void novo() {
		usuarioPessoa = new UsuarioPessoa();
	}

	public String deletar() {
		daoGeneric.deletarPoId(usuarioPessoa);
		usuarioPessoa = new UsuarioPessoa();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com sucesso!!!"));
		return "";

	}

}
