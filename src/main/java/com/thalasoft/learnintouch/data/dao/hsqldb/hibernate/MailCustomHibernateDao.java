package com.thalasoft.learnintouch.data.dao.hsqldb.hibernate;

import java.sql.Timestamp;

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
		Timestamp tsSinceDateTime = new Timestamp(sinceDateTime.toDateTime().getMillis());
		Query query = getSession().createSQLQuery("delete from mail where send_datetime is not null and cast(send_datetime as date) >= cast(timestamp '" + tsSinceDateTime + "' as date)");
		return query.executeUpdate();
	}

}
