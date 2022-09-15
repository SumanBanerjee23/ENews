package com.organization.ENews.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	long id;
	String roleName;
	
	/*public Role(){}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}*/
	

}
