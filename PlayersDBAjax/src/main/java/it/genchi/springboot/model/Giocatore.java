package it.genchi.springboot.model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="giocatore",
indexes = {@Index(name = "nome_cognome", columnList = "nome,cognome")})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Giocatore implements Serializable {
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="nome")
	 @NotNull
	private String nome;
	 @Column(name="cognome")
	 @NotNull
	private String cognome;
	 @Column(name="squadra")
	private String squadra;
	
	public Giocatore() {
		
	}
	
	public Giocatore(Long id, String nome, String cognome, String squadra) {
		this(nome, cognome, squadra);
		this.id=id;
	}
	
	public Giocatore(String nome, String cognome, String squadra) {
		this.nome = nome;
		this.cognome = cognome;
		this.squadra = squadra;
	}
	
	@Override
	public String toString() {
		return String.format("%d%20s%20s%20s", id,nome,cognome,squadra);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getSquadra() {
		return squadra;
	}
	public void setSquadra(String squadra) {
		this.squadra = squadra;
	}
	
	 public void setId(Long id) {
		this.id=id;
	}
	
	 public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Giocatore)) {
			return false;
		}
		Giocatore other = (Giocatore) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(nome, other.nome);
	}

}

