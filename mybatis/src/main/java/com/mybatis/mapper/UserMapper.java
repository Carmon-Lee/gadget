/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.mybatis.mapper;

import com.mybatis.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liguang
 * @version UserMapper.java, v 0.1 2020年09月01日 9:40 下午
 */

@Repository
public interface UserMapper {

    @Select("select * from t_user")
    List<User> getAllUsers();
}
