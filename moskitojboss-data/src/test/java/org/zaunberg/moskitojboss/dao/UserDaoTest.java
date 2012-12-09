package org.zaunberg.moskitojboss.dao;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import junit.framework.Assert;
import org.junit.Test;
import org.zaunberg.moskitojboss.domain.User;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 */
public class UserDaoTest extends AbstractDaoTest {

    @ObjectUnderTest
    private final UserDao userDao = new UserDao();

    /**
     * Must fail because user reference is not set.
     *
     * @throws Exception
     */
    @Test(expected = ConstraintViolationException.class)
    public void testFailSaveTask() throws Exception {

        final User user = new User(null, "vin", "diesel", "triple@xXx.com","secret");

        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                userDao.persist(user);
            }
        });
    }

    @Test
    public void testSaveTask() throws Exception {

        // first create and save a user
        final User user = new User("xXx", "vin", "diesel", "triple@xXx.com","secret");

        Assert.assertEquals(0, userDao.loadAll().size());

        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                userDao.persist(user);
            }
        });

        Assert.assertEquals(1, userDao.loadAll().size());
    }

    @Test(expected = ConstraintViolationException.class)
    public void testFailOnWronEMail() throws Exception {

        // first create and save a user
        final User user = new User("xXx", "vin", "diesel", "triple@","secret");
        transactionHelper.executeInTransaction(new VoidRunnable() {
            @Override
            public void doRun(final EntityManager entityManager) throws Exception {
                transactionHelper.persist(user);
            }
        });

        Assert.fail("sould not have been reached!");
    }

}
