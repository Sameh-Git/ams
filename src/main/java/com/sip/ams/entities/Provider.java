package com.sip.ams.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name")
	private String name;
	@NotBlank(message = "Address is mandatory")
	@Column(name = "address")
	private String address;
	@NotBlank(message = "Email is mandatory")
	@Column(name = "email")
	private String email;
	
	@Column(name = "providerlogo")
	private String providerlogo;

	public String getProviderlogo() {
		return providerlogo;
	}

	public void setProviderlogo(String providerlogo) {
		this.providerlogo = providerlogo;
	}

	public Provider() {
	}

	public Provider(String name, String address, String email,String providerlogo) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.providerlogo= providerlogo;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "provider")
	private List<Article> articles;
	public List<Article> getArticles() {
	return articles;
	}
	public void setArticles(List<Article> articles) {
	this.articles = articles;
	}

}
