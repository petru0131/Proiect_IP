public class GameManager {
    private static GameManager instance =null;
    static int score = 0;
    static int moves = 20;

    public GameManager(){}
    public GameManager(int score,int moves){
        this.score=score;
        this.moves=moves;
    }
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
    public static void incrementScore() {
        score = score + 15;
    }
    public static void decrementMoves() {
        moves--;
    }
}