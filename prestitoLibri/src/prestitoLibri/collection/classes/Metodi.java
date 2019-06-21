package prestitoLibri.collection.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


public class Metodi {

	/*
	public void NomeToId(String titoloLibro, Metodi metodo, Collection<Libro> catalogoLibri) {
	
		for(Libro libro : catalogoLibri) {
			if (libro.getTitoloLibro().contains(titoloLibro))
			//metodo()
			}
		}
		 
	}
	*/
	
	
	
	

	
	public void daRestituire(String titoloLibro, Collection<Libro> catalogoLibri, Collection<Prestito> elencoPrestiti) {
		
		boolean flag = false;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataRiferimento = new Date();
		try {
			dataRiferimento = formatter.parse("01/01/1800");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		for(Libro libro : catalogoLibri) {
			if (libro.getTitoloLibro().contains(titoloLibro)) {
				for(Prestito prestito : elencoPrestiti) {
					if(prestito.getIdLibro().contentEquals(libro.getIdLibro()) && prestito.getFinePrestito().equals(dataRiferimento)) {
							flag = true;
					}	
				}
				if(flag){
					System.out.println("Il libro " + libro.getTitoloLibro() + " è da restituire");
				}
				else{
					System.out.println("Il libro " + libro.getTitoloLibro() + " NON è da restituire");
				}
			}
		}
	}


	public Libro daIdaLibro(String idLibro, Collection<Libro> catalogoLibri) {
		Libro libro = new Libro();
		for(Libro l : catalogoLibri) {
			if (l.getIdLibro().equals(idLibro)) {
				libro = l;
			}
		}
		return libro;
	}
	
	
	public void libriUtente(String nomeUtente, String cognomeUtente, Collection<Libro> catalogoLibri, Collection<Prestito> elencoPrestiti) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataRiferimento = new Date();
		try {
			dataRiferimento = formatter.parse("01/01/1800");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Prestito prestito : elencoPrestiti) {
			if(prestito.getNomeUtente().equals(nomeUtente) && prestito.getCognomeUtente().equals(cognomeUtente) && prestito.getFinePrestito().equals(dataRiferimento)) {
				
				Libro libro = daIdaLibro(prestito.getIdLibro(), catalogoLibri);
				System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " ha in prestito il libro " + libro.getTitoloLibro());
			}
		}
	}
	
	
	
	
	public void storicoUtente(String nomeUtente, String cognomeUtente, Collection<Prestito> elencoPrestiti) {
		boolean flag = false;
		
		System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " ha in prestito i libri corrispondenti agli id:");
		
		for(Prestito prestito : elencoPrestiti) {
			if(prestito.getNomeUtente().equals(nomeUtente) && prestito.getCognomeUtente().equals(cognomeUtente)) {
				System.out.println(prestito.getIdLibro());
				flag = true;
			}
		}
		if(!flag) {
			System.out.println("Nessuno");
		}
	}
	
	
	
	
	
	// completare la prima parte
	public void utenteInPrestito(String idLibro, Collection<Prestito> elencoPrestiti) {
		
		boolean flag = false;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataRiferimento = new Date();
		try {
			dataRiferimento = formatter.parse("01/01/1800");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Prestito prestito : elencoPrestiti) {
			if(prestito.getIdLibro().contentEquals(idLibro) && prestito.getFinePrestito().equals(dataRiferimento)) {
			System.out.println("L'utente " + prestito.getNomeUtente() + " " + prestito.getCognomeUtente() + " è in possesso del libro " + idLibro);
			flag = true;
			}	
		}
		if(!flag) {
			System.out.println("Il libro corrispondente all'id "+ idLibro + " NON è in prestito");
		}
	}
	
	
	
	
	
	
	
	/*
		public void utenteInPrestito(String idLibro, Collection<Prestito> elencoPrestiti) {
			
			boolean flag = false;
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date dataRiferimento = new Date();
			try {
				dataRiferimento = formatter.parse("01/01/1800");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(Prestito prestito : elencoPrestiti) {
				if(prestito.getIdLibro().contentEquals(idLibro) && prestito.getFinePrestito().equals(dataRiferimento)) {
				System.out.println("L'utente " + prestito.getNomeUtente() + " " + prestito.getCognomeUtente() + " è in possesso del libro " + idLibro);
				flag = true;
				}	
			}
			if(!flag) {
				System.out.println("Il libro corrispondente all'id "+ idLibro + " NON è in prestito");
			}
		}
		*/
	
	
	
	
	
	public void tempoPrestitoMassimo(String idLibro, Collection<Prestito> elencoPrestiti) {
		long massimo = 0;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date dataRiferimento = new Date();
		try {
			dataRiferimento = formatter.parse("01/01/1800");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dataAttuale = new Date(); 
		for(Prestito prestito : elencoPrestiti) {
			if(prestito.getIdLibro().contentEquals(idLibro)) {

				if(prestito.getFinePrestito().equals(dataRiferimento)) {
					long differenzaDate = Math.abs(dataAttuale.getTime()-prestito.getInizioPrestito().getTime());
							if(differenzaDate > massimo) {
								massimo = differenzaDate;
							}
				}
				else {
					long differenzaDate = Math.abs(prestito.getFinePrestito().getTime()-prestito.getInizioPrestito().getTime());
							if(differenzaDate > massimo) {
								massimo = differenzaDate;
							}
				}
			
			}	
		}
	
		if(massimo == 0) {
			
			/*
			Libro libro = daIdaLibro(prestito.getIdLibro(), catalogoLibri);
			System.out.println("L'utente " + nomeUtente + " " + cognomeUtente + " ha in prestito il libro " + libro.getTitoloLibro());
			*/
			
			System.out.println("Il libro corrispondente all'id "+ idLibro + " non è mai stato preso in prestito");
		}
		else {
			System.out.println("Il libro corrispondente all'id "+ idLibro + " è stato preso in prestito per un periodo massimo di " + massimo/(1000*60*60*24) + " giorni");
		}
	}
	
	
}
