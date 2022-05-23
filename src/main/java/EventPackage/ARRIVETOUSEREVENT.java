package EventPackage;


public class ARRIVETOUSEREVENT extends Event {
    String CaptainName;
    String UserName;

    public ARRIVETOUSEREVENT(String captinName, String userName) {
        CaptainName = captinName;
        UserName = userName;
    }

    public String getCaptainName() {
        return CaptainName;
    }

    public void setCaptainName(String captainName) {
        CaptainName = captainName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String toString() {
        return "-Arrived to User Event{" +
                "CaptinName='" + CaptainName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", name='" + name + '\'' +
                ", DateTime=" + DateTime.format(super.myFormatObj) +
                '}';
    }
}
