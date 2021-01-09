package TeamFormation;

public class Owner {
	private String firstName;
	private String surName;
	private String ownerID;
	private String role;
	private String email;
	private String companyID;
	
	public Owner(String firstName, String surName, String ownerID, String role, String email, String companyID) {
		this.firstName = firstName;
		this.surName = surName;
		this.ownerID = ownerID;
		this.role = role;
		this.email = email;
		this.companyID = companyID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSurName() {
		return surName;
	}
	
	public String getOwnerID() {
		return ownerID;
	}
	
	public String getRole() {
		return role;
	}
	public String getEmail() {
		return email;
	}
	
	public String getCompanyID() {
		return companyID;
	}
	public String toString() {
		return new String(
				"FirstName: " + firstName + " SurName: " + surName + " OwnerID: "+ownerID +
				" Role: " + role+"email: "+email+"companyID: "+companyID+"\n");
	}

}
