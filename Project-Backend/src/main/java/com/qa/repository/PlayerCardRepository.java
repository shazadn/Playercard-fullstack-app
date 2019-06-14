package com.qa.repository;

import java.util.List;

import com.qa.model.PlayerCard;

public interface PlayerCardRepository {

	public PlayerCard create(PlayerCard pCard);
	
	public PlayerCard read(int id);
	public List<PlayerCard> readAll();	
	
	public PlayerCard update(int id, PlayerCard pCard);
	
	public void delete(int id);
}
