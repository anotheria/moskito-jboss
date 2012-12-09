package org.zaunberg.moskitojboss.business;

import net.anotheria.moskito.integration.cdi.Monitor;
import org.zaunberg.moskitojboss.business.counter.TaskCounter;
import org.zaunberg.moskitojboss.dao.TaskDao;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.domain.enums.Category;

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
public class TaskService implements Serializable {

    @Inject
    private TaskDao taskDao;

    @Inject
    private TaskCounter taskCounter;

    // -------------- interface method implementations ------------------------------------

    /**
     * Generate random Tasks for demo purpose.
     */
    public void generateTasks(final User user) {
        for (int i = 0; i < 10; i++) {
            Task task = new Task("generated Task # " + i, Category.randomCategory(), false, user);
            saveTask(task);
        }
    }

    public void saveTask(Task task) {
        count(task);
        taskDao.persist(task);
    }

    public Task mergeTask(Task task) {
        return taskDao.merge(task);
    }

    public void removeTask(Task task) {
        taskDao.delete(loadTask(task.getId()));
    }

    public boolean taskExists(long id) {
        return taskDao.exists(id);
    }

    public Task loadTask(long id) {
        return taskDao.load(id);
    }

    public List<Task> loadAll() {
        return taskDao.loadAll();
    }

    public List<Task> loadForCategory(Category category) {
        return taskDao.findForCategory(category);
    }

    public List<Task> loadForUser(User user) {
        return taskDao.findForUser(user);
    }

    public List<Task> loadForUserAndStatus(User user, boolean status) {
        return taskDao.findForUserAndStatus(user, status);
    }

    public void count(Task task) {
        switch (task.getCategory()) {
            case BUSINESS:
                taskCounter.countBusiness();
                break;
            case PRIVATE:
                taskCounter.countPrivate();
                break;
            case TODO:
                taskCounter.countTodo();
                break;
            case IMPORTANT:
                taskCounter.countImportant();
                break;
        }
    }
}
