package org.zaunberg.moskitojboss.dao;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import junit.framework.Assert;
import org.junit.Test;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.domain.enums.Category;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.util.List;

public class TaskDaoTest extends AbstractDaoTest {

    @ObjectUnderTest
    private final TaskDao taskDao = new TaskDao();

    /**
     * Must fail because user reference is not set.
     *
     * @throws Exception
     */
    @Test(expected = ConstraintViolationException.class)
    public void testFailSaveTask() throws Exception {
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                final Task task = new Task("xyz", Category.BUSINESS, false, null);
                taskDao.persist(task);
            }
        });
    }

    @Test
    public void testSaveTask() throws Exception {

        // first create and save a user
        final User user = new User("xXx", "vin", "diesel", "triple@xXx.com","secret");
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                transactionHelper.persist(user);
            }
        });

        // we expect the database to be empty
        Assert.assertEquals(0, taskDao.loadAll().size());
        // write access must be executed within a transaction
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                final Task task = new Task("xyz", Category.BUSINESS, false, user);
                taskDao.persist(task);
            }
        });

        Assert.assertEquals(1, taskDao.loadAll().size());
    }

    @Test
    public void testFindForCategory() throws Exception {

        // first create and save a user
        final User user = new User("xXx", "vin", "diesel", "triple@xXx.com","secret");
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                transactionHelper.persist(user);
            }
        });

        final Task task1 = new Task("aaa", Category.TODO, true, user);

        // we expect the database to be empty
        Assert.assertEquals(0, taskDao.loadAll().size());
        // write access must be executed within a transaction
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                final Task task2 = new Task("ccc", Category.BUSINESS, false, user);
                final Task task3 = new Task("bbb", Category.BUSINESS, false, user);
                taskDao.persist(task1);
                taskDao.persist(task2);
                taskDao.persist(task3);
            }
        });

        Assert.assertEquals(3, taskDao.loadAll().size());

        Assert.assertEquals(0, taskDao.findForCategory(Category.PRIVATE).size());
        Assert.assertEquals(2, taskDao.findForCategory(Category.BUSINESS).size());

        final List<Task> tasks = taskDao.findForCategory(Category.TODO);
        Assert.assertEquals(1, tasks.size());
        Assert.assertEquals(task1.getId(), tasks.get(0).getId());
    }

    @Test
    public void testFindForUser() throws Exception {

        // first create and save a user
        final User user1 = new User("xXx", "vin", "diesel", "triple@xXx.com","secret");
        final User user2 = new User("spiderman", "peter", "parker", "peter.parker@marvel.com","secret");
        final User user3 = new User("ironman", "tony", "stark", "tony.stark@starkindustries.com","secret");
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                transactionHelper.persist(user1);
                transactionHelper.persist(user2);
                transactionHelper.persist(user3);
            }
        });

        // we expect the database to be empty
        Assert.assertEquals(0, taskDao.loadAll().size());
        // write access must be executed within a transaction
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                final Task task1 = new Task("aaa", Category.BUSINESS, true, user1);
                final Task task2 = new Task("bbb", Category.TODO, false, user1);
                final Task task3 = new Task("ccc", Category.BUSINESS, false, user2);
                taskDao.persist(task1);
                taskDao.persist(task2);
                taskDao.persist(task3);
            }
        });

        Assert.assertEquals(3, taskDao.loadAll().size());

        Assert.assertEquals(2, taskDao.findForUser(user1).size());
        Assert.assertEquals(1, taskDao.findForUser(user2).size());
        Assert.assertEquals(0, taskDao.findForUser(user3).size());
    }
}
