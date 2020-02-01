package cn.com.newloading.bean;

/**
 * 普通用户
 * @author 35030
 *
 */
public class UserInfo extends PageBean{
	
	private String id;
	private String name;
	private String age;
	private String sex;
	private String role;
	private String phone;
	private String password;
	private String status;
	private String createTime;//审核时间
	
	private String btgxmCount;//不通过项目的个数
	
	public UserInfo() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBtgxmCount() {
		return btgxmCount;
	}

	public void setBtgxmCount(String btgxmCount) {
		this.btgxmCount = btgxmCount;
	}
	
	
	
}
