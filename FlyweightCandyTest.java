import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class FlyweightCandyTest {

    @Test
    void getImage() {
        FlyweightCandy f=new FlyweightCandy(new ImageIcon( "Images/we70x70.png"));
        assertNotNull(f.getImage());
    }
}