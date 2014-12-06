package net.de1mos.jbox.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

public class GenericDAO<AccessClass> {

	protected Class<AccessClass> type;
	
	@Autowired
	private SessionFactory factory;
	
	protected GenericDAO(Class<AccessClass> targetClass) {
		type = targetClass;
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public void save(AccessClass object) {
		getSession().saveOrUpdate(object);
	}
	
	public void delete(AccessClass object) {
		getSession().delete(object);
	}
	
	public AccessClass get(Long id) {
		AccessClass result = find(id);
		Assert.notNull(result, String.format("%s with id %s not found", type.toString(), id));
		return result;
	}
	
	public AccessClass find(Long id) {
		return type.cast(getSession().get(type, id));
	} 

	protected Session getSession() {
		return factory.getCurrentSession();
	}
}
