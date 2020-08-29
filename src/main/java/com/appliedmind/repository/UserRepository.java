package com.appliedmind.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appliedmind.entity.user.UserProfileEntity;

@Repository
public interface UserRepository
		extends CrudRepository<UserProfileEntity, Long>, JpaSpecificationExecutor<UserProfileEntity> {

	public UserProfileEntity findByEmail(String email);

	public UserProfileEntity findByPhone(String phone);

}
