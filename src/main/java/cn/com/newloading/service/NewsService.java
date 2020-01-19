package cn.com.newloading.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.newloading.bean.News;

public interface NewsService {

	String addNews(News news);
	
	List<News> queryNewsByKeyword(String keyword,String current,String limit);
	
	List<News> queryNewsByKeyword2(String keyword);
	
	String delNews(@Param("id")String id);
	
	String editNews(News news);
	
	int queryNewsTotalByKeyword(String keyword);
	
	News queryNewsById(String id);
	
}
