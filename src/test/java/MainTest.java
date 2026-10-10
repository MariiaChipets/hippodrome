import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Disabled //Вимкнено, щоб не витрачати час під час загального запуску
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    void main_ShouldExecuteWithinTwentyTwoSeconds() throws Exception {
        Main.main(new String[]{});
    }
}