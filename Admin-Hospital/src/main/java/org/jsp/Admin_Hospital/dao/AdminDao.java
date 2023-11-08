package org.jsp.Admin_Hospital.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.Admin_Hospital.dto.Admin;
import org.jsp.Admin_Hospital.dto.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class AdminDao {
	@Autowired
	 private  EntityManager manager;
	   
	     public Admin save(Admin a) //save_Admin
	     {
	    	 EntityTransaction transaction = manager.getTransaction();
	    	 manager.persist(a);
	    	 transaction.begin();transaction.commit();
			return a;
	    	 
	     }
	     public Admin update(Admin a) //update_Admin
	     {
	    	 EntityTransaction transaction = manager.getTransaction();
	    	 manager.merge(a);
	    	 transaction.begin();transaction.commit();
			return a;
	    	 
	     }
	    
	     public Admin verify(long phone, String password) //vetifyByPhone
	     {
	        String qry = "select a from Admin a where a.phone=?1 and a.password=?2";
	        Query q = manager.createQuery(qry);
	        q.setParameter(1, phone);
	        q.setParameter(2, password);
	        try
	        {
	        	return (Admin) q.getSingleResult();
	        }
	        catch (NoResultException e) {
	        	System.err.println("Invalid Phone / Password");
	        	return null;
			}
			
	    	 
	     }
	     public Admin verify(String email, String password) //vetifyByEmail
	     {
	        String qry = "select a from Admin a where a.email=?1 and a.password=?2";
	        Query q = manager.createQuery(qry);
	        q.setParameter(1, email);
	        q.setParameter(2, password);
	        try
	        {
	        	return (Admin) q.getSingleResult();
	        }
	        catch (NoResultException e) {
	        	System.err.println("Invalid Email / Password");
	        	return null;
			}	 
	     }
	     
	     public List<Hospital> findHospitalByAdminId(int id)
	     {
	    	 Admin a = manager.find(Admin.class, id);
	    	 if(a!=null)
	    	 {
	    		 return a.getHospital();
	    	 }
	    	 else
	    	 {
	    		 return null;
	    	 }
	    	
	     }

}
