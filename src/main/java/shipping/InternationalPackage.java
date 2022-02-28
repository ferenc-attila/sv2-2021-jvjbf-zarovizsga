package shipping;

public class InternationalPackage implements Transportable {

    private static final int INTERNATIONAL_BASE_PRICE = 1200;

    private int weight;
    private boolean breakable;
    private String destinationCountry;
    private int distance;

    public InternationalPackage(int weight, boolean breakable, String destinationCountry, int distance) {
        this.weight = weight;
        this.breakable = breakable;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
    }

    @Override
    public int calculateShippingPrice() {
        if (breakable) {
            return calculateDistancePrice() + (INTERNATIONAL_BASE_PRICE * 2);
        }
        return calculateDistancePrice() + INTERNATIONAL_BASE_PRICE;
    }

    private int calculateDistancePrice() {
        return distance * 10;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public int getDistance() {
        return distance;
    }
}
