package EventPackage;


public class ARRIVETODESTEVENT extends Event {
    String CaptinName;
    String UserName;

    public ARRIVETODESTEVENT(String captinName, String userName) {
        CaptinName = captinName;
        UserName = userName;
    }

    public String getCaptinName() {
        return CaptinName;
    }

    public void setCaptinName(String captinName) {
        CaptinName = captinName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    @Override
    public String toString() {
        return "-Arrived to destination Event{" +
                "CaptinName='" + CaptinName + '\'' +
                ", UserName='" + UserName + '\'' +
                ", name='" + name + '\'' +
                ", DateTime=" + DateTime.format(super.myFormatObj) +
                '}';
    }
}
