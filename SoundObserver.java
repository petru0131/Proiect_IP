import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Random;

interface Observer {
    void update();
}

class SoundObserver implements Observer {
    private static final String[] soundFiles = {
            "Images/wee.wav",
            "Images/wee2.wav",
            "Images/wee3.wav"
    };
    private boolean updated = false;

    private Clip clip;
    private Random random;
    public SoundObserver() {
        random = new Random();
        try {
            // load the sound file
            int index = random.nextInt(soundFiles.length);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(getClass().getResource(soundFiles[index]));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }  catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void update() {

        clip.start();
    }
    public boolean isUpdated() {
        return true;
    }
}