package com.thalasoft.learnintouch.data.dao.oracle.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.domain.Photo;
import com.thalasoft.learnintouch.data.dao.hibernate.PhotoHibernateDao;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

@Repository
@Transactional
public class PhotoCustomHibernateDao extends PhotoHibernateDao {

	@Override
	public Page<Photo> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
//		String pattern = "%" + searchPattern + "%";
//		Criterion reference = Restrictions.ilike("reference", pattern);
//		Criterion name = Restrictions.ilike("name", pattern);
//		Criterion description = Restrictions.ilike("description", pattern);
//		Criterion textCommentt = Restrictions.ilike("textComment", pattern);
//		Criterion image = Restrictions.ilike("image", pattern);
//		Disjunction disjunction = Restrictions.disjunction();
//		disjunction.add(reference).add(name).add(description).add(textCommentt).add(image);
//		Criteria criteria = getSession().createCriteria(getPersistentClass());
//		criteria.add(disjunction);
//      OrderList orderList = new OrderList().add(Order.asc("photoAlbum")).add(Order.asc("listOrder"));
//		Page<Photo> page = getPage(pageNumber, pageSize, criteria);
//		return page;

		String pattern = "%" + searchPattern + "%";
		String query = "from Photo where lower(reference) like lower(:reference) or lower(name) like lower(:name) or lower(description) like lower(:description) or lower(textComment) like lower(:textComment) or lower(image) like lower(:image) order by coalesce(photoAlbum, '0'), listOrder";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("reference", pattern);
		parameters.put("name", pattern);
		parameters.put("description", pattern);
		parameters.put("textComment", pattern);
		parameters.put("image", pattern);
		Page<Photo> page = getPage(pageNumber, pageSize, query, parameters, getSession());
		return page;
	}

}
