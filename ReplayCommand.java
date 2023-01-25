import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReplayCommand implements Command, ActionListener {
    private CommandManger gameManager;
    public ReplayCommand(){}
    public ReplayCommand(CommandManger gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        gameManager.replay();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
