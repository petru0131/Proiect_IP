import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {
    MusicPlayer player;
    String musicPath = "Images/music.wav";
    @Test
    void play() {
        player = new MusicPlayer(musicPath);
        player.play();
        assertTrue(player.clip.isOpen());
    }

    @Test
    void stop() {
        player = new MusicPlayer(musicPath);
        player.play();
        player.stop();
        assertFalse(player.clip.isActive());
    }

    @Test
    void repeat() {
        player = new MusicPlayer(musicPath);
        player.play();
        player.repeat();
        assertTrue(player.clip.isOpen());
    }
}