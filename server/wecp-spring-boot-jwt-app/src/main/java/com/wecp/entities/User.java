package com.wecp.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	 
	 
	 String userId;
	 
	 String password;
	 
	 String role;
	 
	 Double oustanding = 0d;
	 
	 @JsonIgnore
	 @OneToMany(cascade=CascadeType.ALL)
	  @JoinColumn(name="u_id")
	Set<UserTransaction> transactions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Double getOustanding() {
		return oustanding;
	}

	public void setOustanding(Double oustanding) {
		this.oustanding = oustanding;
	}

	public Set<UserTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<UserTransaction> transactions) {
		this.transactions = transactions;
	}
	 
	 

}