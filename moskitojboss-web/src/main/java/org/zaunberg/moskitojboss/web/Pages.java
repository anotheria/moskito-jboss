package org.zaunberg.moskitojboss.web;

import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

/**
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@ViewConfig
public interface Pages {

    static enum PageConfig {
        @ViewPattern("/admin/*")
        ADMIN,

        @ViewPattern("/pages/*")
        @org.zaunberg.moskitojboss.web.UserIsLoggedIn
        LOGGEDIN,

        @ViewPattern("/*")
        @LoginView("/login.xhtml")
        ALL
    }

}
