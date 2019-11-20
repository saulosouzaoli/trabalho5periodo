package br.com.xsalada.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.xsalada.model.Empresa;
import br.com.xsalada.model.Ingrediente;

@FacesConverter(value = "ingredienteConverter")
public class IngredienteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || value.trim().isEmpty()) {
			return value;
		}
		String[] values = value.split("\\|");

		Ingrediente ingrediente = new Ingrediente();

		try {
			if (value.contains("|") && values.length > 1) {
				if (!values[0].equals("null"))
					ingrediente.setId(Integer.valueOf(values[0]));
				if (!values[1].equals("null"))
					ingrediente.setDescricao(values[1]);
				if (!values[2].equals("null")){
					ingrediente.setEmpresa(new Empresa());
					ingrediente.getEmpresa().setId(Integer.valueOf(values[2]));
				}
				ingrediente.setNome(values[3]);
			} else
				ingrediente.setNome(values[0]);
		} catch (Exception e) {
			throw new ConverterException();
		}
		return ingrediente;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value instanceof Ingrediente) {
			Ingrediente p = (Ingrediente) value;
			if (p.getId() == null)
				return "";
			String chave = String.valueOf(p.getId()) + "|" + p.getDescricao()+"|"+(p.getEmpresa()==null?"null" : String.valueOf(p.getEmpresa().getId()))+ "|" + p.getNome();
			return chave;
		} else {
			return "";
		}
	}

}
