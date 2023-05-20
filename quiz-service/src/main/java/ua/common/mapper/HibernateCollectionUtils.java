package ua.common.mapper;

import org.hibernate.Hibernate;
import org.mapstruct.Condition;

import java.util.Collection;

/**
 * @author (ozhytary)
 */
public class HibernateCollectionUtils {
    @Condition
    public static <T> boolean isCollectionAvailable(Collection<T> collection) {
        if (collection == null) {
            return false;
        }

        return Hibernate.isInitialized(collection);
    }
}
