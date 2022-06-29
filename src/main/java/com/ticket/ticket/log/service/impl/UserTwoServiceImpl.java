package com.ticket.ticket.log.service.impl;

import com.ticket.ticket.common.SnowflakerServiceHelperBase;
import com.ticket.ticket.log.entity.StuckPasswordTwoEntity;
import com.ticket.ticket.log.entity.UserTwoEntity;
import com.ticket.ticket.log.repository.StuckPasswordTwoRepository;
import com.ticket.ticket.log.repository.UserTwoRepository;
import com.ticket.ticket.log.service.UserTwoService;
import com.ticket.ticket.utils.DateUtils;
import com.ticket.ticket.utils.StringUtils;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author ZY
 * @Date 2022/6/17 15:29
 */
@Service
public class UserTwoServiceImpl extends SnowflakerServiceHelperBase implements UserTwoService {
    @Resource
    private UserTwoRepository userTwoRepository;
    @Resource
    private StuckPasswordTwoRepository stuckPasswordTwoRepository;

    @Override
    public UserTwoEntity save(UserTwoEntity entity) {
        if (StringUtils.isNotEmpty(entity.getStuckPassword())) {
            List<StuckPasswordTwoEntity> stuckPasswordList = stuckPasswordTwoRepository.findAllByStuckPassword(entity.getStuckPassword());
            if (stuckPasswordList.isEmpty()) {
                throw new DataAccessResourceFailureException("卡密：" + entity.getStuckPassword() + "不存在，请联系管理员！");
            }
            List<UserTwoEntity> userEntityList = userTwoRepository.findAllByStuckPassword(entity.getStuckPassword());
            if (!userEntityList.isEmpty()) {
                throw new DataAccessResourceFailureException("卡密：" + entity.getStuckPassword() + "已经注册，请使用其他卡密！");
            }
            List<UserTwoEntity> list = userTwoRepository.findAllByPhone(entity.getPhone());
            if (!list.isEmpty()) {
                if (list.size() == 1) {
                    UserTwoEntity UserTwoEntity = list.get(0);
                    Date date = new Date(); // 当前时间

                    // 获取绑定次数
                    int i = 0;
                    if (UserTwoEntity.getTryNumber() != null) {
                        i = Integer.parseInt(UserTwoEntity.getTryNumber());
                    }


                    String stuckPassword = entity.getStuckPassword();
                    String useTimeType = stuckPassword.substring(18);
                    entity.setUseTimeType(useTimeType);
                    entity.setId(UserTwoEntity.getId());
                    switch (useTimeType) {
                        case "1":
                            entity.setEndTime(DateUtils.AddMin(date, 10));
                            if (i == 0) {
                                throw new DataAccessResourceFailureException("试用次数已用完，如需继续使用请购买，谢谢！！！");
                            } else {
                                i = i - 1;
                                entity.setTryNumber(String.valueOf(i));
                            }
                            break;
                        case "2":
                            entity.setEndTime(DateUtils.AddMin(date, 30));
                            if (i == 0) {
                                throw new DataAccessResourceFailureException("试用次数已用完，如需继续使用请购买，谢谢！！！");
                            } else {
                                i = i - 1;
                                entity.setTryNumber(String.valueOf(i));
                            }
                            break;
                        case "3":
                            entity.setEndTime(DateUtils.AddHours(date, 1));
                            if (i == 0) {
                                throw new DataAccessResourceFailureException("试用次数已用完，如需继续使用请购买，谢谢！！！");
                            } else {
                                i = i - 1;
                                entity.setTryNumber(String.valueOf(i));
                            }
                            break;
                        case "4":
                            entity.setEndTime(DateUtils.addDay(date, 1));
                            if (i == 0) {
                                throw new DataAccessResourceFailureException("试用次数已用完，如需继续使用请购买，谢谢！！！");
                            } else {
                                i = i - 1;
                                entity.setTryNumber(String.valueOf(i));
                            }
                            break;
                        case "5":
                            entity.setEndTime(DateUtils.addDay(date, 7));
                            break;
                        case "6":
                            entity.setEndTime(DateUtils.addDay(date, 31));
                            break;
                        case "7":
                            entity.setEndTime(DateUtils.getAddYeaForDate(date, 100));
                            break;
                    }
                } else {
                    throw new DataAccessResourceFailureException("手机号：" + entity.getPhone() + "已经注册，请联系管理员！");
                }
            } else {
                // 第一次注册
                Date date = new Date(); // 当前时间
                entity.setId(snowflakeIdWorkerBeanBase.getId());

                String stuckPassword = entity.getStuckPassword();
                String useTimeType = stuckPassword.substring(18);
                entity.setUseTimeType(useTimeType);
                switch (useTimeType) {
                    case "1":
                        entity.setEndTime(DateUtils.AddMin(date, 10));
                        entity.setTryNumber("1");
                        break;
                    case "2":
                        entity.setEndTime(DateUtils.AddMin(date, 30));
                        entity.setTryNumber("1");
                        break;
                    case "3":
                        entity.setEndTime(DateUtils.AddHours(date, 1));
                        entity.setTryNumber("1");
                        break;
                    case "4":
                        entity.setEndTime(DateUtils.addDay(date, 1));
                        entity.setTryNumber("1");
                        break;
                    case "5":
                        entity.setEndTime(DateUtils.addDay(date, 7));
                        entity.setTryNumber("2");
                        break;
                    case "6":
                        entity.setEndTime(DateUtils.addDay(date, 31));
                        entity.setTryNumber("2");
                        break;
                    case "7":
                        entity.setEndTime(DateUtils.getAddYeaForDate(date, 100));
                        entity.setTryNumber("2");
                        break;
                }
            }
            // 修改卡密状态
            stuckPasswordTwoRepository.deleteByStuckPassword(entity.getStuckPassword());
            return userTwoRepository.save(entity);
        } else {
            List<UserTwoEntity> list = userTwoRepository.findAllByPhone(entity.getPhone());
            if (list.isEmpty()) {
                throw new DataAccessResourceFailureException("手机号：" + entity.getPhone() + "已经注册，请联系管理员！");
            } else {
                return userTwoRepository.save(list.get(0));
            }
        }
    }

    @Override
    public List<String> getStuckPassword(String type, Integer num) {
        ArrayList<String> list = new ArrayList<>();
        if (num == null) {
            String charAndNum = getCharAndNum(18) + type;
            StuckPasswordTwoEntity entity = new StuckPasswordTwoEntity();
            entity.setId(snowflakeIdWorkerBeanBase.getId());
            entity.setStuckPassword(charAndNum);
            try {
                stuckPasswordTwoRepository.save(entity);
                list.add(charAndNum);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            int num1 = num;
            for (int i = 0; i < num1; i++) {
                String charAndNum = getCharAndNum(18) + type;
                StuckPasswordTwoEntity entity = new StuckPasswordTwoEntity();
                entity.setId(snowflakeIdWorkerBeanBase.getId());
                entity.setStuckPassword(charAndNum);
                try {
                    stuckPasswordTwoRepository.save(entity);
                    list.add(charAndNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public void bindingUid(String uid, String phone) {
        try {
            userTwoRepository.bindingUid(uid, phone);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessResourceFailureException("绑定失败");
        }
    }

    public static String getCharAndNum(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                val += (char) (65 + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
}
