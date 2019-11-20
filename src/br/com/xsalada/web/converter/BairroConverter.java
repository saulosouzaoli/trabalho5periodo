package br.com.xsalada.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.xsalada.model.Bairro;
import br.com.xsalada.model.Cidade;

@FacesConverter(value = "bairroConverter")
public class BairroConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || value.trim().isEmpty()) {
			return value;
		}
		String[] values = value.split("\\|");

		Bairro bairro = new Bairro();

		try {
			if (value.contains("|") && values.length > 1) {
				if (!values[0].equals("null"))
					bairro.setId(Integer.valueOf(values[0]));
				if (!values[1].equals("null"))
					bairro.setCidade(new Cidade());
					bairro.getCidade().setId(Integer.valueOf(values[1]));
				bairro.setDescricao(values[2]);
			} else
				bairro.setDescricao(values[0]);
		} catch (Exception e) {
			throw new ConverterException();
		}
		return bairro;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value instanceof Bairro) {
			Bairro p = (Bairro) value;
			if (p.getId() == null)
				return "";
			String chave = String.valueOf(p.getId())+ "|" + String.valueOf(p.getCidade().getId()) + "|" + p.getDescricao();
			return chave;
		} else {
			return "";
		}
	}

}
