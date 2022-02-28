package shipping;

public class NationalPackage implements Transportable {

    private static final int INLAND_BASE_PRICE = 1000;

    private int weight;
    private boolean breakable;

    public NationalPackage(int weight, boolean breakable) {
        this.weight = weight;
        this.breakable = breakable;
    }

    @Override
    public int calculateShippingPrice() {
        if (breakable) {
            return INLAND_BASE_PRICE * 2;
        }
        return INLAND_BASE_PRICE;
    }

    @Override
    public String getDestinationCountry() {
        return Transportable.super.getDestinationCountry();
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }
}
