package com.thalasoft.learnintouch.data.dao.oracle.hibernate;

import org.hibernate.Query;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.hibernate.MailHibernateDao;

@Repository
@Transactional
public class MailCustomHibernateDao extends MailHibernateDao {

	@Override
	public long deleteByDate(LocalDateTime sinceDateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("YYYY-MM-dd");
		Query query = getSession().createSQLQuery("delete from mail where send_datetime is not null and to_char(send_datetime, 'YYYY-MM-DD') >= :sinceDateTime");
		query.setString("sinceDateTime", dateTimeFormatter.print(sinceDateTime));
		return query.executeUpdate();
	}

}
