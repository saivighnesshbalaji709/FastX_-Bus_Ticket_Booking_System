package com.hexaware.fastx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {  // Product Entity class 
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.AUTO)
		private int userId;
		private String name;
		private String email;
		private String password;
		private String gender;
		private long contactNumber;
		private String role; 

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}

		public User(int userId, String name, String email, String password, String gender, long contactNumber,
				String role) {
			super();
			this.userId = userId;
			this.name = name;
			this.email = email;
			this.password = password;
			this.gender = gender;
			this.contactNumber = contactNumber;
			this.role = role;
		}
		/**
		 * @return the userId
		 */
		public int getUserId() {
			return userId;
		}

		/**
		 * @param userId the userId to set
		 */
		public void setUserId(int userId) {
			this.userId = userId;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}

		/**
		 * @param gender the gender to set
		 */
		public void setGender(String gender) {
			this.gender = gender;
		}

		/**
		 * @return the contactNumber
		 */
		public long getContactNumber() {
			return contactNumber;
		}

		/**
		 * @param contactNumber the contactNumber to set
		 */
		public void setContactNumber(long contactNumber) {
			this.contactNumber = contactNumber;
		}

		/**
		 * @return the role
		 */
		public String getRole() {
			return role;
		}

		/**
		 * @param role the role to set
		 */
		public void setRole(String role) {
			this.role = role;
		}
		@Override
		public String toString() {
			return "user [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
					+ gender + ", contactNumber=" + contactNumber + ", role=" + role + "]";
		}
	
	

}
	




