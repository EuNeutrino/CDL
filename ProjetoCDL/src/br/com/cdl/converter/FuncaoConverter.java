package br.com.cdl.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.cdl.dao.FuncaoDAO;
import br.com.cdl.domain.Funcao;

@FacesConverter("funcaoConverter")
public class FuncaoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facescontext, UIComponent componente, String valor) {
		System.out.println("getAsObject");
		Long codigo = Long.parseLong(valor);
		Funcao f = new Funcao();
		f.setId(codigo);
		try {
			Funcao funcao = new FuncaoDAO().listarPorCodigo(f);
			return funcao;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object objecto) {

		System.out.println("getAsString");
		try {
			Funcao funcao = (Funcao) objecto;
			Long codigo = funcao.getId();
			return codigo.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
