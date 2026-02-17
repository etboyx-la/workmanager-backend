package com.example.workmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.workmanager.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}