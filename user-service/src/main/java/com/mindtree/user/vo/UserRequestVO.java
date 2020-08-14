package com.mindtree.user.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserRequestVO {

	@NotBlank(message = "User Name Cannot Be Empty!")
	private String name;

	@NotBlank(message = "Email Id Cannot Be Empty!")
	@Email(message = "Email Id Is Not Valid!")
	private String emailId;

	@NotBlank(message = "Mobile Number Cannot Be Empty!")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile Number Is Not Valid!")
	private String mobileNo;

	@NotBlank(message = "Password Cannot Be Empty!")
	@Length(min = 8, max = 24, message = "Password Must Be Between 8 and 24 Characters.")
	private String password;

	public UserRequestVO() {
	}

	public UserRequestVO(String name, String emailId, String mobileNo, String password) {
		this.name = name;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UserRequestVO other = (UserRequestVO) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (mobileNo == null) {
			if (other.mobileNo != null)
				return false;
		} else if (!mobileNo.equals(other.mobileNo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRequestVO [name=" + name + ", emailId=" + emailId + ", mobileNo=" + mobileNo + ", password="
				+ password + "]";
	}
}
