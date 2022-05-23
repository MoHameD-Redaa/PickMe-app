package EventPackage;

public class ACCEPTOFFEREVENT extends Event {
    String Username;
    String captainName;

    public ACCEPTOFFEREVENT(String username, String captainName) {

        Username = username;
        this.captainName = captainName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @Override
    public String toString() {
        return "-User Accept offer Event{" +
                "Username = '" + Username + '\'' +
                "CaptainName = '" + captainName + '\'' +
                ", name = '" + name + '\'' +
                ", DateTime = " + DateTime.format(super.myFormatObj) +
                '}';
    }
}
