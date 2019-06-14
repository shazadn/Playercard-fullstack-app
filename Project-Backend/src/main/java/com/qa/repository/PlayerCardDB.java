package com.qa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.model.PlayerCard;

@Transactional(value = TxType.SUPPORTS)
public class PlayerCardDB implements PlayerCardRepository{

	@PersistenceContext(unitName = "myPU")
	private EntityManager em;
	
	@Transactional(value = TxType.REQUIRED)
	public PlayerCard create(PlayerCard pCard) {
		em.persist(pCard);
		return pCard;
	}

	public PlayerCard read(int id) {
		PlayerCard pCard = em.find(PlayerCard.class,id);
		return pCard;
	}

	public List<PlayerCard> readAll() {
		String query = "Select pla from PlayerCard pla";
		TypedQuery<PlayerCard> q = em.createQuery(query, PlayerCard.class);
		List<PlayerCard> list = q.getResultList();
		return list;
	}

	
	@Transactional(value = TxType.REQUIRED)
	public PlayerCard update(int id, PlayerCard newInfo) {
		PlayerCard pCard = read(id);
		pCard.setName(newInfo.getName());
		pCard.setGamerTag(newInfo.getGamerTag());
		pCard.setCharacter(newInfo.getCharacter());
		return pCard;
	}

	@Transactional(value = TxType.REQUIRED)
	public void delete(int id) {
		em.remove(read(id));
		
	}

}
