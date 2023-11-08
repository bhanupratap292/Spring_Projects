package org.jsp.Admin_Hospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.Admin_Hospital.dto.Admin;
import org.jsp.Admin_Hospital.dto.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalDao {
	@Autowired
    private EntityManager manager;
    
    public Hospital addHospital(Hospital h, int admin_id) //add Hospital
    {
    	Admin a = manager.find(Admin.class, admin_id);
    	if(a!=null) {
    		h.setAdmin(a);
    		a.getHospital().add(h);
    	EntityTransaction transaction = manager.getTransaction();
    	manager.persist(h);
    	transaction.begin();transaction.commit();
    	return h;
    	}
		return null; 	
    }
    
    public Hospital updateHospital(Hospital h, int admin_id) //add Hospital
    {
    	Admin a = manager.find(Admin.class, admin_id);
    	if(a!=null) {
    		h.setAdmin(a);
    		a.getHospital().add(h);
    	EntityTransaction transaction = manager.getTransaction();
    	manager.merge(h);
    	transaction.begin();transaction.commit();
    	return h;
    	}
		return null; 	
    }
   
    public List<Hospital> findByName(String name) //Find By Name
    {
    	String qry = "select h from Hospital h where h.name=?1";
    	Query q = manager.createQuery(qry);
    	q.setParameter(1, name);
		return q.getResultList();  	
    }
    
    
}
