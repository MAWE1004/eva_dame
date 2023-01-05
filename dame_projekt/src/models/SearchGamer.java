package models;

public class SearchGamer {
    String multiAdr;
    String game;
    int time;
    String gamerWhite;
    String gamerBlack;

    @Override
    public String toString() {
        return "SearchGamer{" +
                "multiAdr='" + multiAdr + '\'' +
                ", game='" + game + '\'' +
                ", time=" + time +
                ", gamerWhite='" + gamerWhite + '\'' +
                ", gamerBlack='" + gamerBlack + '\'' +
                '}';
    }


    public SearchGamer(){

    }
    public SearchGamer(String game, int time, String gamerWhite, String gamerBlack) {
        this.game = game;
        this.time = time;
        this.gamerWhite = gamerWhite;
        this.gamerBlack = gamerBlack;
    }

    public String getMultiAdr() {
        return multiAdr;
    }

    public void setMultiAdr(String multiAdr) {
        this.multiAdr = multiAdr;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getGamerWhite() {
        return gamerWhite;
    }

    public void setGamerWhite(String gamerWhite) {
        this.gamerWhite = gamerWhite;
    }

    public String getGamerBlack() {
        return gamerBlack;
    }

    public void setGamerBlack(String gamerBlack) {
        this.gamerBlack = gamerBlack;
    }
}
