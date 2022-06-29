package com.ticket.ticket.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable>
        extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
    <S extends T> Iterable<S> batchSave(Iterable<S> var1);

    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);
}