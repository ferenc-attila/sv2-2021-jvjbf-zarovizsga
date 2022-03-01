package shipping;

public interface Transportable {

    String HOME_COUNTRY = "Hungary";

    int getWeight();

    boolean isBreakable();

    int calculateShippingPrice();

    default String getDestinationCountry() {
        return HOME_COUNTRY;
    }
}
