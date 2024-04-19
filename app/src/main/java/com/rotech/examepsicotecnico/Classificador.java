package com.rotech.examepsicotecnico;

public class Classificador {
    int pontuacao = 0;

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void incrementar(){
        pontuacao++;
    }

    public int getPontuacao() {
        return pontuacao;
    }
}
