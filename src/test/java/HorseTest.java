import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    public void constructor_WhenNameIsNull_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 2.5, 10.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t", "\n"})
    public void constructor_WhenNameIsBlank_ThrowsIllegalArgumentException(String blankName) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(blankName, 2.5, 10.0)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void constructor_WhenSpeedIsNegative_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Bucephalus", -1.5, 10.0)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void constructor_WhenDistanceIsNegative_ThrowsIllegalArgumentException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Bucephalus", 2.5, -10.0)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getName_ShouldReturnNameFromConstructor() {
        String expectedName = "Bucephalus";
        Horse horse = new Horse(expectedName, 2.5, 10.0);
        assertEquals(expectedName, horse.getName());
    }

    @Test
    public void getSpeed_ShouldReturnSpeedFromConstructor() {
        double expectedSpeed = 2.4;
        Horse horse = new Horse("Bucephalus", 2.4, 10.0);
        assertEquals(expectedSpeed, horse.getSpeed());
    }

    @Test
    public void getDistance_ShouldReturnDistanceFromConstructor() {
        double expectedDistance = 10.0;
        Horse horse = new Horse("Bucephalus", expectedDistance, 10.0);
        assertEquals(expectedDistance, horse.getDistance());
    }

    @Test
    public void getDistance_ShouldReturnZeroWhenCreatedWithTwoParameters() {
        Horse horse = new Horse("Bucephalus", 2.4);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    public void move_ShouldCallGetRandomDoubleWithCorrectParameters() {
        Horse horse = new Horse("Bucephalus", 2.4, 10.0);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.7, 0.9})
    void move_ShouldCalculateCorrectDistanceBasedOnFormula(double mockedRandomValue) {
        double speed = 2.5;
        double initialDistance = 10.0;
        Horse horse = new Horse("Bucephalus", speed, initialDistance);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
          horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockedRandomValue);
          horse.move();
          double expectedDistance = initialDistance+speed*mockedRandomValue;
          assertEquals(expectedDistance, horse.getDistance());
        }
    }
}

