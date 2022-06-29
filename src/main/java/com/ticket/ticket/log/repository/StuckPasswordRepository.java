package com.ticket.ticket.log.repository;

import com.ticket.ticket.common.BaseRepository;
import com.ticket.ticket.log.entity.StuckPasswordEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 20:23
 */
public interface StuckPasswordRepository extends BaseRepository<StuckPasswordEntity, String> {
    List<StuckPasswordEntity> findAllByStuckPassword(String stuckPassword);

    @Query(value = "DELETE FROM stuck_password where stuckPassword = ?" ,nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByStuckPassword(String stuckPassword);

}
