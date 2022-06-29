package com.ticket.ticket.common;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Iterator;

public class BaseBatchUtils<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    private final EntityManager em;
    private int BATCH_SIZE = 500;

    public BaseBatchUtils(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.em = entityManager;
    }

    public BaseBatchUtils(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }

    @Override
    @Transactional
    public Iterable batchSave(Iterable var1) {
        Iterator iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            em.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }
        if (index % BATCH_SIZE != 0) {
            em.flush();
            em.clear();
        }
        return var1;
    }

    @Override
    @Transactional
    public Iterable batchUpdate(Iterable var1) {
        Iterator iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            em.merge(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0) {
                em.flush();
                em.clear();
            }
        }
        if (index % BATCH_SIZE != 0) {
            em.flush();
            em.clear();
        }
        return var1;
    }
}
