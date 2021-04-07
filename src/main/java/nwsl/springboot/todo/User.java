package nwsl.springboot.todo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Setter;
import lombok.Getter;

@Entity
@Setter
@Getter
@Table(name="userdata")
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	@Column(name="id")
	private long id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private Role role;
	
	/* 削除
	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return this.username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	*/
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		;
		return null;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}
	
	//@Override
	public Role getRole() {
		return this.role;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		;
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		;
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		;
		return true;
	}

	@Override
	public boolean isEnabled() {
		;
		return true;
	}
}
