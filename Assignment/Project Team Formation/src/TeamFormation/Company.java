package TeamFormation;

public class Company {

	private String companyID;
	private String companyName;
	private String abn;
	private String url;
	private String address;

	Company(String id, String name, String abn, String url, String address) {
		this.companyID = id;
		this.companyName = name;
		this.abn = abn;
		this.url = url;
		this.address = address;
	}

	public String getCompanyID() {
		return companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getAbn() {
		return abn;
	}

	public String getUrl() {
		return url;
	}

	public String getAddress() {
		return address;
	}

	public String toString() {
		return new String(
				" CompanyID: "+companyID+" CompanyName: " + companyName + " ABN: abn" + " Address: " + address+"\n");
	}

}
