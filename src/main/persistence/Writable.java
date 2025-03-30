package persistence;

import org.json.JSONObject;

// represents a class that can be written as JSONObject
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
