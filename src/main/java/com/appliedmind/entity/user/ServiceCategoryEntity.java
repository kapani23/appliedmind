package com.appliedmind.entity.user;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SERVICE_CATEGORY")
public class ServiceCategoryEntity {

	@Id
	@Column(name = "SERVICE_CATEGORY_ID")
	private Long serviceCategoryId;

	// Teaching, Wellness & Health
	@Column(name = "SERVICE_CATEGORY_NAME")
	private String serviceCategoryName;

//	@OneToOne(targetEntity = UserServicesEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinColumn(name = "USER_SERVICES_ID", referencedColumnName = "USER_SERVICES_ID")
//	private UserServicesEntity userServicesEntity;

	public Long getServiceCategoryId() {
		return serviceCategoryId;
	}

	public void setServiceCategoryId(Long serviceCategoryId) {
		this.serviceCategoryId = serviceCategoryId;
	}

	public String getServiceCategoryName() {
		return serviceCategoryName;
	}

	public void setServiceCategoryName(String serviceCategoryName) {
		this.serviceCategoryName = serviceCategoryName;
	}

//	public UserServicesEntity getUserServicesEntity() {
//		return userServicesEntity;
//	}
//
//	public void setUserServicesEntity(UserServicesEntity userServicesEntity) {
//		this.userServicesEntity = userServicesEntity;
//	}

}
