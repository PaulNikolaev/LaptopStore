import java.util.HashSet;
import java.util.Set;

public class LaptopStore {
    private Set<Laptop> laptops;

    public LaptopStore() {
        laptops = new HashSet<>();
    }

    public void addLaptop(Laptop laptop) {
        laptops.add(laptop);
    }

    public Set<Laptop> getLaptops() {
        return laptops;
    }

}
