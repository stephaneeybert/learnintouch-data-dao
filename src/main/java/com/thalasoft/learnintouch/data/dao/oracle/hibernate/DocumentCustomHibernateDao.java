package com.thalasoft.learnintouch.data.dao.oracle.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.domain.Document;
import com.thalasoft.learnintouch.data.dao.hibernate.DocumentHibernateDao;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;

@Repository
@Transactional
public class DocumentCustomHibernateDao extends DocumentHibernateDao {

	@Override
	public Page<Document> findPublished(int pageNumber, int pageSize) {
		String query = "from Document where hide != :hide order by coalesce(documentCategory, '0')";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("hide", true);
		Page<Document> page = getPage(pageNumber, pageSize, query, parameters, getSession());
		return page;
	}

}
