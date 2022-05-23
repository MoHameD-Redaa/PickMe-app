package EventPackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public abstract class Event {
    String name;
    java.time.LocalDateTime DateTime;
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    static int id=1;

    public Event() {

        name="Event"+id++;
        DateTime=LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }
}
