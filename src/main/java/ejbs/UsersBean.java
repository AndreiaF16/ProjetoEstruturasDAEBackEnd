package ejbs;

import entities.User;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "UserEJB")
public class UsersBean {
    @PersistenceContext
    EntityManager em;

    public User find(String id) {
        try{
            return em.find(User.class, id);
        }catch (Exception e) {
            throw new EJBException("ERROR_FINDING_USER", e);
        }
    }

    public User authenticate(final String username, final String password) throws
            Exception {
        User user = find(username);
        if (user != null &&
                user.getPassword().equals(User.hashPassword(password))) {
            return user;
        }
        throw new Exception("Failed logging in with username '" + username + "': unknown username or wrong password");
    }
}
