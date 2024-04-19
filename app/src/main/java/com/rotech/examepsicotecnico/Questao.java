package com.rotech.examepsicotecnico;

public class Questao {
    String textoDaQuestao, textoAltA, textoAltB, textoAltC, textoAltD, textoAltE;
    int qCorreta, imagemPlacaId, nQuestao, grupoDaQuestao;

    public void setDados (int numeroDaQuestao,
                          int imagemDaQuestao,
                          String textoQuestao,
                          String questaoA,
                          String questaoB,
                          String questaoC,
                          String questaoD,
                          String questaoE,
                          int questaoCorreta,
                          int grpDaQuestao){
        nQuestao = numeroDaQuestao;
        imagemPlacaId = imagemDaQuestao;
        textoDaQuestao = textoQuestao;
        textoAltA = questaoA;
        textoAltB = questaoB;
        textoAltC = questaoC;
        textoAltD = questaoD;
        textoAltE = questaoE;
        qCorreta = questaoCorreta;
        grupoDaQuestao = grpDaQuestao;
    }

    public void setGrupoDaQuestao(int grupoDaQuestao) {
        this.grupoDaQuestao = grupoDaQuestao;
    }

    public String getTextoDaQuestao() {
        return textoDaQuestao;
    }

    public String getTextoAltA() {
        return textoAltA;
    }

    public String getTextoAltB() {
        return textoAltB;
    }

    public String getTextoAltC() {
        return textoAltC;
    }

    public String getTextoAltD() {
        return textoAltD;
    }

    public String getTextoAltE() {
        return textoAltE;
    }

    public int getqCorreta() {
        return qCorreta;
    }

    public int getImagemPlacaId(){
        return imagemPlacaId;
    }

    public int getnQuestao(){
        return nQuestao;
    }

    public int getGrupoDaQuestao(){
        return grupoDaQuestao;
    }
}