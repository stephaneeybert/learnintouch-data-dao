package com.thalasoft.learnintouch.data.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.learnintouch.data.dao.TemplateContainerDao;
import com.thalasoft.learnintouch.data.dao.domain.TemplateContainer;
import com.thalasoft.learnintouch.data.dao.domain.TemplateModel;

@Repository
@Transactional
public class TemplateContainerHibernateDao extends GenericHibernateDao<TemplateContainer, Serializable> implements TemplateContainerDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateContainer> findWithModel(TemplateModel templateModel) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		criteria.addOrder(Order.asc("row")).addOrder(Order.asc("cell"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemplateContainer> findWithModelAndRow(TemplateModel templateModel, int row) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		criteria.add(Restrictions.eq("row", row));
		criteria.addOrder(Order.asc("cell"));
		return criteria.list();
	}

	@Override
	public TemplateContainer findWithModelAndRowAndCell(TemplateModel templateModel, int row, int cell) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		criteria.add(Restrictions.eq("row", row));
		criteria.add(Restrictions.eq("cell", cell));
		criteria.setMaxResults(1);
		return (TemplateContainer) criteria.uniqueResult();
	}

	@Override
	public TemplateContainer findWithModelAndNextCell(TemplateModel templateModel, int row, int cell) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		criteria.add(Restrictions.eq("row", row));
		criteria.add(Restrictions.gt("cell", cell));
		criteria.addOrder(Order.asc("cell"));
		criteria.setMaxResults(1);
		return (TemplateContainer) criteria.uniqueResult();
	}

	@Override
	public TemplateContainer findWithModelAndPreviousCell(TemplateModel templateModel, int row, int cell) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("templateModel", templateModel));
		criteria.add(Restrictions.eq("row", row));
		criteria.add(Restrictions.lt("cell", cell));
		criteria.addOrder(Order.desc("cell"));
		criteria.setMaxResults(1);
		return (TemplateContainer) criteria.uniqueResult();
	}

	@Override
	public int findNbCellsByRow(TemplateModel templateModel) {
		String statement = "select row rowNb, (max(cell) + 1) nbCells from TemplateContainer where templateModel.id = :templateModelId group by row";
		Query query = getSession().createQuery(statement);
		query.setLong("templateModelId", templateModel.getId());
		Long count = (Long) query.list().get(0);
		return count.intValue();
	}

}
