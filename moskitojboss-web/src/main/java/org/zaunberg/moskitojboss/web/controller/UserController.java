package org.zaunberg.moskitojboss.web.controller;

import org.slf4j.Logger;
import org.zaunberg.moskitojboss.business.UserService;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.web.LoggedInUser;
import org.zaunberg.moskitojboss.web.UserChangedEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@WebController
public class UserController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger log;

    @Inject
    private UserService userService;

    @Inject
    private Event<UserChangedEvent> userChangedEvent;

    @Inject
    @LoggedInUser
    private User user;


 // -------------- Initialization ------------------------------------------------------


    // -------------- Actions -------------------------------------------------------------
    public void saveUser() {
        userService.saveUser(user);
        log.info("User was created successfully: {}", user.getLogin());
    }

    public void removeUser() {
        if (userService.userExists(user.getId())) {
            userService.removeUser(user);
            log.info("User was removed successfully: {}", user.getLogin());
        } else {
            log.error("Cannot remove User. User is not in the list: {}", user.getLogin());
        }

    }

    public void updateUser() {
        if (userService.userExists(user.getId())) {
            userService.mergeUser(user);
            userChangedEvent.fire(new UserChangedEvent());
            log.info("User was updated successfully: {}", user.getLogin());
        } else {
            log.error("Cannot update user data. User is not in the list: {}", user.getLogin());
        }
    }


    // -------------- Controller Helper ---------------------------------------------------
    public List<User> getUsers() {
        List<User> users = userService.loadAll();

        return users;
    }


// -------------- Getter/Setter -------------------------------------------------------

    public User getUser() {
        return user;
    }
}
