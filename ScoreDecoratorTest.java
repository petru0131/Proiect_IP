import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

class ScoreDecoratorTest {
    ScoreDecorator decorator = new ScoreDecorator();
    @Test
    void decorate() {
        decorator.decorate();
        String output = captureSystemOut();
        assertTrue(output.contains("Scorul prezent este: " + GameManager.getInstance().getScore()));
    }
    private String captureSystemOut() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        decorator.decorate();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }
}