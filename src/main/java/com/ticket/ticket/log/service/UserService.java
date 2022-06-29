package com.ticket.ticket.log.service;


import com.ticket.ticket.log.entity.UserEntity;

import java.util.List;

/**
 * @Author ZY
 * @Date 2022/6/17 15:03
 */
public interface UserService {

    UserEntity save(UserEntity entity);

    List<String> getStuckPassword(String type, Integer num);

    void bindingUid(String uid, String phone);
}
