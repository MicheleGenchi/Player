package it.genchi.springboot.utils;

import java.sql.SQLException;

public class GiocatoreNotFoundException extends SQLException {

	private static final long serialVersionUID = 1L;

	private long id;
	
	public GiocatoreNotFoundException(long id) {
	    super(String.format("Giocatore con questo '%s' non è stato trovato ", id));
	}
	
	public GiocatoreNotFoundException(String nome, String cognome) {
        super(String.format("Giocatore con nome '%s' e cognome '%s' già esiste", nome, cognome));
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
