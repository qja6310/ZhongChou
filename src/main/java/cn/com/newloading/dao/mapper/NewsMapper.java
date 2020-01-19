package cn.com.newloading.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.com.newloading.bean.News;
import cn.com.newloading.dao.provider.NewsProvider;

public interface NewsMapper {
	
	@Insert("insert into t_news(title,content,issueTime,adminId) values(#{title},#{content},#{issueTime},#{adminId})")
	@Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
	Integer addNews(News news);
	
	@SelectProvider(type = NewsProvider.class,method = "queryNewsByKeyword")
	List<News> queryNewsByKeyword(News news);
	
	@SelectProvider(type = NewsProvider.class,method = "queryNewsByKeyword2")
	List<News> queryNewsByKeyword2(News news);
	
	@SelectProvider(type = NewsProvider.class,method = "queryNewsTotalByKeyword")
	int queryNewsTotalByKeyword(News news);
	
	@Delete("delete from t_news where id = #{id}")
	Integer delNews(@Param("id")String id);
	
	@Update("update t_news set title = #{title},content = #{content},adminId = #{adminId} where id = #{id}")
	Integer editNews(News news);
	
	@Select("select id,title,content,issueTime from t_news where id = #{id}")
	News queryNewsById(@Param("id")String id);
}
