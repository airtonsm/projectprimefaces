package managedBean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import model.UsuarioPessoa;

@ManagedBean(name = "telefoneManaged")
@ViewScoped
public class TelefoneManagedBean {

	private UsuarioPessoa user = new UsuarioPessoa();

	@PostConstruct
	public void init() {

		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		System.out.println(coduser);

	}

}
