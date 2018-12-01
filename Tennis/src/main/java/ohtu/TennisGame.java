package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    private String equalScore(int score) {
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";

        }
    }

    private String scoreToText(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return "";
    }

    private String advantageOrWin(int player1, int player2) {
        if (player1 - player2 == 1) {
            return "Advantage player1";
        } else if (player2 - player1 == 1) {
            return "Advantage player2";
        } else if (player1 - player2 > 1) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    public String getScore() {
        String scoreText = "";
        if (m_score1 == m_score2) {
            scoreText = equalScore(m_score1);
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            scoreText = advantageOrWin(m_score1, m_score2);
        } else {
            scoreText = scoreToText(m_score1)
                    + "-"
                    + scoreToText(m_score2);
        }
        return scoreText;
    }
}
