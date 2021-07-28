package com.bluedelivery.infra.user;

import org.apache.ibatis.annotations.Mapper;

import com.bluedelivery.domain.user.UserRepository;

@Mapper
public interface UserMapper extends UserRepository {
}
