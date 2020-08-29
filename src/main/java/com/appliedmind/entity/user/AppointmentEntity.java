package com.appliedmind.entity.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "APPOINTMENT")
public class AppointmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SCHEDULE_ID")
	private Long userScheduleId;

	@Column(name = "REVIEW")
	private String startTime;

	@Column(name = "RATING")
	private String endTime;

	@Column(name = "CREATED_TIME", nullable = false)
	private LocalDateTime createdTime;

	@Column(name = "UPDATED_TIME", nullable = false)
	private LocalDateTime updatedTime;

}