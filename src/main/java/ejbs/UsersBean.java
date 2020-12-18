package ejbs;

import entities.Cliente;
import entities.User;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "UserEJB")
public class UsersBean extends BaseBean<User,Integer> {

    public User authenticate(Integer id, String password) throws Exception {
        User user = find(id);
        if (user != null && user.getPassword().equals(User.hashPassword(password))) {
            return user;
        }
        throw new Exception("Failed logging in with username '" + id + "': unknown username or wrong password");
    }

     public User findByUsername(String username) {
        try{
            List<User> users = (List<User>) em.createNamedQuery("getAllUsers").getResultList();
            for (User user : users) {
                if(user.getUsername().equals(username)){
                    return user;
                }
            }
            return null;
        }catch (Exception e) {
            throw new EJBException("ERROR_FINDING_USER", e);
        }
    }
}
