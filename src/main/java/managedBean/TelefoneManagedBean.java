package managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DaoTelefone;
import dao.DaoUsuario;
import model.TelefoneUser;
import model.UsuarioPessoa;

@ManagedBean(name = "telefoneManaged")
@ViewScoped
public class TelefoneManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UsuarioPessoa user = new UsuarioPessoa();
	DaoUsuario daoUser = new DaoUsuario();
	private DaoTelefone<TelefoneUser> daoTelefone = new DaoTelefone<TelefoneUser>();
	List<TelefoneUser> list = new ArrayList<TelefoneUser>();

	private TelefoneUser telefone = new TelefoneUser();

	@PostConstruct
	public void init() {

		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		user = daoUser.pesquisar(Long.parseLong(coduser), UsuarioPessoa.class);

	}

	public void setTelefone(TelefoneUser telefone) {
		this.telefone = telefone;
	}

	public TelefoneUser getTelefone() {
		return telefone;
	}

	public void setUser(UsuarioPessoa user) {
		this.user = user;
	}

	public UsuarioPessoa getUser() {
		return user;
	}

	public void setDaoTelefone(DaoTelefone<TelefoneUser> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public void setDaoUser(DaoUsuario daoUser) {
		this.daoUser = daoUser;
	}

	public List<TelefoneUser> getList() {
		return list;
	}

	public void setList(List<TelefoneUser> list) {
		this.list = list;
	}

	public String salvar() {
		telefone.setUsuarioPessoa(user);
		daoTelefone.salvar(telefone);
		telefone = new TelefoneUser();
		user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Cadastrado com sucesso!!!"));

		return "";
	}

	public void deletar() {
		try {

			daoTelefone.deletarPoId(telefone);
			user = daoUser.pesquisar(user.getId(), UsuarioPessoa.class);
			telefone = new TelefoneUser();
			// FacesContext.getCurrentInstance().addMessage(null,
			// new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação: ", "Removido com
			// sucesso!!!"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
