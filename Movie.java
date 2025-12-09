import java.util.Set;

public class Movie {
    private String nimi;
    private String eidr;
    private String kategooria;
    private String priceCategory;
    private static Set<String> eidrs = new java.util.HashSet<>();

    public Movie(String nimi, String eidr, String kategooria, String priceCategory) {
        if (eidrs.contains(eidr)) {
            throw new IllegalArgumentException("EIDR already exists: " + eidr);
        }
        this.nimi = nimi;
        this.eidr = eidr;
        this.kategooria = kategooria.toUpperCase();
        this.priceCategory = priceCategory;
        eidrs.add(eidr);
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
        return switch (priceCategory) {
            case "Madal" -> 1;
            case "Keskmine" -> 2;
            case "Kõrge" -> 4;
            default -> 0;
        };
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
