import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    public void constructor_WhenListIsNull_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    public void constructor_WhenListIsEmpty_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    public void getHorses_ShouldReturnsSameHorsesInSameOrder() {
        List<Horse> expectedHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expectedHorses.add(new Horse("Horse" + i, 2.5));
        }
        Hippodrome hippodrome = new Hippodrome(expectedHorses);
        List<Horse> actualHorses = hippodrome.getHorses();
        assertEquals(expectedHorses, actualHorses);
    }

    @Test
    public void move_ShouldCallMoveMethodOnAllHorse() {
        List<Horse> mockedHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseMock = Mockito.mock(Horse.class);
            mockedHorses.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(mockedHorses);
        hippodrome.move();
        for (Horse horseMock : mockedHorses) {
            Mockito.verify(horseMock).move();
        }
    }

    @Test
    public void getWinner_ShouldReturnHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse1", 2.5, 10.0);
        Horse horse2 = new Horse("Horse2", 2.5, 11.0);
        Horse horse3 = new Horse("Horse3", 2.5, 12.0);
        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horse3, hippodrome.getWinner());
    }
}