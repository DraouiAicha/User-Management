package Entity;

public class User {
	private String firstName;
	private String lastName;
	private String loginId;
	private String password;
	private String dateOfBirth;
	private String mobileN;
	public User(String firstName, String lastName, String loginId, String password, String dateOfBirth, String mobileN) {
		super();
		this.firstName = firstName;
		this.lastName=lastName;
		this.loginId = loginId;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.mobileN = mobileN;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileN() {
		return mobileN;
	}
	public void setMobileN(String mobileN) {
		this.mobileN = mobileN;
	}
	@Override
	public String toString() {
		return "User [First Name=" + firstName + ", lastName=" + lastName + ", Login Id=" + loginId + ", Password=" +password + ", Date Of Birth="+ dateOfBirth + ", Mobile No="+ mobileN +"]";
	}
}
