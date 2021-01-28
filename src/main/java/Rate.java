public class Rate {
    private int rateWeek;
    private int rateWeekend;

	public int getRateWeek() { return rateWeek; }
    public void setRateWeek(int rw) {
        this.rateWeek = rw;
    }

    public int getRateWeekend() { return rateWeekend; }
    public void setRateWeekend(int rwnd) {
        this.rateWeekend = rwnd;
    }

    public Rate(int week, int weekend) {
        this.rateWeek = week;
        this.rateWeekend = weekend;

	}
}
