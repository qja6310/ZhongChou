package cn.com.newloading.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import cn.com.newloading.bean.News;
import cn.com.newloading.utils.StringUtil;

public class NewsProvider {

	private static final String T_NEWS = "t_news";
	
	public String queryNewsByKeyword(News news) {
		SQL sql = new SQL();
		sql.SELECT("id,title,content,issueTime").FROM(T_NEWS);
		if(StringUtil.isNotBlank(news.getTitle())) {
			news.setTitle("%"+news.getTitle()+"%");
			sql.WHERE("title like #{title} and content like #{title}");
		}
		sql.ORDER_BY("issueTime desc limit #{start},#{limit}");
		return sql.toString();
	}
	public String queryNewsByKeyword2(News news) {
		SQL sql = new SQL();
		sql.SELECT("id,title,content,issueTime").FROM(T_NEWS);
		if(StringUtil.isNotBlank(news.getTitle())) {
			news.setTitle("%"+news.getTitle()+"%");
			sql.WHERE("title like #{title} and content like #{title}");
		}
		sql.ORDER_BY("issueTime desc");
		return sql.toString();
	}
	public String queryNewsTotalByKeyword(News news) {
		SQL sql = new SQL();
		sql.SELECT("count(*)").FROM(T_NEWS);
		if(StringUtil.isNotBlank(news.getTitle())) {
			news.setTitle("%"+news.getTitle()+"%");
			sql.WHERE("title like #{title} and content like #{title}");
		}
		return sql.toString();
	}
	
}
