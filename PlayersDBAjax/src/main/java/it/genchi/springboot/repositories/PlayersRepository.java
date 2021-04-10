package it.genchi.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.genchi.springboot.model.Giocatore;
import it.genchi.springboot.utils.GiocatoreNotFoundException;


@Repository
public interface PlayersRepository extends JpaRepository<Giocatore, Long>{
	
	 
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Giocatore g WHERE g.nome = :nome and g.cognome = :cognome")
	public boolean existsByNome_Cognome(@Param("nome") String nome, @Param("cognome") String cognome);
	

	public default void throw_existsByNome_Cognome(String nome, String cognome) throws GiocatoreNotFoundException {
		if (existsByNome_Cognome(nome, cognome)==true)
			throw new GiocatoreNotFoundException(nome, cognome);
	}
}
