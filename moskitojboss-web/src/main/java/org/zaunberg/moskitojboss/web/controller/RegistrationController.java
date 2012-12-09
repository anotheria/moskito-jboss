package org.zaunberg.moskitojboss.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.zaunberg.moskitojboss.business.UserService;
import org.zaunberg.moskitojboss.domain.User;

@WebController
public class RegistrationController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;
    
    @Inject
    private UserService userService;
   
    @Inject
    private NavigationController navigationController;
    
    private User user;
    private boolean registrationComplete = false;
    
    // -------------- Initialization ------------------------------------------------------
    
    @PostConstruct
    public void init() {
      user = new User();
    }
  
    // -------------- Actions -------------------------------------------------------------
    
    public String createUser() {
        userService.saveUser(user);
        log.info("User was created successfully: {}", user.getLogin());
        user = new User();
        registrationComplete = true;
        return null;
    }   
    
    public String cancel() {
    	return null;
    }
    
    public String activateRegistration() {
    	return null;
    }
      
    // -------------- Getter/Setter -------------------------------------------------------
    
    public User getUser() {
        return user;
    }

	public boolean isRegistrationComplete() {
		return registrationComplete;
	}

	public void setRegistrationComplete(boolean registrationComplete) {
		this.registrationComplete = registrationComplete;
	}
}
