package org.zaunberg.moskitojboss.dao;

import org.zaunberg.moskitojboss.dao.common.JpaGenericDao;
import org.zaunberg.moskitojboss.domain.User;

import javax.ejb.Stateless;
import java.util.Map;

/**
 * @author <a href="mailto:marek.i@gmx.net">Marek Iwaszkiewicz</a>
 * @author <a href="mailto:michaelschuetz83@gmail.com">Michael Schuetz</a>
 */
@Stateless
public class UserDao extends JpaGenericDao<User> {

    public User loadByUsername(String username) {
        final Map<String, Object> paramMap = createParameterMap(User.PARAM_USERNAME, username);
        return findSingleByNamedQuery(User.QUERY_LOAD_BY_USERNAME, paramMap);
    }

}
