import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class TrainConsistManagementAppTest {

    private final TrainConsistService service = new TrainConsistService();

    @Test
    <Bogie>
    void testGrouping_BogiesGroupedByType() {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", "Passenger", 72),
                new Bogie("Sleeper", "Passenger", 72)
        );

        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogies);

        Assertions.assertTrue(result.containsKey("Sleeper"));
        assertEquals(2, result.get("Sleeper").size());
    }

    @Test
    <Bogie>
    void testGrouping_MultipleBogiesInSameGroup() {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("AC Chair", "Passenger", 60),
                new Bogie("AC Chair", "Passenger", 60),
                new Bogie("AC Chair", "Passenger", 60)
        );

        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogies);

        assertEquals(3, result.get("AC Chair").size());
    }

    @Test
    <Bogie>
    void testGrouping_DifferentBogieTypes() {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", "Passenger", 72),
                new Bogie("First Class", "Passenger", 40)
        );

        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogies);

        assertEquals(2, result.size());
    }

    @Test
    <Bogie>
    void testGrouping_EmptyBogieList() {

        List<Bogie> bogies = new ArrayList<>();

        List<Bogie> bogie = List.of();
        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogie);

        assertTrue(result.isEmpty());
    }

    @Test
    <Bogie>
    void testGrouping_SingleBogieCategory() {

        List<Bogie> bogies = Collections.singletonList(
                new Bogie("Rectangular", "Goods", 100)
        );

        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogies);

        assertEquals(1, result.size());
        assertTrue(result.containsKey("Rectangular"));
    }

    @Test
    <Bogie>
    void testGrouping_MapContainsCorrectKeys() {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", "Passenger", 72),
                new Bogie("AC Chair", "Passenger", 60),
                new Bogie("First Class", "Passenger", 40)
        );

        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogies);

        assertTrue(result.containsKey("Sleeper"));
        assertTrue(result.containsKey("AC Chair"));
        assertTrue(result.containsKey("First Class"));
    }

    @Test
    <Bogie>
    void testGrouping_GroupSizeValidation() {

        List<Bogie> bogies = Arrays.asList(
                new Bogie("Sleeper", "Passenger", 72),
                new Bogie("Sleeper", "Passenger", 72),
                new Bogie("AC Chair", "Passenger", 60)
        );

        Map<String, List<Bogie>> result =
                service.groupBogiesByType(bogies);

        assertEquals(2, result.get("Sleeper").size());
        assertEquals(1, result.get("AC Chair").size());
    }

    @Test
    <Bogie>
    void testGrouping_OriginalListUnchanged() {

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", "Passenger", 72));
        bogies.add(new Bogie("AC Chair", "Passenger", 60));

        int originalSize = bogies.size();

        service.groupBogiesByType(bogies);

        assertEquals(originalSize, bogies.size());
    }
}