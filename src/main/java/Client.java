public class Client {
    private String type;
    private String[] dates;
    private int[] prices;

    public String getTypeClient() { return type; }
    public void setType(String t) {
        this.type = t;
    }

    public String[] getDates() { return dates; }
    public void setDates(String[] d) {
        this.dates = d;
    }

    public int[] getPrices() { return prices; }
    public void setPrices(int[] p) {
        this.prices = p;
    }

    public Client(int[] p){
        this.prices = p;
    }
}