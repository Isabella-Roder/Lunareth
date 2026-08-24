package com.isabella.lunareth.npc.personagens;

import com.isabella.lunareth.npc.Falas;
import com.isabella.lunareth.npc.NpcPatrulha;

public class Isabella extends NpcPatrulha {
    
    public Isabella() {
        super(400, 400, 500, 400, 50, "player/parado/parado.png", construirFalas());
    }

    public static Falas construirFalas() {
        Falas falaSim = new Falas("Esse mapa já foi mais vivo... hoje só restam ruinas e algumas criaturas. Tome cuidado.");
        Falas falaNao = new Falas("Tudo bem. Se mudar de ideia, estarei por aqui.");

        return new Falas("Você não é daqui, é? Quer saber sobre este lugar?", falaSim, falaNao);
    }
}
