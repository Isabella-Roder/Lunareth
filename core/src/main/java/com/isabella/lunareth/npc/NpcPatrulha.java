package com.isabella.lunareth.npc;

public class NpcPatrulha extends Npc {
    
    private final float xInicial;
    private final float yInicial;
    private final float xFinal;
    private final float yFinal;
    private final float velocidade;

    private boolean indoParaFinal = true;

    public NpcPatrulha(
        float xInicial,
        float yInicial,
        float xFinal,
        float yFinal,
        float velocidade,
        String caminhoTextura,
        String dialogo
    ) {
        super(xInicial, yInicial, caminhoTextura, dialogo);

        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.velocidade = velocidade;
    }

    @Override
    public void atualizar(float delta) {
        float destinoX = indoParaFinal ? xFinal : xInicial;

        if (x < destinoX) {
            x += velocidade * delta;
        } else {
            x -= velocidade * delta;
        }

        if (Math.abs(x - destinoX) < 2f) {
            indoParaFinal = !indoParaFinal;
        }
    }
}
