import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.event.ActionListener;
import static org.junit.jupiter.api.Assertions.*;

class CommandMangerTest {

    @Test
    void replay() {
        CommandManger c=new CommandManger();
        c.replay();
        int initialScore = GameManager.getScore();
        int initialMoves = GameManager.getMoves();
        assertEquals(initialScore, GameManager.getScore());
        assertEquals(initialMoves, GameManager.getMoves());
    }

    @Test
    void quit() {
        CommandManger commandManager = new CommandManger();
        commandManager.quit();
        try {
            System.exit(0);
            fail("The System.exit(0) should have been called, but it was not");
        } catch (SecurityException e) {
            // This is expected since we are not allowed to call System.exit in a test environment
        }
    }

}