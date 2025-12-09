import java.util.Set;

public class Movie {
    private String nimi;
    private String eidr;
    private String kategooria;
    private String priceCategory;

    public Movie(String nimi, String eidr, String kategooria, String priceCategory) {
        this.nimi = nimi;
        this.eidr = eidr;
        this.kategooria = kategooria.toUpperCase();
        this.priceCategory = priceCategory;
    }

    public double getLendingPrice(int days) {
        if (days < 1) {
            return 0;
        }
        return switch (priceCategory) {
            case "Madal" -> 1 + (days - 1) * 0.5;
            case "Keskmine" -> 2 + (days - 1);
            case "Kõrge" -> 4 + (days - 1) * 2;
            default -> 0;
        };
    }

    public double getLendingPrice() {
        return getLendingPrice(1);
    }

    public String getNimi() {
        return nimi;
    }

    public String getEidr() {
        return eidr;
    }

    public String getKategooria() {
        return kategooria;
    }

    @Override
    public String toString() {
        return getNimi() + " " + getEidr() + " " + getKategooria();
    }
}
