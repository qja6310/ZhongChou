package cn.com.newloading.bean;

public class DonationLog {

	private String id;
	private String projectId;
	private String userId;
	private String money;
	private String donationTime;
	
	public DonationLog() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDonationTime() {
		return donationTime;
	}

	public void setDonationTime(String donationTime) {
		this.donationTime = donationTime;
	}
	
}
