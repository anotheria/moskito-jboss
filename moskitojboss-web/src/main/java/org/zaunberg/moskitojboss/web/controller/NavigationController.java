package org.zaunberg.moskitojboss.web.controller;

import static org.zaunberg.moskitojboss.web.PageConstants.MOSKITO_PAGE;
import static org.zaunberg.moskitojboss.web.PageConstants.PROFILE_PAGE;
import static org.zaunberg.moskitojboss.web.PageConstants.HOME_PAGE;
import static org.zaunberg.moskitojboss.web.PageConstants.REGISTRATION_PAGE;
import static org.zaunberg.moskitojboss.web.PageConstants.LOGIN_PAGE;

import java.io.Serializable;



/**
 * Controller for navigation links
 */
@WebController
public class NavigationController implements Serializable {

    private boolean disabledNewTask = false;

    public String goHome() {
        disabledNewTask = true;
        return HOME_PAGE;
    }   
    
    public String goMoskito() {
        disabledNewTask = true;
        return MOSKITO_PAGE;
    } 
    
    public String goProfile() {
        disabledNewTask = true;
        return PROFILE_PAGE;
    }
    
    public String goLogin() {
        disabledNewTask = true;
        return LOGIN_PAGE;
    }   
    
    public String goRegistration() {
        disabledNewTask = true;
        return REGISTRATION_PAGE;
    } 

    // -------------- Getter/Setter -------------------------------------------------------
    public boolean isDisabledNewTask() {
        return disabledNewTask;
    }

    public void setDisabledNewTask(boolean disabledNewTask) {
        this.disabledNewTask = disabledNewTask;
    }
}
