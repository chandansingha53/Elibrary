package com.elibrary.model;

// POJO CLASS
public class User {

	private String userName;
	private String userID;
	private String password;
	private String address;
	private String gender;
	private String city;
	private byte[] image;

	public User() {
	}

	public User(String userName, String userID, String password, String address, String gender, String city) {
		super();
		//this.id = id;
		this.userName = userName;
		this.userID = userID;
		this.password = password;
		this.address = address;
		this.gender = gender;
		this.city = city;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	
}
