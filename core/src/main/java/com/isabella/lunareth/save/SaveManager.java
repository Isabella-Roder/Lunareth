package com.isabella.lunareth.save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class SaveManager {
    
    private static final String ARQUIVO = "save.json";

    public static void salvar(SaveData dados) {
        Json json = new Json();
        FileHandle arquivo = Gdx.files.local(ARQUIVO);
        arquivo.writeString(json.toJson(dados), false);
    }

    public static SaveData carregar() {
        FileHandle arquivo = Gdx.files.local(ARQUIVO);

        if (!arquivo.exists()) {
            return null;
        }
        Json json = new Json();
        return json.fromJson(SaveData.class, arquivo.readString());
    }
}
