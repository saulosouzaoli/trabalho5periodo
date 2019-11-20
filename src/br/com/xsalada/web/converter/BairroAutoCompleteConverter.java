package br.com.xsalada.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;

@FacesConverter(value = "bairroAutoCompleteConverter")
public class BairroAutoCompleteConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || value.trim().isEmpty()) {
			return value;
		}
		String[] values = value.split("\\|");

		Bairro Bairro = new Bairro();

		try {
			Bairro.setId(Integer.valueOf(values[0]));
			Bairro.setDescricao(values[1]);
			Bairro.setCidade(new Cidade());
			Bairro.getCidade().setId(Integer.valueOf(values[2]));
		} catch (Exception e) {
			throw new ConverterException();
		}
		return Bairro;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value instanceof Bairro) {
			Bairro p = (Bairro) value;
			String chave =  p.getDescricao();
			return chave;
		} else {
			return "";
		}
	}

}
