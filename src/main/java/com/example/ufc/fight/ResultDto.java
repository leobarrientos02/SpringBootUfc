package com.example.ufc.fight;

public class ResultDto {

    private String result;
    private String winner;

    public ResultDto(String result, String winner) {
        this.result = result;
        this.winner = winner;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
