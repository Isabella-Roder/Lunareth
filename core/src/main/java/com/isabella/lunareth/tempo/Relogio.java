package com.isabella.lunareth.tempo;

public class Relogio {
    
    private float horaAtual = 6;
    private int diaAtual = 1;
    private float duaracaoHoraEmSegundo = 60f;

    public void atualizar(float delta) {
        horaAtual += delta / duaracaoHoraEmSegundo;

        if (horaAtual >= 24) {
            horaAtual -= 24;
            diaAtual += 1;
        }
    }

    public boolean isNoite() {
        if (horaAtual < 6 || horaAtual > 18) {
            return true;
        }
        return false;
    }

    public float getOpacidadeNoite() {
        float distanciaDoMeioDia = Math.abs(horaAtual - 12);
        return (distanciaDoMeioDia / 12f) * 0.7f;
    }

    public float getHoraAtual() {
        return horaAtual;
    }

    public int getDiaAtual() {
        return diaAtual;
    }
}
