package org.zaunberg.moskitojboss.web.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator("org.zaunberg.moskitojboss.web.validator.ConfirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {
	
	public static final String ATTRIBUTE_NAME = "pwConfirm";
	
	@Inject
	private transient ResourceBundle bundle;
	
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String password = (String) value;
        String confirm = (String) component.getAttributes().get(ATTRIBUTE_NAME);

        if (password == null || confirm == null) {
            return;
        }

        if (!password.equals(confirm)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("org.zaunberg.moskitojboss.register.error.passwordConfirmNotEqual")));
        }
    }

}
