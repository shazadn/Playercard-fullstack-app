package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.Character;

@Transactional(value = TxType.SUPPORTS)
public class CharacterDB {
	@PersistenceContext(unitName = "myPU")
	private EntityManager em;

	@Transactional(value = TxType.REQUIRED)
	public Character create(Character cha) {
		em.persist(cha);
		return cha;
	}

	public Character read(int id) {
		Character cha = em.find(Character.class, id);
		return cha;
	}

	public List<Character> readAll() {
		String query = "Select cha from Character cha";
		TypedQuery<Character> q = em.createQuery(query, Character.class);
		List<Character> list = q.getResultList();
		return list;
	}

	@Transactional(value = TxType.REQUIRED)
	public Character update(int id, Character newInfo) {
		Character cha = read(id);
		cha.setName(newInfo.getName());
		cha.setBio(newInfo.getBio());
		return cha;
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(int id) {
		em.remove(read(id));

	}

}
