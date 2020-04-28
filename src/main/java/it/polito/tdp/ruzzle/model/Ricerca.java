package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	
	public List<Pos> trovaParola(String parola, Board board) {
		for(Pos p:board.getPositions()) {
			if(board.getCellValueProperty(p).get().charAt(0)==(parola.charAt(0))) {
				//potenziale inizio della parola che cerco, invoco ricorsione passando questa posizione
				List<Pos> percorso= new ArrayList<>();
				percorso.add(p);
				if(cerca(parola,1,percorso,board))
					return percorso;
				
			}
		}
		return null;
	}

	private boolean cerca(String parola, int livello, List<Pos> percorso, Board board) {
		//caso terminale
		if(livello==parola.length()) {
			return true;
		}
		
		Pos ultima=percorso.get(percorso.size()-1);//prendo l'ultima posizione corretta che ho trovato
		List<Pos> adiacenti=board.getAdjacencies(ultima);//data l'ultima posizione prendo quelle adiacenti 
		//per scorrerle e vedere se tra queste è contenuta la lettera che cerco
		for(Pos p:adiacenti) {
			//controllo di non averla usata e controllo che parola.charAt(livello)==la lettera dentro la casella della board
			if(!percorso.contains(p) && parola.charAt(livello)==board.getCellValueProperty(p).get().charAt(0)) {
				percorso.add(p);
				if(cerca(parola,livello+1,percorso, board))
					return true;//uscita rapida in caso io trovi una soluzione che soddisfa
				percorso.remove(percorso.size()-1);
			}
		}
		return false;
	}
}
