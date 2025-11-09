package com.coderscampus.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Class name = User, DB Table name = user
@Table(name = "users")
public class User {
	private Long userId;
	private String username;
	private String password;
	private String name;
	private List<Account> accounts = new ArrayList<>();
	private Address address;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany
	@JoinTable(name = "user_account",
	           joinColumns = @JoinColumn(name = "user_id"),
	           inverseJoinColumns = @JoinColumn(name = "account_id"))
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	@OneToOne(mappedBy = "user")
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", accounts=" + accounts +
				", address=" + address +
				'}';
	}

}