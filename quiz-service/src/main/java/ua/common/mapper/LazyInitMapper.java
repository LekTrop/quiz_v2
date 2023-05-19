package ua.common.mapper;

import org.hibernate.Hibernate;

import java.util.Collection;

/**
 * @author (ozhytary)
 */
public interface LazyInitMapper {
    default <T> boolean isNotLazyInit(Collection<T> collection) {
        if (Hibernate.isInitialized(collection)) {
            return true;
        }
        return false;
    }
}
