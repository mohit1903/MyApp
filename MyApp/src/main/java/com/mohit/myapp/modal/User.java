package com.mohit.myapp.modal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	
	@Column(name="name" , nullable=false)
	private String Name;
	@Column(name="password" , nullable=false)
	private String password;
	@Column(name="email" , nullable=false)
	private String email;
	
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "confirmation_token")
	private String confirmationToken;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
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
	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getConfirmationToken() {
		return confirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Id;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((confirmationToken == null) ? 0 : confirmationToken.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (Id != other.Id)
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (confirmationToken == null) {
			if (other.confirmationToken != null)
				return false;
		} else if (!confirmationToken.equals(other.confirmationToken))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	

}
