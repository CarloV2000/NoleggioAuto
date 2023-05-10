package it.polito.tdp.noleggioAuto;
import java.util.PriorityQueue;

import it.polito.tdp.noleggioAuto.Event.EventType;


public class Simulatore {
	
	//modello del mondo
	private int nAuto;
	
	//parametri di ingresso
	private int NC;//numero auto acquistate
	private int T_IN;// n minuti tra due arrivi di clienti(media)
	private int T_TRAVEL;//durata minima del noleggio(60 min)
	private int MULT_TRAVEL; // quanti t_travel al max posso avere
	
	//indicatori in uscita
	private int TOT_CLIENTI;//n tot clienti arrivati
	private int TOT_INSODDISFATTI;// clienti a cui non ho potuto affittare auto
	
	//tipi di eventi (e coda degli eventi)
	private PriorityQueue<Event>queue;

	public Simulatore(int nC, int t_IN, int t_TRAVEL, int mULT_TRAVEL) {// il costruttore deve avere solo i valori parametri d ingresso!!!
		super();
		NC = nC;
		T_IN = t_IN;
		T_TRAVEL = t_TRAVEL;
		MULT_TRAVEL = mULT_TRAVEL;
		
		
		this.nAuto = this.NC;//inizialmente sono tutte quelle acquistate
		this.TOT_CLIENTI = 0;
		this.TOT_INSODDISFATTI = 0;
		
		this.queue = new PriorityQueue<Event>();
		
	}
	
	public void run() {
		while(!this.queue.isEmpty()) {
			Event e = this.queue.remove();//o .poll() se voglio ritornare null al posto di eccezione in caso di errore
			int time = e.getTime();
			EventType type = e.getType();
			//System.out.println(e.getType()+" al tempo "+ time);//mi stampa per ogni evento il tipo e il tempo

			
			switch(type) {
			case NUOVO_CLIENTE:
				
				if(nAuto>0) {
					//cliente soddisfatto: auto noleggiata
					this.TOT_CLIENTI++;
					this.nAuto--;
					
					int durata = this.T_TRAVEL*(int)(1 + Math.random()*this.MULT_TRAVEL);
					this.queue.add(new Event(time+durata, EventType.RESTITUZIONE_AUTO));
					
				}else {
					//cliente insoddisfatto: auto non noleggiata
					this.TOT_CLIENTI++;
					this.TOT_INSODDISFATTI++;
				}
				break;
				
			case RESTITUZIONE_AUTO:
				
				this.nAuto++;
				
				break;
			}
		}
	}
	
	public void initialize() {// simula arrivo dei clienti (1 ogni T_IN)
		//inserire nella coda 1 o piu eventi(ad es. arrivano clienti)
		for(int time = 0; time < 8*60; time = time + T_IN) { //cosi partendo ta time = 0 arriva un cliente ogni T_IN
			this.queue.add(new Event(time, EventType.NUOVO_CLIENTE));//(ctrl+spazio)
		}
		
	}
	
	//a fine silmulazione devo permettere all'utente di fare get  dei valori (indicatori in uscita)
	public int getTOT_CLIENTI() {
		return TOT_CLIENTI;
	}

	public int getTOT_INSODDISFATTI() {
		return TOT_INSODDISFATTI;
	}
}
