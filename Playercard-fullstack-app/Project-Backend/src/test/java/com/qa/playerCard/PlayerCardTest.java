package com.qa.playerCard;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.model.PlayerCard;
import com.qa.model.User;

public class PlayerCardTest {

	PlayerCard card;
	
	
	@BeforeClass
	public static void initial() {
		System.out.println("Start Report");
	}
	

	@Before
	public void setup() {
		System.out.println("Begin");
		card = new PlayerCard();
	}
	
	
	@Test
	public void cardName() {
		card.setName("Shaz");
		assertEquals("Name error","Shaz", card.getName());
	}
	@Test
	public void cardGamerTag() {
		card.setGamerTag("ninja-killa");
		assertEquals("GamerTag error","ninja-killa", card.getGamerTag());
	}
	@Test
	public void cardCharacter() {
		card.setCharacter("Scorpion");
		assertEquals("Character error","Scorpion", card.getCharacter());
	}
	
	
}
