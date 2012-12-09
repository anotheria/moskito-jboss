package org.zaunberg.moskitojboss.web.controller;

import org.slf4j.Logger;
import org.zaunberg.moskitojboss.business.UserService;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.web.LoggedInUser;
import org.zaunberg.moskitojboss.web.UserChangedEvent;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@WebController
public class LoginController implements Serializable {

    private String loginName;
    private String password;
    private User user;
    private boolean loggedIn;

    @Inject
    private UserService userService;

    @Inject
    private Logger log;

// -------------- Initialization ------------------------------------------------------

// -------------- Actions -------------------------------------------------------------

    public String login() {
        User user = this.userService.loadByUsername(this.loginName);
        String forward;

        if (user != null && user.getPassword().equals(this.password)) {
            forward = "succeed";

            log.info("Login succeed ({})", user.getLogin());

            this.loggedIn = true;
            this.user = user;
        } else {
            forward = null;

            log.error("Login failed");

            this.loggedIn = false;
            this.user = null;
        }

        return forward;
    }

    public void logout() {
        this.loggedIn = false;
        this.user = null;
    }

    public void observeUserChangeEvent(@Observes UserChangedEvent event) {
        user = this.userService.loadById(user.getId());
    }

    @Produces
    @LoggedInUser
    public User getUser() {
        return user;
    }

    // -------------- Getter/Setter -------------------------------------------------------

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }
}
