package model;

import java.util.Calendar;
import java.util.Date;


//Represents an typing test app event.
public class Event {
    private static final int HASH_CONSTANT = 13;    // constant for hash code generation
    private Date dateLogged;                        // date the event was occured at
    private String description;                     // description of the event that occured
	
    // MODIFIES: this
    // EFFECTS: Creates an event with the given description
    //          and the current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }
	
    // EFFECTS: returns the date of this event
    public Date getDate() {
        return dateLogged;
    }
	
    // EFFECTS: returns the description of this event
    public String getDescription() {
        return description;
    }
	
    @Override
    // EFFECTS: overrides equals() so that only events with the same date and description equal
	public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        
        Event otherEvent = (Event) other;
        
        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }
	
    @Override
    // EFFECTS: ovverides default hashcode to be 13 times the hashcode of the date and description
	public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }
	
    @Override
    // EFFECTS: overrides default string representation to be the date followed by a new line and description
	public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
