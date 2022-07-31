package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.PhotoDao;
import com.thalasoft.learnintouch.data.dao.domain.Photo;
import com.thalasoft.learnintouch.data.dao.domain.PhotoAlbum;
import com.thalasoft.learnintouch.data.dao.domain.PhotoFormat;
import com.thalasoft.learnintouch.data.dao.hibernate.pagination.Page;
import com.thalasoft.learnintouch.data.utils.OrderList;

@Repository
@Transactional
public class PhotoHibernateDao extends GenericHibernateDao<Photo, Serializable> implements PhotoDao {

	private static final String DB_TABLE_PHOTO_ALBUM = "photoAlbum";

	@Override
	public Page<Photo> findAll(int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        OrderList orderList = new OrderList().add(Order.asc("photoAlbum")).add(Order.asc("listOrder"));
		Page<Photo> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Photo> findWithReference(String reference, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("reference", reference));
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("photoAlbum")).add(Order.asc("listOrder"));
		Page<Photo> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Photo> findWithPhotoAlbum(PhotoAlbum photoAlbum, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (photoAlbum != null) {
		    conjunction.add(Restrictions.eq("photoAlbum", photoAlbum));
		} else {
		    conjunction.add(Restrictions.isNull("photoAlbum"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("listOrder"));
		Page<Photo> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> findWithPhotoAlbumOrderById(PhotoAlbum photoAlbum) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (photoAlbum != null) {
			criteria.add(Restrictions.eq("photoAlbum", photoAlbum));
		} else {
			criteria.add(Restrictions.isNull("photoAlbum"));
		}
		criteria.addOrder(Order.asc("id"));
		return criteria.list();
	}

	@Override
	public Page<Photo> findWithPhotoFormat(PhotoFormat photoFormat, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
		if (photoFormat != null) {
		    conjunction.add(Restrictions.eq("photoFormat", photoFormat));
		} else {
		    conjunction.add(Restrictions.isNull("photoFormat"));
		}
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("photoAlbum")).add(Order.asc("listOrder"));
		Page<Photo> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Photo findWithListOrder(PhotoAlbum photoAlbum, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (photoAlbum != null) {
			criteria.add(Restrictions.eq("photoAlbum", photoAlbum));
		} else {
			criteria.add(Restrictions.isNull("photoAlbum"));
		}
		criteria.add(Restrictions.eq("listOrder", listOrder));
		criteria.setMaxResults(1);
		return (Photo) criteria.uniqueResult();
	}

	@Override
	public Photo findNextWithListOrder(PhotoAlbum photoAlbum, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (photoAlbum != null) {
			criteria.add(Restrictions.eq("photoAlbum", photoAlbum));
		} else {
			criteria.add(Restrictions.isNull("photoAlbum"));
		}
		criteria.add(Restrictions.gt("listOrder", listOrder));
		criteria.addOrder(Order.asc("listOrder"));
		criteria.setMaxResults(1);
		return (Photo) criteria.uniqueResult();
	}

	@Override
	public Photo findPreviousWithListOrder(PhotoAlbum photoAlbum, int listOrder) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		if (photoAlbum != null) {
			criteria.add(Restrictions.eq("photoAlbum", photoAlbum));
		} else {
			criteria.add(Restrictions.isNull("photoAlbum"));
		}
		criteria.add(Restrictions.lt("listOrder", listOrder)).addOrder(Order.desc("listOrder")).setMaxResults(1);
		return (Photo) criteria.uniqueResult();
	}

	@Override
	public long countListOrderDuplicates(PhotoAlbum photoAlbum) {
		String statement = "select count(distinct p1.id) as count from Photo p1, Photo p2 where p1.id != p2.id and p1.listOrder = p2.listOrder and ";
		if (photoAlbum != null) {
			statement += "p1.photoAlbum.id = p2.photoAlbum.id and p1.photoAlbum.id = :photoAlbumId";
		} else {
			statement += "p1.photoAlbum.id is null and p2.photoAlbum.id is null";
		}
		Query query = getSession().createQuery(statement);
		if (photoAlbum != null) {
			query.setLong("photoAlbumId", photoAlbum.getId());
		}
		Long count = (Long) query.list().get(0);
		return count.longValue();
	}
	
	@Override
	public Page<Photo> findWithPatternLike(String searchPattern, int pageNumber, int pageSize) {
		String pattern = "%" + searchPattern + "%";
		Criterion reference = Restrictions.ilike("reference", pattern);
		Criterion name = Restrictions.ilike("name", pattern);
		Criterion description = Restrictions.ilike("description", pattern);
		Criterion textCommentt = Restrictions.ilike("textComment", pattern);
		Criterion image = Restrictions.ilike("image", pattern);
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(reference).add(name).add(description).add(textCommentt).add(image);
		Criteria criteria = getSession().createCriteria(getPersistentClass());
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(disjunction);
        criteria.add(conjunction);
        OrderList orderList = new OrderList().add(Order.asc("photoAlbum")).add(Order.asc("listOrder"));
		Page<Photo> page = getPage(pageNumber, pageSize, criteria, orderList);
		return page;
	}

	@Override
	public Page<Photo> findWithPhotoAlbumAndImage(String album, String image, int pageNumber, int pageSize) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "p");
		criteria.createAlias(DB_TABLE_PHOTO_ALBUM, "pa", CriteriaSpecification.INNER_JOIN);
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("pa.name", album))
		.add(Restrictions.eq("p.image", image));
        criteria.add(conjunction);
		Page<Photo> page = getPage(pageNumber, pageSize, criteria);
		return page;
	}

}
