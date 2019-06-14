package com.qa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayerCard {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String gamerTag;
	private String character;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGamerTag() {
		return gamerTag;
	}
	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	
	
}