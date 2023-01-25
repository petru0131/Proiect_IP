
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class CommandManger {
    GameManager g=new GameManager(0,20);
    private Command replayCommand;
    private Command quitCommand;

    public CommandManger() {
        //...
        replayCommand = new ReplayCommand(this);
        quitCommand = new QuitCommand(this);
        //...
    }
    //...
    public void replay() {
        g.getScore();
        g.getMoves();
        new Candy();
    }
    public void quit() {

        System.exit(0);
    }
    public void setReplayButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                replayCommand.execute();
            }
        });
    }
    public void setQuitButton(JButton button) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quitCommand.execute();
            }
        });
    }


}