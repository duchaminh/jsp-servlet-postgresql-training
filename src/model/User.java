package model;

public class User {
	private String userId;
	
	private String password;
	
	private String familyName;
	
	private String firstName;
	
	private int genderId;
	
	private int age;
	
	private int authority;
	
	private int admin;
	
	private String createUserId;
	
	private String updateUserID;
	
	private long createDate;
	
	private long updateDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String username) {
		this.userId = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getGenderId() {
		return genderId;
	}

	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserID() {
		return updateUserID;
	}

	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}

	public long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}

	public long getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(long updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		return "User [username=" + userId + ", password=" + password + ", familyName=" + familyName + ", firstName="
				+ firstName + ", genderId=" + genderId + ", age=" + age + ", authority=" + authority + ", admin="
				+ admin + ", createUserId=" + createUserId + ", updateUserID=" + updateUserID + ", createDate="
				+ createDate + ", updateDate=" + updateDate + "]";
	}
	
}
