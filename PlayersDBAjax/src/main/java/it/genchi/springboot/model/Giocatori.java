package it.genchi.springboot.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class Giocatori {
	
	private List<Giocatore> giocatori;

	public Giocatori() {
		
	}
	
	public List<Giocatore> getGiocatori() {
		if (giocatori==null) {
			return new ArrayList<Giocatore>();
		}
		return giocatori;
	}

	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}
	
	
}
