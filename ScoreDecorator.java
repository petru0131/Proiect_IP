interface CandyDecorator {
    void decorate();
}

class ScoreDecorator implements CandyDecorator {
    private GameManager gameManager;

    public ScoreDecorator() {

    }

    public void setScore(GameManager gm) {
        gameManager = gm;
    }

    @Override
    public void decorate() {
        System.out.println("Scorul prezent este: " + gameManager.getScore());
    }
}