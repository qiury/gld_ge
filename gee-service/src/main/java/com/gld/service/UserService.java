package com.gld.service;

import com.gld.mappers.UserMapper;
import com.gld.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 用户操作服务类
 * Created by qiuzx on 2019-09-03.
 * @Version 1.0
 * @Author qiuzx@gld.com
 */
@Service
public class UserService implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int deleteById(Serializable id) {
        return userMapper.deleteById(id);
    }

    @Override
    public List<User> selectList() {
        return userMapper.selectList(null);
    }
}
