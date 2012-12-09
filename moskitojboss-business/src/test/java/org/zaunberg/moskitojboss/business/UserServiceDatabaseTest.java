package org.zaunberg.moskitojboss.business;

import de.akquinet.jbosscc.needle.annotation.InjectInto;
import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import junit.framework.Assert;
import org.junit.Test;
import org.zaunberg.moskitojboss.dao.AbstractDaoTest;
import org.zaunberg.moskitojboss.dao.TaskDao;
import org.zaunberg.moskitojboss.dao.UserDao;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.domain.enums.Category;

import javax.persistence.EntityManager;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
public class UserServiceDatabaseTest extends AbstractDaoTest {

    @ObjectUnderTest
    @InjectInto(targetComponentId = "userService")
    private final UserDao userDao = new UserDao();

    @ObjectUnderTest
    @InjectInto(targetComponentId = "userService")
    private final TaskDao taskDao = new TaskDao();

    @ObjectUnderTest
    private final UserService userService = new UserService();

    // -------------- test methods --------------------------------------------------------

    @Test
    public void testUserService() throws Exception {

        final User user = new User("xXx", "vin", "disel", "triple@xXx.com","secret");

        Assert.assertEquals(0, userService.loadAll().size());

        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                userService.saveUser(user);
            }
        });

        Assert.assertEquals(1, userService.loadAll().size());
    }

    @Test
    public void testRemoveUser() throws Exception {

        Assert.assertEquals(0, userService.loadAll().size());

        // first create and save a user
        final User user1 = new User("xXx", "vin", "disel", "triple@xXx.com", "secret");
        final User user2 = new User("spiderman", "peter", "parker", "peter.parker@marvel.com", "secret");

        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                userService.saveUser(user1);
                userService.saveUser(user2);
            }
        });

        final Task u1_task1 = new Task("aa1", Category.BUSINESS, false, user1);
        final Task u1_task2 = new Task("aa2", Category.PRIVATE, true, user1);
        final Task u1_task3 = new Task("aa3", Category.TODO, false, user1);

        final Task u2_task1 = new Task("bb1", Category.BUSINESS, false, user2);
        final Task u2_task2 = new Task("bb2", Category.BUSINESS, true, user2);

        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                transactionHelper.persist(u1_task1);
                transactionHelper.persist(u1_task2);
                transactionHelper.persist(u1_task3);

                transactionHelper.persist(u2_task1);
                transactionHelper.persist(u2_task2);
            }
        });

        Assert.assertEquals(2, userService.loadAll().size());
        Assert.assertEquals(5, taskDao.loadAll().size());

        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                userService.removeUser(user1);
            }
        });

        Assert.assertEquals(1, userService.loadAll().size());
        Assert.assertEquals(2, taskDao.loadAll().size());
        Assert.assertNull(taskDao.load(u1_task1.getId()));
        Assert.assertNotNull(taskDao.load(u2_task1.getId()));
        Assert.assertNotNull(taskDao.load(u2_task2.getId()));

    }

}
