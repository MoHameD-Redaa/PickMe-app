package EventPackage;

public class ADDOFFEREVENT extends Event {
    String CaptainName;
    double Price;

    public ADDOFFEREVENT(String captinName, double price) {
        CaptainName = captinName;
        Price = price;
    }

    public String getCaptainName() {
        return CaptainName;
    }

    public void setCaptainName(String captainName) {
        CaptainName = captainName;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "-Captain put Price Event{" +
                "CaptinName = '" + CaptainName + '\'' +
                ", Price = " + Price +
                ", name = '" + name + '\'' +
                ", DateTime = " + DateTime.format(super.myFormatObj) +
                '}';


    }
}
