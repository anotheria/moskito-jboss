package org.zaunberg.moskitojboss.business;

import net.anotheria.moskito.integration.cdi.Monitor;
import org.zaunberg.moskitojboss.dao.TaskDao;
import org.zaunberg.moskitojboss.dao.UserDao;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

import static net.anotheria.moskito.integration.cdi.MonitoringCategorySelector.SERVICE;

/**
 * Stateless EJB Session Bean for managing tasks.
 *
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@Monitor(SERVICE)
@Stateless
public class UserService implements Serializable {

    @Inject
    private UserDao userDao;

    @Inject
    private TaskDao taskDao;

// -------------- interface method implementations ------------------------------------

    public void saveUser(User user) {
        userDao.persist(user);
    }

    public void mergeUser(User user) {
        userDao.merge(user);
    }

    public void removeUser(User user) {
        List<Task> tasksToRemove = taskDao.findForUser(user);

        if (!tasksToRemove.isEmpty()) {
            for (Task task : tasksToRemove) {
                taskDao.delete(task);
            }
        }

        userDao.delete(loadById(user.getId()));
    }

    public boolean userExists(final Long userId) {
        return userDao.exists(userId);
    }

    public User loadById(final Long userId) {
        return userDao.load(userId);
    }

    public User loadByUsername(final String username) {
        return userDao.loadByUsername(username);
    }

    public List<User> loadAll() {
        return userDao.loadAll();
    }

}
