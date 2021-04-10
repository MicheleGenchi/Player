package it.genchi.springboot.service;



import org.springframework.http.ResponseEntity;

import it.genchi.springboot.model.Giocatore;
import it.genchi.springboot.model.Giocatori;




public interface PlayerService {

    ResponseEntity<Giocatori> getAllPlayers();

    ResponseEntity<String> addPlayer(Giocatore player);

    ResponseEntity<Giocatore> updatePlayer(Giocatore newPlayer);

    ResponseEntity<String> deletePlayer(Long id);

}