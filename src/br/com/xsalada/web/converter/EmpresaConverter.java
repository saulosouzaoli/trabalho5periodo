package br.com.xsalada.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.xsalada.model.Empresa;

@FacesConverter(value = "empresaConverter")
public class EmpresaConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		if (value == null || (value.trim().length() == 0)) {
			return value;
		}
		String[] values = value.split("\\|");

		Empresa cidade = new Empresa();

		try {
			cidade.setId(Integer.valueOf(values[0]));			
			cidade.setCnpj(values[1]);
			cidade.setNome(values[2]);
			cidade.setNomeFantasia(values[3]);
		} catch (Exception e) {
			throw new ConverterException();
		}
		return cidade;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {

		if (value instanceof Empresa) {
			Empresa p = (Empresa) value;
			if(p.getId()==null)
				return "";
			String chave = String.valueOf(p.getId()) + "|" 
			+ p.getCnpj()+"|"
			+p.getNome()+"|"
			+p.getNomeFantasia();
			return chave;
		} else {
			return "";
		}
	}

}
