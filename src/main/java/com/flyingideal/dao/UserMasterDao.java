package com.flyingideal.dao;

import com.flyingideal.entity.UserMaster;
import org.apache.ibatis.annotations.Param;

/**
 * @author yanchao
 * @date 2018/3/26 15:17
 */
public interface UserMasterDao {

    UserMaster getUserByUsername(String username);

    int addUser(UserMaster userMaster);
}
