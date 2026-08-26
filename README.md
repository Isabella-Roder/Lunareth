# Elaris — Ecos do Vale

Elaris — Ecos do Vale (nome interno do projeto/pacote: `lunareth`) é um jogo
2D experimental desenvolvido em Java com [libGDX](https://libgdx.com/). O
projeto está no início e evolui de forma incremental, priorizando uma base
simples, jogável e fácil de expandir.

O jogo mistura a vida e progressão de Stardew Valley com um lado de aventura
inspirado em Zelda e Tibia: exploração de diferentes biomas, cada um com suas
próprias criaturas e itens. Essas referências orientam apenas o design: o
código, os personagens, os mapas e os demais conteúdos do jogo devem ser
originais ou possuir licença compatível.

## Estado atual

O protótipo possui:

- janela desktop de 1080 x 720;
- mapa 2D de 64x48 tiles, com regiões nomeadas (praia, bosque, ruínas,
  formações de pedra) e diferentes texturas de terreno;
- personagem animado (parado, andando, atacando e morrendo);
- movimento independente da taxa de quadros;
- câmera acompanhando o personagem;
- colisão com o cenário e limites do mapa;
- itens coletáveis no mundo (armas e comidas), com catálogo de tipos de item;
- inventário com ícones, que pode ser aberto/fechado;
- atributos do personagem (vida, fome, sede, energia);
- save/load básico (posição do jogador e itens coletados);
- ciclo de dia/noite, com escurecimento gradual da tela;
- NPC com rotina de patrulha e diálogo com falas encadeadas e escolha
  sim/não;
- combate: ataque do jogador (com animação) contra criaturas, que também
  causam dano de volta.

## Controles

| Tecla/Botão | Ação |
| --- | --- |
| `W` | Mover para cima |
| `A` | Mover para a esquerda |
| `S` | Mover para baixo |
| `D` | Mover para a direita |
| `F` | Falar com NPC próximo |
| `1` / `2` | Escolher "sim" / "não" em diálogos |
| `E` | Comer item |
| `I` | Abrir/fechar inventário |
| Clique esquerdo | Atacar criatura próxima |
| `F5` | Salvar jogo |
| `F9` | Carregar jogo |

## Tecnologias

- Java 21
- libGDX 1.14.2
- LWJGL3
- Gradle Wrapper
- Git

## Requisitos

- JDK 21 instalado e disponível no sistema;
- um ambiente gráfico compatível com aplicações LWJGL3.

Não é necessário instalar o Gradle separadamente, pois o projeto inclui o
Gradle Wrapper.

## Como executar

Na raiz do projeto, execute:

```bash
./gradlew lwjgl3:run
```

No Windows, use:

```bat
gradlew.bat lwjgl3:run
```

Para apenas compilar e verificar o projeto:

```bash
./gradlew build
```

## Estrutura do projeto

```text
Lunareth/
├── assets/       # Texturas e outros recursos do jogo
├── core/         # Regras, entidades e renderização compartilhadas
│   └── src/main/java/com/isabella/lunareth/
│       ├── Lunareth.java
│       ├── mundo/            # Mapa, mundo do jogo, relógio
│       ├── input/            # ControlesInput (teclado e mouse)
│       ├── ui/                # Hud
│       ├── player/           # Player, Atributos, Inventario
│       ├── coletaveis/       # Item, TipoItem, catálogo, armas, comida
│       ├── npc/               # Npc, NpcPatrulha, Falas, personagens
│       ├── criaturas/        # Criatura
│       ├── tempo/            # Relogio (dia/noite)
│       └── save/              # SaveData, SaveManager
├── lwjgl3/       # Inicialização e configuração da versão desktop
├── gradle/       # Arquivos do Gradle Wrapper
└── build.gradle  # Configuração geral do projeto
```

Responsabilidades atuais das classes principais:

- `Lunareth`: inicializa os recursos e coordena `Mundo`, `ControlesInput` e
  `Hud` a cada frame;
- `Mundo`: guarda o estado do mundo (mapa, itens, NPCs, criaturas, relógio) e
  atualiza/renderiza tudo isso;
- `Mapa`: armazena a grade de tiles, desenha o cenário e informa quais
  posições são sólidas;
- `ControlesInput`: processa teclado e mouse (movimento é tratado no
  `Player`; aqui ficam save/load, comer, falar com NPC, inventário e ataque);
- `Hud`: desenha a interface (overlay de noite, barras de atributos, lista de
  itens, diálogo);
- `Player`: anima, movimenta o personagem e resolve colisões;
- `Inventario`: guarda a lista de itens coletados;
- `Item`/`TipoItem`/`Itens`: representam objetos coletáveis no mundo e o
  catálogo de tipos de item existentes no jogo;
- `Npc`/`NpcPatrulha`/`Npcs`/`Falas`: NPCs com rotina de patrulha e diálogo
  em árvore (falas com escolha sim/não);
- `Criatura`: inimigos simples do mundo, com vida e dano de ataque;
- `SaveData`/`SaveManager`: estrutura e persistência dos dados salvos em
  `assets/save.json`.

## Próximos passos

O escopo da versão inicial (ver `AGENTS.md`) está concluído, assim como
combate básico, diálogo de NPC com escolhas e ciclo de dia/noite.

Os próximos passos giram em torno de dar mais identidade de mundo ao jogo,
inspirado em Stardew Valley (vida, progressão) com um lado de aventura
(exploração, combate):

- **Biomas**: hoje o `Mapa` já organiza o terreno em regiões nomeadas (praia,
  bosque, ruínas, formações de pedra). A ideia é associar criaturas e itens
  específicos a cada uma dessas regiões, em vez de tudo ficar espalhado sem
  relação com o terreno;
- **Rotinas de NPC por dia** (ex.: NPC fica em casa num dia, em outro lugar
  em outro dia) — depende do sistema de tempo/dia já existente (`Relogio`);
- **Agricultura/crafting**: ainda não iniciado, fica para uma etapa própria.

Cada um desses itens deve ser implementado aos poucos, um de cada vez, sem
tentar montar tudo de uma vez.

## Assets

Assets adicionados ao projeto devem ser originais, possuir licença compatível
com uso comercial ou estar claramente identificados como placeholders. Não
devem ser utilizados assets extraídos de jogos comerciais.

## Desenvolvimento

O projeto segue três princípios simples:

- implementar uma funcionalidade pequena por vez;
- preservar o que já funciona;
- compilar e testar depois de cada alteração relevante.

Antes de enviar uma mudança, execute pelo menos:

```bash
./gradlew build
```

## Licença

O projeto ainda não possui uma licença definida. Até que uma licença seja
adicionada, nenhum direito de uso, modificação ou distribuição é concedido
automaticamente.
