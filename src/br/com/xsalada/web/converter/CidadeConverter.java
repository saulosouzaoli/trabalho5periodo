package br.com.xsalada.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.xsalada.model.Cidade;

@FacesConverter(value = "cidadeConverter")
public class CidadeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || value.trim().isEmpty()) {
			return value;
		}
		String[] values = value.split("\\|");

		Cidade cidade = new Cidade();

		try {
			cidade.setId(Integer.valueOf(values[0]));
			cidade.setDescricao(values[1]);
			cidade.setUf(values[2]);
		} catch (Exception e) {
			throw new ConverterException();
		}
		return cidade;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value instanceof Cidade) {
			Cidade p = (Cidade) value;
			if (p.getId() == null)
				return "";
			String chave = String.valueOf(p.getId()) + "|" + p.getDescricao()
					+ "|" + p.getUf();
			return chave;
		} else {
			return "";
		}
	}

}
