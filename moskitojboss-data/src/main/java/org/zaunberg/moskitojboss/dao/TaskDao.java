package org.zaunberg.moskitojboss.dao;

import org.zaunberg.moskitojboss.dao.common.JpaGenericDao;
import org.zaunberg.moskitojboss.domain.Task;
import org.zaunberg.moskitojboss.domain.User;
import org.zaunberg.moskitojboss.domain.enums.Category;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@Stateless
public class TaskDao extends JpaGenericDao<Task> {

    public List<Task> findForCategory(Category category) {
        final Map<String, Object> paramMap = createParameterMap(Task.PARAM_CATEGORY, category);
        return findByNamedQuery(Task.QUERY_LOAD_FOR_CATEGORY, paramMap);
    }

    public List<Task> findForUser(User user) {
        final Map<String, Object> paramMap = createParameterMap(Task.PARAM_USERNAME, user.getLogin());
        return findByNamedQuery(Task.QUERY_LOAD_FOR_USER, paramMap);
    }
    
    public List<Task> findForUserAndStatus(User user, boolean status) {
        final Map<String, Object> paramMap = createParameterMap(Task.PARAM_USERNAME, user.getLogin(), Task.PARAM_STATUS, status);
        return findByNamedQuery(Task.QUERY_LOAD_FOR_USER_AND_STATUS, paramMap);
    }
}
