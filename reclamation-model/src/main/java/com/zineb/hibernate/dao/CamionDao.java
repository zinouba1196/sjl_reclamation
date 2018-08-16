package com.zineb.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.zineb.hibernate.entity.Camion;

public class CamionDao implements DaoInterface<Camion,Integer> {
	
	private Session currentSession;
	private Transaction currentTransaction;
	
	public CamionDao(){}

	public Session openCurrentSession(){
		currentSession=getSessionFactory().openSession();
		return currentSession;
		
	}
	
	public Session openCurrentSessionwithTransaction(){
		currentSession=getSessionFactory().openSession();
		currentTransaction=currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession(){
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction(){
		currentTransaction.commit();
		currentSession.close();
	}
	
	 public Session getCurrentSession() {
		 return currentSession;
    }
		 
     public void setCurrentSession(Session currentSession) {
		 this.currentSession = currentSession;
	 }
		 
	 public Transaction getCurrentTransaction() {
	      return currentTransaction;
	 }
		 
	 public void setCurrentTransaction(Transaction currentTransaction) {
		  this.currentTransaction = currentTransaction;
	 }
		 
		  

	
	// Method Used to create the hibernate's SessionFactory Object
	public static SessionFactory getSessionFactory(){
		//Creating Configuration Instance and passing hibernate configuration file 
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
		
		//Creating Hibernate Session Factory Instance 
		SessionFactory factoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		
		return factoryObj;
		
	}

	@Override
	public void persist(Camion entity) {
		getCurrentSession().persist(entity);
		
	}

	@Override
	public void update(Camion entity) {
		getCurrentSession().persist(entity);
		
	}

	@Override
	public Camion findbyId(Integer id) {
		Camion camion = getCurrentSession().get(Camion.class,id);
		return camion;
	}

	@Override
	public void delete(Camion entity) {
		getCurrentSession().delete(entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Camion> findAll() {
		List<Camion> camions = (List<Camion>)getCurrentSession().createQuery("from Camion").list();
		return camions;
	}

	@Override
	public void deleteAll() {
		List<Camion> entityList = findAll();
		for(Camion entity : entityList){
			delete(entity);
		}
		
	}

}