import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SubjectTest {

    @Test
    void registerObserver() {
        Candy candy = new Candy();
        SoundObserver soundObserver = new SoundObserver();
        candy.registerObserver(soundObserver);
        assertTrue(candy.observers.contains(soundObserver));
    }

    @Test
    void removeObserver() {
        Candy candy = new Candy();
        SoundObserver soundObserver = new SoundObserver();
        candy.registerObserver(soundObserver);
        assertTrue(candy.observers.contains(soundObserver));
        candy.removeObserver(soundObserver);
        assertFalse(candy.observers.contains(soundObserver));
    }

    @Test
    void notifyObservers() {
        SoundObserver soundObserver = new SoundObserver();
        Candy candy = new Candy();
        candy.registerObserver(soundObserver);
        candy.check();
        candy.notifyObservers();
        assertTrue(soundObserver.isUpdated());
    }

}