import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuitCommand implements Command,ActionListener {
    private CommandManger gameManager;
    public QuitCommand(){}
    public QuitCommand(CommandManger gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void execute() {
        gameManager.quit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
