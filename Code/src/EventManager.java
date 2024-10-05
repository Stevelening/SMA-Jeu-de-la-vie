import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class EventManager {
    // ====== Attributes ======
    private long currentDate = 0;
    private Map<Long, LinkedList<Event>> events  ;

    // ====== Constructors ======
    public EventManager(){
        this.events = new HashMap<Long, LinkedList<Event>>();
    }
    public EventManager(long currentDate){
        this.currentDate = currentDate;
        this.events = new HashMap<Long, LinkedList<Event>>();
    }

    // ====== Getters ======
    public long getCurrentDate(){
        return currentDate;
    }

    // ====== Setters ======
    public void setCurrentDate(long currentDate){
        this.currentDate = currentDate;
    }

    // ====== Other methods ======
    public void addEvent(Event e){
        if(events.containsKey(e.getDate()))
            this.events.get(e.getDate()).add(e);
        else {
            LinkedList<Event> list = new LinkedList<Event>();
            list.add(e);
            this.events.put(e.getDate(), list);
        }
    }
    public void next(){
        currentDate++;
        if(events.get(this.currentDate) != null) {
            for (Event e : events.get(this.currentDate)) {
                e.execute();
            }
            events.remove(this.currentDate);
        }
    }
    public void restart(){
        this.currentDate = 0;
        events.clear();
    }
    public boolean isFinished(){
        return events.isEmpty();
    }

}
