public class Hotel {
    private String name;
    private Rate rateRegular;
    private Rate rateRewards;
    private byte stars;

    public String getName() { return name; }
    public void setName(String n) {
        this.name = n;
    }

    public Rate getRateRegular() { return rateRegular; }
    public void setRate(Rate r) {
        this.rateRegular = r;
    }

    public Rate getRateRewards() { return rateRewards; }
    public void setRateRewards(Rate r) {
        this.rateRewards = r;
    }

    public byte getStars() { return stars; }
    public void setStars(byte s) {
        this.stars = s;
    }

    public Hotel(String n, Rate regular, Rate rewards, byte s) {
        this.name = n;
        this.rateRegular = regular;
        this.rateRewards = rewards;
        this.stars = s;
	}
}
