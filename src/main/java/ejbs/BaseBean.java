package ejbs;

import exceptions.MyEntityAlreadyExistsException;
import exceptions.MyEntityNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseBean<Entity, PK> {

    @PersistenceContext
    protected EntityManager em;

    public Class<Entity> getEntityClass () {
        var paramType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<Entity>) paramType.getActualTypeArguments()[0];
    }

    public Entity find (PK primaryKey) {
        return em.find(getEntityClass(), primaryKey);
    }

    public String getClassName () {
        return getEntityClass().getSimpleName();
    }

    public String getClassNameLowercase () {
        return getEntityClass().getSimpleName().toLowerCase();
    }

    public Entity findOrFail (PK primaryKey) throws MyEntityNotFoundException {
        var entity = find(primaryKey);

        if (entity == null) {
            throw new MyEntityNotFoundException(
                    getClassName() + "with primary key «" + primaryKey + "» not found."
            );
        }

        return entity;
    }

    public List<Entity> all () {
        return em.createNamedQuery(getClassNameLowercase() + ".all").getResultList();
    }

    //Something you need to do before entering the class
    public void beforeRemove(Entity entity) {

    }

    public void create() throws MyEntityAlreadyExistsException {
    }

    public void remove(PK primaryKey) throws MyEntityNotFoundException {
        var entity = findOrFail(primaryKey);

        beforeRemove(entity);

        em.remove(entity);
    }
}
