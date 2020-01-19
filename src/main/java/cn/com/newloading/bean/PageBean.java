package cn.com.newloading.bean;

public class PageBean{

	private int current;//当前页
	private int limit;//限制数
	private int start;//开始点,例如current = 1,limit=10,那么start = (current - 1) * limit,数据就是从第1~10
	
	public PageBean() {
		
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}
	
}
