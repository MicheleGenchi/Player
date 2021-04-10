package it.genchi.springboot;
import java.io.IOException;
import java.net.Socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlayersApplication {

	public static boolean hostAvailabilityCheck(String address, int port) {
		try (Socket s = new Socket(address, port)) {
		        return true;
		    } catch (IOException ex) {
		        /* ignore */
		    }
		    return false;
		}
	
	public static void main(String[] args) {
		if (hostAvailabilityCheck("localhost", 3306))
			SpringApplication.run(PlayersApplication.class, args);
		else
			System.out.println("Avviare Mysql, database non presente");
	}

}
