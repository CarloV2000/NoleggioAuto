package it.polito.tdp.noleggioAuto;
import java.util.Objects;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		NUOVO_CLIENTE,
		RESTITUZIONE_AUTO
	}
	
	private int time;//minuti (da inizio giornata) dell'evento
	private EventType type; // tipo di evento
	
	public Event(int time, Event.EventType type) {
		super();
		this.time = time;
		this.type = type;
	}
	
	public int getTime() {
		return time;
	}
	public EventType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(time, type);
	}

	@Override
	public int compareTo(Event o) {//priority queue!!!!!
		// TODO Auto-generated method stub
		return this.time-o.time;
	}

	
	
}
