package org.zaunberg.moskitojboss.web.controller;

import org.slf4j.Logger;
import org.zaunberg.moskitojboss.business.TaskService;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.domain.enums.Category;
import org.zaunberg.moskitojboss.web.LoggedInUser;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.zaunberg.moskitojboss.web.PageConstants.HOME_PAGE;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@WebController
public class TaskController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Category categoryFilter;

    @Inject
    private TaskService taskService;

    @Inject
    @LoggedInUser
    private User user;

    @Inject
    private LoginController loginController;


    @Inject
    private Logger log;

    private Task task;
    private Long taskId;
    private List<Task> tasks;

    private boolean hasNewTask = false;
    private boolean hasTaskEditable = false;
    private boolean displayCloseTasks = false;
    private int first = 0;
    private int rows = 10;


// -------------- Initialization ------------------------------------------------------

    @PostConstruct
    public void init() {
    	tasks = new ArrayList<Task>();
        task = new Task();
        hasNewTask = false;
    }


// -------------- Actions -------------------------------------------------------------

    public String reset() {
        hasTaskEditable = false;
        hasNewTask = false;
        task = new Task();

        return HOME_PAGE;
    }

    public void generate() {
        taskService.generateTasks(loginController.getUser());
    }

    public String saveNewTask() {
        // initially new tasks are not finished and not editable
        task.setFinished(false);
        task.setUser(user);

        taskService.saveTask(task);
        log.info("Task was created successfully: {}", task.getLabel());
        this.hasNewTask = false;

        return HOME_PAGE;
    }

    public void removeTask() {
        if (hasNewTask) {
            this.hasNewTask = false;
        }

        if (taskService.taskExists(task.getId())) {
            taskService.removeTask(task);
            log.info("Task was removed successfully: {}", task.getLabel());

            this.hasTaskEditable = false;
        } else {
            log.error("Cannot remove task. It is not in the list: {}", task.getLabel());
        }
    }

    public void updateTask() {
        if (taskService.taskExists(task.getId())) {
            taskService.mergeTask(task);
            log.info("Task was updated successfully: {}", task.getLabel());

            this.hasTaskEditable = false;
        } else {
            log.error("Cannot update task. It is not in the list: {}", task.getLabel());
        }
    }

    public void enableEditMode(Task task) {
        if (task != null) {
            setTask(task);
            this.hasTaskEditable = true;
            log.info("Task edit mode has been started.");

            this.hasNewTask = false;
        } else {
            log.error("Edit mode is not possible.");
        }
    }

    public void disableEditMode() {
        this.hasTaskEditable = false;
        log.info("Task edit mode has been canceled.");
    }

    public String activatedCategory(String category) {
        if (categoryFilter == null) {
            if (category.equals("ALL")) {
                return "active";
            } else {
                return "inactive";
            }
        } else {
            if (category.equals(categoryFilter.name()))
                return "active";
            else
                return "inactive";
        }
    }


// -------------- Controller Helper ---------------------------------------------------

    public void refreshTaskList() {
    	tasks = new ArrayList<Task>();
        //TODO use user instead. Problems with refresh after dealing with couple of users.
        final List<Task> all = taskService.loadForUserAndStatus(loginController.getUser(), displayCloseTasks);
        
        if (categoryFilter != null) {
            for (final Task task : all) {
                if (task.getCategory() == categoryFilter) {
                    tasks.add(task);
                }
            }
        } else {
            tasks = all;
        }    	
    }
    
    
    public List<Task> getTasks() {
    	refreshTaskList();

        return tasks;
    }

    public boolean isTaskListEmpty() {
    	return getTasks().isEmpty();
    }

    
    
    public String forward() {
    	if(isForwardAvailable()) {
    		first = first + rows;
    	}
    	   	
        return null;
    }
     
    public String backward() {
    	if(isBackwardAvailable()) {
    		first = first - rows;
    	}
    	
        return null;
    }
    
// -------------- Getter/Setter -------------------------------------------------------

    public boolean isForwardAvailable() {
    	return (first + rows < tasks.size());
    }
    
    public boolean isBackwardAvailable() {
    	return (first - rows >= 0);
    }
    
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Category getCategoryFilter() {
        return categoryFilter;
    }

    public String setCategoryFilter(final Category categoryFilter) {
        this.categoryFilter = categoryFilter;
        return HOME_PAGE;
    }

    public boolean getHasNewTask() {
        return hasNewTask;
    }

    public void setHasNewTask(final boolean hasNewTask) {
        this.hasNewTask = hasNewTask;

        if (hasNewTask) {
            task = new Task();
        }
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public boolean getHasTaskEditable() {
        return hasTaskEditable;
    }
    /*public void setHasTaskEditable(boolean hasTaskEditable) {
        this.hasTaskEditable = hasTaskEditable;

        if (hasTaskEditable) {
            setHasNewTask(false);
        } else {
            task = new Task();
        }
    }*/

    public boolean getIsTaskEditable(final Task task) {
        return (task != null)
                && getHasTaskEditable()
                && this.task.getId() != null
                && (this.task.getId().equals(task.getId()));
    }

    public boolean isDisplayCloseTasks() {
		return displayCloseTasks;
	}


	public void setDisplayCloseTasks(boolean displayCloseTasks) {
		this.displayCloseTasks = displayCloseTasks;
	}    
    
	public int getFirst() {
		return first;
	}


	public void setFirst(int first) {
		this.first = first;
	}
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}


	// -------------- Listener ------------------------------------------------------------
	public void filterChanged(final ValueChangeEvent event) {
        categoryFilter = (Category) event.getNewValue();
        log.info("Category filter changed to: {}", categoryFilter);
    }
}