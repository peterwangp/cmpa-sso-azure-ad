package org.cmpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println ( "*** main() ***");
        Logger lgr = Logger.getLogger(Application.class.getName());
        lgr.info( "Application starting up..." );
        // pre-load the PostgreSQL db driver
        try {
            Class.forName("org.postgresql.Driver");
            lgr.info( "Registered PostGreSQL Driver org.postgresql.Driver");
        } catch ( java.lang.ClassNotFoundException ex ) {
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        SpringApplication.run(Application.class, args);
    }
}

