package com.mindtree.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;
	@Column(name = "mobile_no", nullable = false, unique = true)
	private String mobileNo;

	private String password;
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Location lastKnownLocation;
//	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Preference preference;

	public User() {
	}

//	public User(long id, String name, String emailId, String mobileNo, String password, Location lastKnownLocation,
//			Preference preference) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.emailId = emailId;
//		this.mobileNo = mobileNo;
//		this.password = password;
//		this.lastKnownLocation = lastKnownLocation;
//		this.preference = preference;
//	}

	public User(String name, String emailId, String mobileNo, String password) {
		this.name = name;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

//	public Location getLastKnownLocation() {
//		return lastKnownLocation;
//	}
//
//	public void setLastKnownLocation(Location lastKnownLocation) {
//		this.lastKnownLocation = lastKnownLocation;
//	}
//
//	public Preference getPreference() {
//		return preference;
//	}
//
//	public void setPreference(Preference preference) {
//		this.preference = preference;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
//		result = prime * result + ((lastKnownLocation == null) ? 0 : lastKnownLocation.hashCode());
		result = prime * result + ((mobileNo == null) ? 0 : mobileNo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
//		result = prime * result + ((preference == null) ? 0 : preference.hashCode());
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
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		if (id != other.id)
			return false;
//		if (lastKnownLocation == null) {
//			if (other.lastKnownLocation != null)
//				return false;
//		} else if (!lastKnownLocation.equals(other.lastKnownLocation))
//			return false;
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
//		if (preference == null) {
//			if (other.preference != null)
//				return false;
//		} else if (!preference.equals(other.preference))
//			return false;
		return true;
	}

}
