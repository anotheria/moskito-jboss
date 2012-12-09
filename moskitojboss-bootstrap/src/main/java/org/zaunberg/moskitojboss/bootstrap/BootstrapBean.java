package org.zaunberg.moskitojboss.bootstrap;

import org.slf4j.Logger;
import org.zaunberg.moskitojboss.dao.TaskDao;
import org.zaunberg.moskitojboss.dao.UserDao;
import org.zaunberg.moskitojboss.dao.common.JpaGenericDao;
import org.zaunberg.moskitojboss.domain.AbstractEntity;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.domain.enums.Category;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Initialize Data.
 *
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */

@Startup
@Singleton
public class BootstrapBean {

    @Inject
    private Logger log;

    @Inject
    private TaskDao taskDao;

    @Inject
    private UserDao userDao;

    @PostConstruct
    public void insert() {
        log.debug("Setup data in data base.");

        initTasks();

        log.debug("Test data has been set up successfully.");
    }


    private void initTasks() {

        final User jimUser = new User("jim", "Jim", "Thomson", "jimthomson@mymail.org", "secret");

        final Task task1 = new Task("Call Simon.", Category.TODO, true, jimUser);
        final Task task2 = new Task("Finish shop project", Category.BUSINESS, false, jimUser);
        final Task task3 = new Task("Check Code for feature A.", Category.BUSINESS, false, jimUser);
        final Task task4 = new Task("Visit parents", Category.PRIVATE, false, jimUser);
        final Task task5 = new Task("Finish estimation.", Category.BUSINESS, false, jimUser);
        final Task task6 = new Task("Book flight to Rome.", Category.IMPORTANT, false, jimUser);

        save(userDao, jimUser);
        save(taskDao, task1, task2, task3, task4, task5, task6);
    }

    private <T extends AbstractEntity> void save(final JpaGenericDao<T> dao, final T... entities) {
        for (final T entity : entities) {
            dao.persist(entity);
        }
    }
}
