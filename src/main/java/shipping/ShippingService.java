package shipping;

import java.util.*;

public class ShippingService {

    private List<Transportable> packages = new ArrayList<>();

    public void addPackage(Transportable transportable) {
        packages.add(transportable);
    }

    public List<Transportable> getPackages() {
        return List.copyOf(packages);
    }

    public List<Transportable> collectItemsByBreakableAndWeight(boolean breakable, int weight) {
        return packages.stream()
                .filter(transportable -> transportable.isBreakable() == breakable)
                .filter(transportable -> transportable.getWeight() >= weight)
                .toList();
    }

    public Map<String, Integer> collectTransportableByCountry() {
        Map<String, Integer> result = new HashMap<>();
        for (Transportable actual : packages) {
            if (result.containsKey(actual.getDestinationCountry())) {
                result.put(actual.getDestinationCountry(), result.get(actual.getDestinationCountry()) + 1);
            } else {
                result.put(actual.getDestinationCountry(), 1);
            }
        }
        return result;
    }

    public List<Transportable> sortInternationalPackagesByDistance() {
        return new ArrayList<>(packages.stream()
                .filter(transportable -> !Transportable.HOME_COUNTRY.equals(transportable.getDestinationCountry()))
                .map(InternationalPackage.class::cast)
                .sorted(Comparator.comparingInt(InternationalPackage::getDistance))
                .toList());
    }
}
