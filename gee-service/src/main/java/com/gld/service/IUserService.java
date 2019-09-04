package com.gld.service;

import com.gld.domain.User;

import java.io.Serializable;
import java.util.List;

/**
 * @author qiuzx@gld.com
 * @Description: 用户操作接口
 * Created by qiuzx on 2019-09-03.
 * @since 1.0
 */
public interface IUserService {
    int insert(User entity);
    int deleteById(Serializable id);
    List<User> selectList();

}
