import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameManagerTest {

    @Test
    public void getInstance() {
        GameManager instance = new GameManager().getInstance();
        assertEquals(instance,instance.getInstance());
    }
    @Test
    void getScore() {
        GameManager g = new GameManager(20,10);
        assertEquals(20,g.getScore());
    }
    @Test
    void getMoves() {
        GameManager g = new GameManager(20,10);
        assertEquals(10,g.getMoves());
    }

    @Test
    void setScore() {
        GameManager g = new GameManager(20,10);
        g.setScore(30);
        assertEquals(30,g.getScore());
    }

    @Test
    void setMoves() {
        GameManager g = new GameManager(20,10);
        g.setMoves(15);
        assertEquals(15,g.getMoves());
    }

    @Test
    void incrementScore() {
        GameManager g = new GameManager(20,10);
        g.incrementScore();
        assertEquals(35,g.getScore());
    }

    @Test
    void decrementMoves() {
        GameManager g = new GameManager(20,10);
        g.decrementMoves();
        assertEquals(9,g.getMoves());
    }
}