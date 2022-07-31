package com.thalasoft.learnintouch.data.dao.h2.hibernate;

import org.hibernate.Query;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.hibernate.MailHibernateDao;

@Repository
@Transactional
public class MailCustomHibernateDao extends MailHibernateDao {

	@Override
	public long deleteByDate(LocalDateTime sinceDateTime) {
		Query query = getSession().createQuery("delete from Mail where sendDatetime is not null and formatdatetime(sendDatetime, 'yyyy/MM/dd') >= formatdatetime(?, 'yyyy/MM/dd')");
		query.setTimestamp(0, sinceDateTime.toDateTime().toDate());
		return query.executeUpdate();
	}

}
