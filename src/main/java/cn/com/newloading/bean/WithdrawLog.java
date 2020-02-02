package cn.com.newloading.bean;

public class WithdrawLog extends PageBean{

	private String id;
	private String projectId;
	private String adminId;
	private String userId;
	private String money;
	private String applyTime;
	private String auditTime;
	private String status;
	private String evidence;
	private String pztjTime;
	private String pzshTime;
	private String pzshAdminId;
	private String yhCardNum;
	
	private String startApplyTime;
	private String endApplyTime;
	private String keyword;
	
	private String userName;
	private String patientName;
	private String phone;
	private String currentMoney;
	private String txAdminName;
	private String pzAdminName;
	
	public WithdrawLog() {
		
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

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStartApplyTime() {
		return startApplyTime;
	}

	public void setStartApplyTime(String startApplyTime) {
		this.startApplyTime = startApplyTime;
	}

	public String getEndApplyTime() {
		return endApplyTime;
	}

	public void setEndApplyTime(String endApplyTime) {
		this.endApplyTime = endApplyTime;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(String currentMoney) {
		this.currentMoney = currentMoney;
	}

	public String getEvidence() {
		return evidence;
	}

	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}

	public String getPztjTime() {
		return pztjTime;
	}

	public void setPztjTime(String pztjTime) {
		this.pztjTime = pztjTime;
	}

	public String getPzshTime() {
		return pzshTime;
	}

	public void setPzshTime(String pzshTime) {
		this.pzshTime = pzshTime;
	}

	public String getPzshAdminId() {
		return pzshAdminId;
	}

	public void setPzshAdminId(String pzshAdminId) {
		this.pzshAdminId = pzshAdminId;
	}

	public String getTxAdminName() {
		return txAdminName;
	}

	public void setTxAdminName(String txAdminName) {
		this.txAdminName = txAdminName;
	}

	public String getPzAdminName() {
		return pzAdminName;
	}

	public void setPzAdminName(String pzAdminName) {
		this.pzAdminName = pzAdminName;
	}

	public String getYhCardNum() {
		return yhCardNum;
	}

	public void setYhCardNum(String yhCardNum) {
		this.yhCardNum = yhCardNum;
	}
	
	
}
