package com.isabella.lunareth.mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.isabella.lunareth.coletaveis.catalogo.Item;
import com.isabella.lunareth.coletaveis.catalogo.Itens;
import com.isabella.lunareth.criaturas.Criatura;
import com.isabella.lunareth.mundo.arvores.Arvore;
import com.isabella.lunareth.npc.Npc;
import com.isabella.lunareth.npc.Npcs;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.Player;
import com.isabella.lunareth.tempo.Relogio;

public class Mundo {

    private final Map<Bioma, Mapa> mapas;
    private final List<Item> itensNoMundo;
    private final List<Npc> npcs;
    private final List<Criatura> criaturas;
    private final List<Arvore> arvores;
    private final Relogio relogio;

    private Bioma biomaAtual;

    public Mundo() {
        mapas = new HashMap<>();
        mapas.put(Bioma.BOSQUE, new Mapa(Bioma.BOSQUE));
        mapas.put(Bioma.PRAIA, new Mapa(Bioma.PRAIA));
        biomaAtual = Bioma.BOSQUE;

        relogio = new Relogio();

        criaturas = new ArrayList<>();
        criaturas.add(criarCriaturaNoBioma(Bioma.BOSQUE, 50));

        arvores = new ArrayList<>();
        criarArvoresNoBosque(15);

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

        verificarTransicaoDeBioma(player);

        criaturas.removeIf(Criatura::estaMorta);
    }

    private void criarArvoresNoBosque(int quantidade) {
        Mapa mapaBosque = mapas.get(Bioma.BOSQUE);
        String[] texturas = {
            "mundo/arvores/arvoreBosque.png",
            "mundo/arvores/arvoreBosque2.png", 
            "mundo/arvores/arvoreBosque3.png"
        };

        for (int i = 0; i < quantidade; i++) {
            float x, y;
            do {
                x = (float) (Math.random() * mapaBosque.getAlturaEmPixels());
                y = (float) (Math.random() * mapaBosque.getAlturaEmPixels());
            } while (mapaBosque.solido(x, y) || mapaBosque.biomaEm(x, y) != Bioma.BOSQUE);

            String textura = texturas[(int) (Math.random() * texturas.length)];
            arvores.add(new Arvore(x, y, textura, 40));
        }
    }

    private void verificarTransicaoDeBioma(Player player) {
        Mapa mapaDoAtual = getMapaAtual();
        float margem = mapaDoAtual.getTamanhoTile() * 3;

        if (biomaAtual == Bioma.BOSQUE && player.getX() >= mapaDoAtual.getLarguraEmPixels() - margem) {
            biomaAtual = Bioma.PRAIA;
            player.setPosicao(margem, player.getY());
        } else if (biomaAtual == Bioma.PRAIA && player.getX() <= margem) {
            biomaAtual = Bioma.BOSQUE;
            player.setPosicao(mapas.get(Bioma.BOSQUE).getLarguraEmPixels() - margem, player.getY());
        }
    }

    public void render(SpriteBatch batch) {
        getMapaAtual().render(batch);

        for (Npc npc : npcs) {
            npc.render(batch);
        }

        for (Item itemMundo : itensNoMundo) {
            itemMundo.render(batch);
        }

        for (Arvore arvore : arvores) {
            arvore.render(batch);
        }
    }

    private Criatura criarCriaturaNoBioma(Bioma bioma, float vida) {
        Mapa mapaDoBioma = mapas.get(bioma);
        float x, y;

        do {
            x = (float) (Math.random() * mapaDoBioma.getLarguraEmPixels());
            y = (float) (Math.random() * mapaDoBioma.getAlturaEmPixels());
        } while (mapaDoBioma.biomaEm(x, y) != bioma);

        return new Criatura(x, y, vida, bioma);
    }

    public void renderCriaturas(ShapeRenderer renderer) {
        for (Criatura criatura : criaturas) {
            criatura.render(renderer);
        }
    }

    public void dispose() {
        for (Mapa mapaDoBioma : mapas.values()) {
            mapaDoBioma.dispose();
        }

        for (Item itemMundo : itensNoMundo) {
            itemMundo.dispose();
        }

        for (Npc npc : npcs) {
            npc.dispose();
        }

        for (Arvore arvore : arvores) {
            arvore.dispose();
        }
    }

    public Mapa getMapaAtual() {
        return mapas.get(biomaAtual);
    }

    public Mapa getMapa() {
        return getMapaAtual();
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

    public List<Arvore> getArvores() {
        return arvores;
    }
}
