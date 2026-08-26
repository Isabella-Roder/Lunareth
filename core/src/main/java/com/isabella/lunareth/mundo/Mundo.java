package com.isabella.lunareth.mundo;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.isabella.lunareth.coletaveis.catalogo.Item;
import com.isabella.lunareth.coletaveis.catalogo.Itens;
import com.isabella.lunareth.criaturas.Criatura;
import com.isabella.lunareth.npc.Npc;
import com.isabella.lunareth.npc.Npcs;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.Player;
import com.isabella.lunareth.tempo.Relogio;

public class Mundo {

    private final Mapa mapa;
    private final List<Item> itensNoMundo;
    private final List<Npc> npcs;
    private final List<Criatura> criaturas;
    private final Relogio relogio;

    public Mundo() {
        mapa = new Mapa();
        relogio = new Relogio();

        criaturas = new ArrayList<>();
        criaturas.add(new Criatura(500, 300, 50));

        npcs = new ArrayList<>();
        npcs.add(Npcs.ISABELLA);

        itensNoMundo = new ArrayList<>();

        //armas
        itensNoMundo.add(new Item(150, 150, Itens.FOICE_MISTICA));

        //comidas
        itensNoMundo.add(new Item(250, 250, Itens.MACA));
        itensNoMundo.add(new Item(250, 150, Itens.MACA_VERDE));
        itensNoMundo.add(new Item(300, 250, Itens.MORANGO));
    }

    public void atualizar(float delta, Player player, Inventario inventario) {
        relogio.atualizar(delta);

        for (Npc npc : npcs) {
            npc.atualizar(delta);
        }

        for (Item itemMundo : itensNoMundo) {
            if (!itemMundo.isColetado() && itemMundo.colideCom(player.getX(), player.getY(), 32f)) {
                itemMundo.coletar();
                inventario.adicionar(itemMundo.getTipo());
            }
        }

        for (Criatura criatura : criaturas) {
            if (criatura.pertoDoPlayer(player.getX(), player.getY(), 32f)) {
                player.getAtributos().receberDano(criatura.atacar());
            }
        }

        criaturas.removeIf(Criatura::estaMorta);
    }

    public void render(SpriteBatch batch) {
        mapa.render(batch);

        for (Npc npc : npcs) {
            npc.render(batch);
        }

        for (Item itemMundo : itensNoMundo) {
            itemMundo.render(batch);
        }
    }

    public void renderCriaturas(ShapeRenderer renderer) {
        for (Criatura criatura : criaturas) {
            criatura.render(renderer);
        }
    }

    public void dispose() {
        mapa.dispose();

        for (Item itemMundo : itensNoMundo) {
            itemMundo.dispose();
        }

        for (Npc npc : npcs) {
            npc.dispose();
        }
    }

    public Mapa getMapa() {
        return mapa;
    }

    public List<Item> getItensNoMundo() {
        return itensNoMundo;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public Relogio getRelogio() {
        return relogio;
    }

    public List<Criatura> getCriaturas() {
        return criaturas;
    }
}
