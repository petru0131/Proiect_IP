import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class Candy {

    //singletone
    GameManager gameManager = GameManager.getInstance();
    //pt decorator
    ScoreDecorator decorator = new ScoreDecorator();
    //observer
    private Subject subject;
    private SoundObserver soundObserver;
    //pentru teste in clasa de test SubjectTest
    List<Observer> observers = new ArrayList<>();

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void removeObserver(SoundObserver observer) {
        this.observers.remove(observer);
    }


    Color []cl =  new Color[6];
    ImageIcon []icon = new ImageIcon[6];
    URL []url = new URL[6];
    JLabel jl2=new JLabel();
    JLabel jl3=new JLabel();
    int x=-1,y=-1;
    JFrame jf = new JFrame();
    JPanel jp = new JPanel(new GridLayout(6,10));
    JPanel jp1 = new JPanel(new BorderLayout());
    JButton [][] bn = new JButton[6][10];
    JPanel jp2 = new JPanel();
    JLabel jb = new JLabel("   Score: "+String.valueOf(gameManager.getScore())+"                          Moves: "+String.valueOf(gameManager.getMoves()));
    MusicPlayerInterface musicPlayer = new MusicPlayerAdapter("Images/music.wav");
    private FlyweightCandyFactory factory = new FlyweightCandyFactory();
    public Candy()
    {


        musicPlayer.play();
        musicPlayer.repeat();

        Dimension d1 = new Dimension();
        d1.height = 80;
        jb.setPreferredSize(d1);
        jb.setFont(jb.getFont().deriveFont(30.0f));

        Container cn = jf.getContentPane();
        cn.setLayout(new BorderLayout());
        cn.add(jp1, BorderLayout.EAST);
        cn.add(jb,BorderLayout.NORTH);
        cn.add(jp);



        jf.setTitle("Candy Crush");
        jf.setSize(1000,700);
        jf.setLocation(200,0);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        jp2.setPreferredSize(new Dimension(200, 100));
        JButton replayButton = new JButton("Yes");
        CommandManger q=new CommandManger();
        q.setReplayButton(replayButton);


        JButton exitButton = new JButton("No");
        CommandManger n=new CommandManger();
        n.setQuitButton(exitButton);
        jp2.add(jl2);
        jp2.add(jl3);
        jp2.add(replayButton);
        jp2.add(exitButton);






        url[0] = getClass().getResource("/Images/candy70x70.png");
        url[1] = getClass().getResource("/Images/chocolate70x70.png");
        url[2] = getClass().getResource("/Images/Heart70x70.png");
        url[3] = getClass().getResource("/Images/we70x70.png");
        url[4] = getClass().getResource("/Images/bomb70x70.png");
        url[5] = getClass().getResource("/Images/apple_red.png");

        for(int i = 0 ; i < 6; i++)
            icon[i] = new ImageIcon(url[i]);

        cl[0] = Color.RED;
        cl[1] = Color.BLUE;
        cl[2] = Color.CYAN;
        cl[3] = Color.ORANGE;
        cl[4] = Color.WHITE;
        cl[5] = Color.PINK;


        for(int i=0; i < 6; i++)
            for(int j=0; j < 10; j++ )
            {
                int d = rand();
                //ImageIcon imageUrl=new ImageIcon(url[d]);
                bn[i][j] = new JButton();
                bn[i][j].setActionCommand(String.valueOf(d));
                bn[i][j].setBackground(cl[d]);
                bn[i][j].addActionListener(new Action());
                //FlyweightCandy candy = factory.getCandy(String.valueOf(imageUrl));
                bn[i][j].setIcon(new ImageIcon(url[d]));
                bn[i][j].setOpaque(false);
                bn[i][j].setBorderPainted(false);


                jp.add(bn[i][j]);
            }






        jf.setVisible(true);

        check();
    }

    class Action implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            for(int i=0; i < 6; i++)
                for(int j=0; j < 10; j++ )
                {
                    if(e.getSource() == bn[i][j])
                    {
                        if (x == -1 && y == -1) {

                            x = i;
                            y = j;
                            check();
                        } else {
                            Color tmp = bn[i][j].getBackground();
                            String tmp1 = bn[i][j].getActionCommand();
                            String tmp12 = bn[x][y].getActionCommand();
                            ImageIcon tmp2 = new ImageIcon(url[Integer.parseInt(tmp1)]);

                            if ((i == x + 1 && j == y) || (i == x - 1 && j == y) || (i == x  && j == y - 1) || (i == x && j == y + 1)) {
                                bn[i][j].setBackground(bn[x][y].getBackground());
                                bn[x][y].setBackground(tmp);

                                bn[i][j].setActionCommand(bn[x][y].getActionCommand());
                                bn[x][y].setActionCommand(tmp1);

                                bn[i][j].setIcon(new ImageIcon(url[Integer.parseInt(tmp12)]));
                                bn[x][y].setIcon(tmp2);

                                //x=i;y=j;
                                x=-1; y=-1;

                                check();
                                GameManager.decrementMoves();

                                if(GameManager.getMoves() == 0)
                                {
                                    //Adapter
                                    musicPlayer.stop();
                                    jl2.setText("    Amazing!!! Your score is "+gameManager.getScore()+"!");
                                    jl3.setText("     Do you want to play again???");
                                    jf.setSize(230,120);
                                    jf.setLocationRelativeTo(null);
                                    jf.setContentPane(jp2);
                                    jf.revalidate();
//                                    int check = JOptionPane.showConfirmDialog(null, "Your Score is:"+ score +  " \nDo you want to Play again?");
//
//
//                                    if(check == JOptionPane.YES_OPTION)
//                                    {
//                                        new Candy();
//                                        jf.dispose();
//                                    }
//                                    else if(check == JOptionPane.NO_OPTION)
//                                        System.exit(0);
                               }
                            }
                            else
                            {

                                x = i;
                                y = j;
                            }
                        }

                        jb.setText("   Score: "+String.valueOf(gameManager.getScore())+"                          Moves: "+String.valueOf(gameManager.getMoves()));
                        decorator.decorate();


                    }

                }

        }
    }


    public boolean check(){
        subject = new Subject();
        soundObserver = new SoundObserver();
        subject.registerObserver(soundObserver);
        for(int i = 0; i < 6; i++)
        {
            for(int j = 0;j< 8; j++)
            {
                if(bn[i][j].getActionCommand().matches(bn[i][j + 1].getActionCommand()) && bn[i][j].getActionCommand().matches(bn[i][j + 2].getActionCommand())){

                    if(j+ 3 < 10 && (bn[i][j].getActionCommand().matches(bn[i][j + 3].getActionCommand())))
                    {
                        int d;

                        if(j + 4 < 10 && (bn[i][j].getActionCommand().matches(bn[i][j + 4].getActionCommand())))
                        {
                            d = rand();
                            bn[i][j+4].setActionCommand(String.valueOf(d));
                            bn[i][j+4].setBackground(cl[d]);
                            bn[i][j+4].setIcon(icon[d]);

                            GameManager.incrementScore();
                        }

                            d = rand();
                            bn[i][j+3].setActionCommand(String.valueOf(d));
                            bn[i][j+3].setBackground(cl[d]);
                            bn[i][j+3].setIcon(icon[d]);

                            GameManager.incrementScore();

                    }

                    int d = rand();
                    bn[i][j].setActionCommand(String.valueOf(d));
                    bn[i][j].setBackground(cl[d]);
                    bn[i][j].setIcon(icon[d]);

                    d = rand();
                    bn[i][j+1].setActionCommand(String.valueOf(d));
                    bn[i][j+1].setBackground(cl[d]);
                    bn[i][j+1].setIcon(icon[d]);


                    d = rand();
                    bn[i][j+2].setActionCommand(String.valueOf(d));
                    bn[i][j+2].setBackground(cl[d]);
                    bn[i][j+2].setIcon(icon[d]);
                    subject.notifyObservers();
                    GameManager.incrementScore();

                    check();

                    jb.setText("   Score: "+String.valueOf(gameManager.getScore())+"                          Moves: "+String.valueOf(gameManager.getMoves()));

                }
            }
        }

        for(int j = 0;  j< 10; j++)
        {
            for(int i = 0;i< 4; i++)
            {
                if(bn[i][j].getActionCommand().matches(bn[i + 1][j].getActionCommand()) && bn[i][j].getActionCommand().matches(bn[i + 2][j].getActionCommand())){

                    int d;

                    if(i+ 3 < 6 && (bn[i][j].getActionCommand().matches(bn[i+3][j].getActionCommand())))
                    {

                        if(i+ 4 < 6 && (bn[i][j].getActionCommand().matches(bn[i+4][j].getActionCommand())))
                        {
                            d = rand();
                            bn[i+4][j].setActionCommand(String.valueOf(d));
                            bn[i+4][j].setBackground(cl[d]);
                            bn[i+4][j].setIcon(icon[d]);
                            GameManager.incrementScore();

                        }


                        d = rand();
                        bn[i+3][j].setActionCommand(String.valueOf(d));
                        bn[i+3][j].setBackground(cl[d]);
                        bn[i+3][j].setIcon(icon[d]);
                        GameManager.incrementScore();


                    }


                    d = rand();
                    bn[i][j].setActionCommand(String.valueOf(d));
                    bn[i][j].setBackground(cl[d]);
                    bn[i][j].setIcon(icon[d]);

                    d = rand();
                    bn[i+1][j].setActionCommand(String.valueOf(d));
                    bn[i+1][j].setBackground(cl[d]);
                    bn[i+1][j].setIcon(icon[d]);

                    d = rand();
                    bn[i+2][j].setActionCommand(String.valueOf(d));
                    bn[i+2][j].setBackground(cl[d]);
                    bn[i+2][j].setIcon(icon[d]);
                    //Observer
                    subject.notifyObservers();
                    GameManager.incrementScore();

                    check();

                    jb.setText("   Score: "+String.valueOf(gameManager.getScore())+"                          Moves: "+String.valueOf(gameManager.getMoves()));

                }
            }
        }



        return true;
    }



    public int rand()
    {
        Random r = new Random();
        return r.nextInt(6);

    }

    public static void main(String [] arg)
    {
        new Candy();

    }
}
