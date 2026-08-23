# Lunareth

Lunareth é um jogo 2D experimental desenvolvido em Java com
[libGDX](https://libgdx.com/). O projeto está no início e evolui de forma
incremental, priorizando uma base simples, jogável e fácil de expandir.

O jogo tem como inspirações gerais a exploração, a coleta de recursos, a
progressão e a vida em um mundo aberto. Essas referências orientam apenas o
design: o código, os personagens, os mapas e os demais conteúdos de Lunareth
devem ser originais ou possuir licença compatível.

## Estado atual

A versão inicial do jogo está completa. O protótipo possui:

- janela desktop de 1080 x 720;
- mapa 2D de 40x30 tiles, com grama, pedra, areia e terra;
- personagem animado (parado e andando, virado pra esquerda/direita);
- movimento independente da taxa de quadros;
- câmera acompanhando o personagem;
- colisão com pedras e limites do mapa;
- item coletável no mundo (foice mística), com catálogo de tipos de item;
- inventário básico, exibido como texto na tela;
- save/load básico (posição do jogador e itens coletados).

## Controles

| Tecla | Ação |
| --- | --- |
| `W` | Mover para cima |
| `A` | Mover para a esquerda |
| `S` | Mover para baixo |
| `D` | Mover para a direita |
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
│       ├── mundo/Mapa.java
│       ├── player/Player.java
│       ├── player/Inventario.java
│       ├── coletaveis/Item.java
│       ├── coletaveis/TipoItem.java
│       ├── coletaveis/Itens.java
│       ├── coletaveis/RaridadeItem.java
│       ├── save/SaveData.java
│       └── save/SaveManager.java
├── lwjgl3/       # Inicialização e configuração da versão desktop
├── gradle/       # Arquivos do Gradle Wrapper
└── build.gradle  # Configuração geral do projeto
```

Responsabilidades atuais das classes principais:

- `Lunareth`: inicializa os recursos, coordena atualização e renderização,
  controla a câmera e dispara save/load;
- `Mapa`: armazena a grade de tiles, desenha o cenário e informa quais posições
  são sólidas;
- `Player`: processa os controles, anima, movimenta o personagem e resolve
  colisões;
- `Inventario`: guarda a lista de tipos de item coletados;
- `Item`/`TipoItem`/`Itens`: representam objetos coletáveis no mundo e o
  catálogo de tipos de item existentes no jogo;
- `SaveData`/`SaveManager`: estrutura e persistência dos dados salvos em
  `assets/save.json`.

## Próximos passos

O escopo da versão inicial (ver `AGENTS.md`) está concluído. Sistemas maiores,
como combate, agricultura, crafting e dungeons, ficam para etapas posteriores,
implementados um de cada vez.

Um NPC básico já existe (`npc/`: `Npc`, `NpcPatrulha`, catálogo `Npcs`), com
rotina de patrulha simples e uma fala fixa ao interagir (tecla F). O design
para evoluir esse sistema, ainda não implementado, é:

- **Múltiplas falas por NPC**, não só uma `String` fixa — possivelmente falas
  diferentes dependendo do contexto (primeira vez falando com ele, depois de
  uma missão, etc.);
- **Interação real**, não só mostrar texto — o jogador poder responder/
  escolher opções de diálogo;
- **Rotinas que mudam por dia** (ex.: NPC fica em casa domingo, na loja nos
  outros dias) — depende de existir um sistema de tempo/dia no jogo, que
  ainda não existe.

Isso é grande o suficiente pra ser pensado como uma sessão própria, com
arquitetura planejada antes de codar, em vez de crescer aos poucos em cima do
`NpcPatrulha` atual.

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
