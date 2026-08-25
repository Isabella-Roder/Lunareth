package com.isabella.lunareth.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.isabella.lunareth.coletaveis.armas.Armas;
import com.isabella.lunareth.coletaveis.catalogo.Item;
import com.isabella.lunareth.coletaveis.comida.Comida;
import com.isabella.lunareth.criaturas.Criatura;
import com.isabella.lunareth.mundo.Mundo;
import com.isabella.lunareth.npc.Falas;
import com.isabella.lunareth.npc.Npc;
import com.isabella.lunareth.player.Inventario;
import com.isabella.lunareth.player.ItemInventario;
import com.isabella.lunareth.player.Player;
import com.isabella.lunareth.save.SaveData;
import com.isabella.lunareth.save.SaveManager;

public class ControlesInput {

    private Falas falaAtual = null;
    private boolean inventarioAberto = false;

    public void processar(Player player, Inventario inventario, Mundo mundo) {
        if (Gdx.input.isKeyJustPressed(Keys.F5)) {
            salvar(player, inventario);
        }

        if (Gdx.input.isKeyJustPressed(Keys.F9)) {
            carregar(player, inventario, mundo);
        }

        if (Gdx.input.isKeyJustPressed(Keys.E)) {
            comer(player, inventario);
        }

        if (Gdx.input.isKeyJustPressed(Keys.F)) {
            falarComNpc(player, mundo);
        }

        if (falaAtual != null && falaAtual.temEscolha()) {
            if (Gdx.input.isKeyJustPressed(Keys.NUM_1)) {
                falaAtual = falaAtual.getProximaSeSim();
            } else if (Gdx.input.isKeyJustPressed(Keys.NUM_2)) {
                falaAtual = falaAtual.getProximaSeNao();
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.I)) {
            inventarioAberto = !inventarioAberto;
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            atacar(player, inventario, mundo);
        }
    }

    private void salvar(Player player, Inventario inventario) {
        SaveData dados = new SaveData();

        dados.playerX = player.getX();
        dados.playerY = player.getY();

        for (ItemInventario itemInv : inventario.getItens()) {
            dados.itensColetados.add(itemInv.getTipo().getNome());
        }

        SaveManager.salvar(dados);
    }

    private void carregar(Player player, Inventario inventario, Mundo mundo) {
        SaveData dados = SaveManager.carregar();

        if (dados == null) {
            return;
        }

        player.setPosicao(dados.playerX, dados.playerY);

        inventario.getItens().clear();

        for (Item itemMundo : mundo.getItensNoMundo()) {
            if (dados.itensColetados.contains(itemMundo.getTipo().getNome())) {
                itemMundo.coletar();
                inventario.adicionar(itemMundo.getTipo());
            }
        }
    }

    private void comer(Player player, Inventario inventario) {
        for (ItemInventario itemInv : inventario.getItens()) {
            if (itemInv.getTipo() instanceof Comida comida) {
                player.getAtributos().aplicarEfeito(comida.comer());
                itemInv.removerUnidade();
                break;
            }
        }
    }

    private void falarComNpc(Player player, Mundo mundo) {
        falaAtual = null;

        for (Npc npc : mundo.getNpcs()) {
            if (npc.pertoDoPlayer(player.getX(), player.getY(), 32f)) {
                falaAtual = npc.getFalaInicial();
                break;
            }
        }
    }

    private void atacar(Player player, Inventario inventario, Mundo mundo) {

        player.iniciarAtaque();

        for (Criatura criatura : mundo.getCriaturas()) {

            if (criatura.pertoDoPlayer(player.getX(), player.getY(), 32f)) {
                
                for (ItemInventario itemInv : inventario.getItens()) {

                    if (itemInv.getTipo() instanceof Armas arma) {
                        criatura.recebeDano(arma.atacar());
                        break;
                    }
                }

                break;
            }
        }
    }

    public Falas getFalaAtual() {
        return falaAtual;
    }

    public boolean isInventarioAberto() {
        return inventarioAberto;
    }
}
