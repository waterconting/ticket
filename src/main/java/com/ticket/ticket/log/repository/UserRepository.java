package com.ticket.ticket.log.repository;

import com.ticket.ticket.common.BaseRepository;
import com.ticket.ticket.log.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 14:39
 */
public interface UserRepository extends BaseRepository<UserEntity, String> {


    List<UserEntity> findAllByPhone(String phone);

    List<UserEntity> findAllByStuckPassword(String stuckPassword);

    //添加修改用户信息
    @Query(value = "UPDATE user SET uid = ? WHERE phone = ?",nativeQuery = true)
    @Modifying
    @Transactional
    void bindingUid(String uid , String phone);

}
