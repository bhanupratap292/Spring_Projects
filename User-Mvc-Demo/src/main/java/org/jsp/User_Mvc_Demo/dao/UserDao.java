package org.jsp.User_Mvc_Demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.User_Mvc_Demo.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao {
	@Autowired
	public EntityManager manager;
	public User saveUser(User user) //1
	{
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(user);
		transaction.begin();transaction.commit();
		return user;
		
	}
	public User updateUser(User user) //2
	{
		EntityTransaction transaction = manager.getTransaction();
		manager.merge(user);
		transaction.begin();transaction.commit();
		return user;
	}
	public User findById(int id) //3
	{
		return manager.find(User.class, id);
		
	}
	public boolean deleteUser(int id) //4
	{
		User u = findById(id);
		if(u!=null)
		{
			EntityTransaction transaction = manager.getTransaction();
			manager.remove(u);
			transaction.begin();transaction.commit();
			return true;
		}
		return false;	
	}
	public User verifyUser(int id, String password) //5
	{
		String jpql = "select u from User u where u.id=?1 and u.password=?2";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, id);
		q.setParameter(2, password);
		try
		{
			return (User)q.getSingleResult(); //DC
		}
		catch (NoResultException e) {
			return null;

		}	
	}
	public User verifyUser(long phone, String password) //6
	{
		String jpql = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try
		{
			return (User)q.getSingleResult(); //DC
		}
		catch (NoResultException e) {
			return null;

		}	
	}
	public User verifyUser(String email, String password) //7
	{
		String jpql = "select u from User u where u.email=?1 and u.password=?2";
		Query q = manager.createQuery(jpql);
		q.setParameter(1, email);
		q.setParameter(2, password);
		try
		{
			return (User)q.getSingleResult(); //DC
		}
		catch (NoResultException e) {
			return null;

		}	
	}

}
