package br.com.caelum.notasfiscais.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("comecaComMaisculaValidador")
public class ComecarComMaisculaValitador implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent ui, Object obj) throws ValidatorException {
		if(!obj.toString().matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Deveria começar com maiúsculo", "Deveria começar com maiúsculo"));
		}
	}

}
