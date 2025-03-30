package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log of typing test app events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;     // the only EventLog in the system (Singleton Design Pattern)
    private Collection<Event> events;   // collection of all the events that have occurred
	
    // EFFECTS: creates new event log while preventing external construction (Singleton Design Pattern)
    private EventLog() {
        events = new ArrayList<Event>();
    }
	
    // EFFECTS: returns EventLog instance, or creates it if it doesnt already exist (Singleton Design Pattern)
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }
	
    // MODIFIES: this
    // EFFECTS: adds the given event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }
	
    // MODIFIES: this
    // EFFECTS: clears the event log and logs the clearing as an event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }
	
    @Override
    // EFFECTS: returns an iterator over the events in this event log
	public Iterator<Event> iterator() {
        return events.iterator();
    }
}
