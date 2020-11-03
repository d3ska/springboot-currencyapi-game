package pl.deska.springbootcurrencyapigame.model;

public class Currency {
    private String name;
    private Double rate;
    private String base;
    private String date;

    public Currency(String name, Double rate, String base, String date) {
        this.name = name;
        this.rate = rate;
        this.base = base;
        this.date = date;
    }

    public Currency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
