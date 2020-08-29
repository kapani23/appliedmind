package com.appliedmind.entity.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_SCHEDULE")
public class ProviderScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SCHEDULE_ID")
	private Long userScheduleId;

	@Column(name = "END_TIME")
	private String is;
	
	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

}