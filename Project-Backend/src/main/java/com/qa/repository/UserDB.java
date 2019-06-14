package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.User;

@Transactional(value = TxType.SUPPORTS)
public class UserDB implements UserRepository {
	
	@PersistenceContext(unitName = "myPU")
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public User create(User user) {
		em.persist(user);
		return user;
	}

	public User read(int id) {
		User user = em.find(User.class,id);
		return user;
	}

	public List<User> readAll() {
		String query = "Select use from User use";
		TypedQuery<User> q = em.createQuery(query, User.class);
		List<User> list = q.getResultList();
		return list;
	}

	@Transactional(value = TxType.REQUIRED)
	public User update(int id, User newInfo) {
		User user = read(id);
		user.setUserName(newInfo.getUserName());
		System.out.println(read(user.getId()).getUserName());
		return user;
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(int id) {
		em.remove(read(id));
		
	}
	

}
