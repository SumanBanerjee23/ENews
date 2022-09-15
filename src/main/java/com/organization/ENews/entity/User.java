package com.organization.ENews.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long userid;
	@NotBlank(message = "User Name cannot be empty")
	private String username;
	@NotBlank(message = "Password cannot be empty")
	@Size(min = 6, max = 100, message = "Password must be 6 to 30 characters")
	private String password;
	@NotBlank(message = "Email cannot be empty")
	@Email(message="Enter proper Email")
	private String email;
	@NotBlank(message = "Mobile No. cannot be empty")
	@Size(min=10, message="Invalid Mobile No.")
	private String mobileno;
	private String age;
	private String gender;
	private String datecreated;
    @ManyToMany(cascade= CascadeType.ALL,fetch= FetchType.LAZY)
	@JoinTable(name="user_roles",joinColumns=@JoinColumn(name="users_id"),
	inverseJoinColumns=@JoinColumn(name="roles_id"))
	public Set<Role> roles=new HashSet<Role>();



	/*public User() {

	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", mobileno=" + mobileno + ", age=" + age + ", gender=" + gender + ", datecreated=" + datecreated
				+ ", roles=" + roles + "]";
	}

	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}*/

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	/*public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDatecreated() {
		return datecreated;
	}
	public void setDatecreated(String datecreated) {
		this.datecreated = datecreated;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}*/
	
	
	
	
}
