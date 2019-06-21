package prestitoLibri.collection.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import prestitoLibri.collection.classes.Libro;
import prestitoLibri.collection.classes.Metodi;
import prestitoLibri.collection.classes.Prestito;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//System.out.println("Libri");
	
			
			// creazione catalogoLibri
			Collection<Libro> catalogoLibri = new ArrayList<Libro>();
			Path percorsoCatalogo = Paths.get("Catalogo.txt");
			Collection<String> lineeLibri;
			
			
			
			try {
				lineeLibri = Files.lines(percorsoCatalogo).collect(Collectors.toList());
				
				for(String l : lineeLibri) {
					String[] parole= l.split(";");
					
					
					
					int numeroAutori = Integer.parseInt(parole[2]);
					ArrayList<String> autori = new ArrayList<>(); //potrebbe dare problemi perché dentro al for
				
					for(int i = 0; i< numeroAutori; i++) {
						autori.add(parole[3+i]);
					}
					
					
					Libro libro = new Libro(parole[0], parole[1], numeroAutori, autori);
					catalogoLibri.add(libro);
					
					//System.out.println(libro.getTitoloLibro()+ ", "+ libro.getNumeroAutori());
				}
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
		
		
		
		Collection<Prestito> elencoPrestiti = new ArrayList<Prestito>();
		
		//System.out.println(" ");
		//System.out.println("Prestiti");
		
			
			// creazione elencoPrestiti
			
			Path percorsoPrestiti = Paths.get("Prestiti2.txt");
			Collection<String> lineePrestiti;
			
			
			try {
				lineePrestiti = Files.lines(percorsoPrestiti).collect(Collectors.toList());
				
				for(String l : lineePrestiti) {
					String[] parole= l.split(";");
					Date inizioPrestito;
					
					inizioPrestito = new SimpleDateFormat("dd/MM/yyyy").parse(parole[3]);
				
					
					
					
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
					/*
					formatter.setLenient(false);
					usato per trovare un errore nee dati in input su Prestiti
					*/
					Date finePrestito = new Date();
					
					if(parole[4].contentEquals("0")) {
						finePrestito = formatter.parse("01/01/1800");
					}
					else {
						finePrestito = formatter.parse(parole[4]);
					}

					//System.out.println(finePrestito);
					
					
					
					Prestito prestito = new Prestito(parole[0], parole[1], parole[2], inizioPrestito, finePrestito);
					elencoPrestiti.add(prestito);
					
					
					//System.out.println(prestito.getNomeUtente()+ ", "+ prestito.getCognomeUtente());
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		System.out.println(" ");
		Metodi metodo = new Metodi();
		metodo.daRestituire("a", catalogoLibri , elencoPrestiti);
		
		System.out.println(" ");
		metodo.libriUtente("Giulio", "Mattei",catalogoLibri, elencoPrestiti);
		
		System.out.println(" ");
		metodo.storicoUtente("Giulio", "Mattei", elencoPrestiti);
		System.out.println(" ");
		metodo.storicoUtente("Mario", "Bianchi", elencoPrestiti);
		
		System.out.println(" ");
		metodo.utenteInPrestito("8", elencoPrestiti);
		System.out.println(" ");
		metodo.utenteInPrestito("45", elencoPrestiti);
		
		System.out.println(" ");
		metodo.tempoPrestitoMassimo("45", elencoPrestiti);
		System.out.println(" ");
		metodo.tempoPrestitoMassimo("4", elencoPrestiti);
		System.out.println(" ");
		metodo.tempoPrestitoMassimo("100", elencoPrestiti);
		
	}

}
