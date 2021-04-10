package it.genchi.springboot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.genchi.springboot.model.Giocatore;
import it.genchi.springboot.model.Giocatori;
import it.genchi.springboot.service.PlayerServiceDB;
import it.genchi.springboot.utils.GiocatoreNotFoundException;

@RestController
@RequestMapping("/players")
public class ControllerPlayers {

	@Autowired
	PlayerServiceDB players;

	@GetMapping("/lista")
	public ResponseEntity<Giocatori> getAllPlayers() {
		// System.out.println("Ricaricamento della tabella dei giocatori...");
		return players.getAllPlayers();
	}

	@GetMapping("/trova/{id}")
	public Giocatore getOnePlayer(@PathVariable(value = "id") Long id) {
		try {
			return players.getByID(id);
		} catch (GiocatoreNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Update Giocatore
	@PutMapping("/aggiorna/{id}")
	public ResponseEntity<Giocatore> updateGiocatore(@PathVariable(value = "id") Long id,
			@Valid @RequestBody Giocatore newGiocatore) throws GiocatoreNotFoundException {
		Giocatore oldGiocatore = players.getByID(id);
		if (oldGiocatore == null)
			return null;
		return players.updatePlayer(newGiocatore);
	}

	@PostMapping(path = "/new")
	public ResponseEntity<String> addPlayer(@Valid @RequestBody Giocatore giocatore) {
		return players.addPlayer(giocatore);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		return players.deletePlayer(id);
	}

}
