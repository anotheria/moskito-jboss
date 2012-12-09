package org.zaunberg.moskitojboss.web;

import org.jboss.seam.security.annotations.Secures;
import org.zaunberg.moskitojboss.web.controller.LoginController;

/**
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
public class SecurityProcessorBean {

    @Secures
    @UserIsLoggedIn
    public boolean userIsLoggedIn(LoginController loginController) {
        return loginController != null && loginController.getLoggedIn();
    }
}
