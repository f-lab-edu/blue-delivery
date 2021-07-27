package com.bluedelivery.user.infra;

import org.apache.ibatis.annotations.Mapper;

import com.bluedelivery.user.domain.UserRepository;

@Mapper
public interface UserMapper extends UserRepository {
}
