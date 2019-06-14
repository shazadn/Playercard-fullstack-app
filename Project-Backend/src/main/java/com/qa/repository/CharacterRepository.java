package com.qa.repository;

import java.util.List;

import com.qa.model.Character;

public interface CharacterRepository {

	public Character create(Character cha);
	
	public Character read(int id);
	public List<Character> readAll();	
	
	public Character update(int id, Character cha);
	
	public void delete(int id);
}
