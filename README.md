# Lunareth

Lunareth é um jogo 2D experimental desenvolvido em Java com
[libGDX](https://libgdx.com/). O projeto está no início e evolui de forma
incremental, priorizando uma base simples, jogável e fácil de expandir.

O jogo tem como inspirações gerais a exploração, a coleta de recursos, a
progressão e a vida em um mundo aberto. Essas referências orientam apenas o
design: o código, os personagens, os mapas e os demais conteúdos de Lunareth
devem ser originais ou possuir licença compatível.

## Estado atual

Neste momento, o protótipo possui:

- janela desktop de 1080 x 720;
- mapa 2D formado por tiles de grama e pedra;
- personagem controlado pelo teclado;
- movimento independente da taxa de quadros;
- câmera acompanhando o personagem;
- colisão básica com pedras e limites do mapa.

O personagem ainda é representado no jogo por um quadrado provisório. Existem
sprites em desenvolvimento dentro de `assets/player`, mas eles ainda não foram
integrados à renderização e à animação.

## Controles

| Tecla | Ação |
| --- | --- |
| `W` | Mover para cima |
| `A` | Mover para a esquerda |
| `S` | Mover para baixo |
| `D` | Mover para a direita |

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
│       └── player/Player.java
├── lwjgl3/       # Inicialização e configuração da versão desktop
├── gradle/       # Arquivos do Gradle Wrapper
└── build.gradle  # Configuração geral do projeto
```

Responsabilidades atuais das classes principais:

- `Lunareth`: inicializa os recursos, coordena atualização e renderização e
  controla a câmera;
- `Mapa`: armazena a grade de tiles, desenha o cenário e informa quais posições
  são sólidas;
- `Player`: processa os controles, movimenta o personagem e resolve colisões.

## Próximos passos

O desenvolvimento planejado para a versão inicial inclui, de forma gradual:

1. integrar o sprite e as animações do personagem;
2. adicionar objetos e pelo menos um recurso coletável;
3. criar um inventário básico;
4. implementar save e load básicos.

Sistemas maiores, como combate, NPCs, agricultura, crafting e dungeons, ficam
para etapas posteriores.

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
