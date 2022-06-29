package com.ticket.ticket.log.repository;

import com.ticket.ticket.common.BaseRepository;
import com.ticket.ticket.log.entity.StuckPasswordTwoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 20:23
 */
public interface StuckPasswordTwoRepository extends BaseRepository<StuckPasswordTwoEntity, String> {
    List<StuckPasswordTwoEntity> findAllByStuckPassword(String stuckPassword);

    @Query(value = "DELETE FROM stuck_password_two where stuckPassword = ?" ,nativeQuery = true)
    @Modifying
    @Transactional
    void deleteByStuckPassword(String stuckPassword);

}
