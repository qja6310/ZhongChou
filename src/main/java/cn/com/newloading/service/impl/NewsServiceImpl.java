package cn.com.newloading.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.newloading.bean.News;
import cn.com.newloading.dao.mapper.NewsMapper;
import cn.com.newloading.service.NewsService;
import cn.com.newloading.utils.TimeUtil;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public String addNews(News news) {
		// TODO Auto-generated method stub
		news.setIssueTime(TimeUtil.dateToString(new Date()));
		Integer ret = newsMapper.addNews(news);
		if(ret == 1) {
			return "0000";
		}else {
			return "0001";
		}
	}

	@Override
	public List<News> queryNewsByKeyword(String keyword,String current,String limit) {
		// TODO Auto-generated method stub
		News news = new News();
		news.setTitle(keyword);
		news.setCurrent(Integer.valueOf(current));
		news.setLimit(Integer.valueOf(limit));
		int start = (Integer.valueOf(current) - 1) * Integer.valueOf(limit);
		news.setStart(start);
		return newsMapper.queryNewsByKeyword(news);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String delNews(String id) {
		// TODO Auto-generated method stub
		Integer ret = newsMapper.delNews(id);
		if(ret == 1) {
			return "0010";
		}else {
			return "0011";
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String editNews(News news) {
		// TODO Auto-generated method stub
		Integer ret = newsMapper.editNews(news);
		if(ret == 1) {
			return "0010";
		}else {
			return "0011";
		}
	}

	@Override
	public int queryNewsTotalByKeyword(String keyword) {
		// TODO Auto-generated method stub
		News news = new News();
		news.setTitle(keyword);
		return newsMapper.queryNewsTotalByKeyword(news);
	}

	@Override
	public News queryNewsById(String id) {
		// TODO Auto-generated method stub
		return newsMapper.queryNewsById(id);
	}

	@Override
	public List<News> queryNewsByKeyword2(String keyword) {
		// TODO Auto-generated method stub
		News news = new News();
		news.setTitle(keyword);
		List<News> newsList = newsMapper.queryNewsByKeyword2(news);
		for (int i = 0; i < newsList.size(); i++) {
			News n = newsList.get(i);
			if(n.getContent().length() > 100) {
				int index = n.getContent().indexOf("<img");
				if(index > 100) {
					String content = n.getContent().substring(0, 100);
					content += "......[点击查看详情]";
					n.setContent(content);
				}else {
					if(index == -1) {
						String content = n.getContent() + "[图片]......[点击查看详情]";
						n.setContent(content);
					}else {
						String content = n.getContent().substring(0, index);
						content += "[图片]......[点击查看详情]";
						n.setContent(content);
					}
					
				}
			}else {
				String content = n.getContent();
				content += "......[点击查看详情]";
				n.setContent(content);
			}
		}
		return newsList;
	}

}
