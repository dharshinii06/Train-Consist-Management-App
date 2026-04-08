import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainConsistService<Bogie> {

    public List<Bogie> filterBogiesByCapacity(List<Bogie> bogies, int threshold) {

        return bogies.stream()
                .filter(b -> getCapacity() > threshold)
                .collect(Collectors.toList());
    }

    private int getCapacity() {
        int capacity = 0;
        return capacity;
    }

    public Map<String, List<Bogie>> groupBogiesByType(List<Bogie> bogies) {
        return bogies.stream()
                .collect(Collectors.groupingBy(Bogie::getType));
    }
}