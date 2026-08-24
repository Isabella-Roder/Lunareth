package com.isabella.lunareth.npc;

public class Falas {
    
    private String texto;
    private Falas proximaSeSim = null;
    private Falas proximaSeNao = null;

    public Falas(String texto) {
        this.texto = texto;
    }

    public Falas(String texto, Falas proximaSeSim, Falas proximaSeNao) {
        this.texto = texto;
        this.proximaSeSim = proximaSeSim;
        this.proximaSeNao = proximaSeNao;
    }

    public boolean temEscolha() {
        if (proximaSeSim != null || proximaSeNao != null) {
            return true;
        }

        return false;
    }

    public String getTexto() {
        return texto;
    }

    public Falas getProximaSeSim() {
        return proximaSeSim;
    }

    public Falas getProximaSeNao() {
        return proximaSeNao;
    }

}
