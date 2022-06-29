package com.ticket.ticket.log.repository;

import com.ticket.ticket.common.BaseRepository;
import com.ticket.ticket.log.entity.UserTwoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 14:39
 */
public interface UserTwoRepository extends BaseRepository<UserTwoEntity, String> {


    List<UserTwoEntity> findAllByPhone(String phone);

    List<UserTwoEntity> findAllByStuckPassword(String stuckPassword);

    //添加修改用户信息
    @Query(value = "UPDATE user_two SET uid = ? WHERE phone = ?",nativeQuery = true)
    @Modifying
    @Transactional
    void bindingUid(String uid , String phone);

}
