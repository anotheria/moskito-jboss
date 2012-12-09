package org.zaunberg.moskitojboss.web.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.zaunberg.moskitojboss.business.UserService;

@FacesValidator("org.zaunberg.moskitojboss.web.validator.UsernameValidator")
public class UsernameValidator implements Validator {
	
	@Inject
	private transient ResourceBundle bundle;
	
    @Inject
    private UserService userService;	
	
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String username = (String) value;
        
        if(username == null || username.isEmpty()) {
        	return;
        }     
        
        if(userService.loadByUsername(username) != null) {
        	throw new ValidatorException(new FacesMessage(bundle.getString("org.zaunberg.moskitojboss.register.error.usernameAlreadyUsed")));  	
        }

    }

}
