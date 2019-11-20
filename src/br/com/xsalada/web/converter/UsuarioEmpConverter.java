package br.com.xsalada.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.UsuarioEmp;

@FacesConverter(value = "usuarioEmpConverter")
public class UsuarioEmpConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || (value.trim().length() == 0)) {
			return value;
		}
		String[] values = value.split("\\|");

		UsuarioEmp cidade = new UsuarioEmp();

		try {
			cidade.setId(Integer.valueOf(values[0]));
			cidade.setLogin(values[1]);
			cidade.setSenha(values[2]);
			cidade.setEmpresa(new Empresa());
			cidade.getEmpresa().setId(Integer.parseInt(values[3]));
		} catch (Exception e) {
			throw new ConverterException();
		}
		return cidade;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value instanceof UsuarioEmp) {
			UsuarioEmp p = (UsuarioEmp) value;
			if (p.getId() == null)
				return "";
			String chave = String.valueOf(p.getId())+ "|"
					+ p.getLogin()+ "|"
					+ p.getSenha()+ "|"
					+ (p.getEmpresa() == null ? "null" : p.getEmpresa().getId());
			return chave;
		} else {
			return "";
		}
	}

}
