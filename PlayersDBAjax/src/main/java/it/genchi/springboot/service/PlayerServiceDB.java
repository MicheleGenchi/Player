package it.genchi.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import it.genchi.springboot.model.Giocatore;
import it.genchi.springboot.model.Giocatori;
import it.genchi.springboot.repositories.PlayersRepository;
import it.genchi.springboot.utils.GiocatoreNotFoundException;



@Service
public class PlayerServiceDB implements PlayerService {
	
	@Autowired 
	private Giocatori giocatori;
	
	@Autowired
    private PlayersRepository playerRepository;

	@Override
    public ResponseEntity<Giocatori> getAllPlayers() {
		giocatori.setGiocatori(playerRepository.findAll());
		return new ResponseEntity<Giocatori>(giocatori, HttpStatus.OK);
    }
	
	
	@Override
    public ResponseEntity<String> addPlayer(Giocatore player) {
		String msg = "Messaggio di default";
		HttpStatus http = HttpStatus.CONTINUE;
		try {
			playerRepository.throw_existsByNome_Cognome(player.getNome(), player.getCognome());
			playerRepository.save(player);
			msg="Giocatore ID="+player.getId()+" aggiunto alla lista";
			http=HttpStatus.CREATED;
		} catch (GiocatoreNotFoundException g) {
			msg=g.getMessage();
			http=HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			msg=e.getMessage();
			http=HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<String>(msg, http);
    }

    @Override
    public ResponseEntity<Giocatore> updatePlayer(Giocatore newPlayer) {
    	return new ResponseEntity<Giocatore>(playerRepository.save(newPlayer), HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> deletePlayer(Long id) {
		String messaggio;
		try {
			getByID(id);
			playerRepository.deleteById(id);
			return new ResponseEntity<>("Cancellato correttamente", HttpStatus.OK);
		} catch (GiocatoreNotFoundException e) {
			messaggio=e.getMessage();
		} 
		return new ResponseEntity<>(messaggio, HttpStatus.NOT_FOUND);
	}

	public Giocatore getByID(Long id) throws GiocatoreNotFoundException {
		return playerRepository.findById(id).orElseThrow(() -> new GiocatoreNotFoundException(id));
	}
}
