# AGENTS.md — Elaris — Ecos do Vale

## 1. Sobre o projeto

Elaris — Ecos do Vale (nome interno do projeto/pacote: `lunareth`) é um jogo
2D desenvolvido em Java com libGDX.

O projeto é experimental e deve crescer de forma incremental. A prioridade atual NÃO é implementar rapidamente uma grande quantidade de funcionalidades, mas construir uma base limpa, compreensível e extensível.

Inspirações gerais:
- Stardew Valley: vida, agricultura, NPCs e progressão.
- Minecraft: exploração, coleta, crafting e construção.
- Zelda: exploração, combate, puzzles e dungeons.
- Tibia: progressão, equipamentos, criaturas e exploração de mundo.

Essas referências servem apenas como inspiração de design.
Não copie código, assets, personagens, mapas, textos ou conteúdo protegido desses jogos.

---

## 2. Stack

- Java
- libGDX
- Gradle
- LWJGL3 para Desktop
- Git

O projeto deve continuar executável através de:

./gradlew lwjgl3:run

Não altere versões de Java, Gradle ou libGDX sem necessidade explícita.

---

## 3. Princípio principal

DESENVOLVA O JOGO DE FORMA INCREMENTAL.

Nunca tente implementar vários sistemas grandes ao mesmo tempo.

Antes de escrever código:

1. Leia a estrutura atual do projeto.
2. Entenda o código existente.
3. Identifique exatamente quais arquivos precisam mudar.
4. Explique brevemente o plano.
5. Faça a menor alteração capaz de cumprir a tarefa.
6. Compile/teste depois da alteração.

Não reescreva sistemas funcionando sem necessidade.

---

## 4. Escopo atual

A versão inicial (janela, personagem, movimentação, mapa, câmera, colisões,
coletáveis, inventário, save/load) está concluída. Também já foram
implementados: combate básico, criaturas, NPC com diálogo em árvore
(falas com escolha sim/não) e ciclo de dia/noite.

Próxima fase — dar identidade de mundo ao jogo, inspirado em Stardew Valley
(vida, progressão) com um lado de aventura (exploração, combate):

- [ ] biomas: associar criaturas e itens específicos a cada região do mapa
  (praia, bosque, ruínas, formações de pedra), em vez de tudo espalhado sem
  relação com o terreno;
- [ ] rotinas de NPC por dia (depende do `Relogio`, que já existe);
- [ ] agricultura/crafting (ainda não iniciado).

Cada item dessa lista é uma etapa própria, implementada um de cada vez.

---

## 5. Arquitetura

Evite colocar toda a lógica dentro da classe principal.

Separe responsabilidades em pacotes apropriados, por exemplo:

com.isabella.lunareth
├── player
├── world
├── entities
├── items
├── inventory
├── combat
├── npc
├── farming
├── crafting
├── save
├── screens
└── utils

Essa estrutura pode evoluir conforme o projeto crescer.

Não crie abstrações ou padrões complexos apenas por antecipação.

---

## 6. Regras para alterações

Ao receber uma tarefa:

- altere somente o necessário;
- preserve funcionalidades existentes;
- evite dependências externas desnecessárias;
- não adicione bibliotecas sem explicar por quê;
- não faça mudanças massivas sem autorização;
- não apague código funcional apenas para reorganizá-lo;
- prefira código simples e legível;
- evite classes gigantes;
- evite números mágicos;
- use nomes claros em português no código (classes, métodos, variáveis, pacotes);
- mantenha consistência com o estilo existente.

Se encontrar um problema fora do escopo da tarefa, informe-o em vez de alterar silenciosamente.

---

## 7. Assets

Sprites, músicas, fontes, efeitos sonoros e outros assets devem:

- ser originais;
- possuir licença compatível com uso comercial;
- ou ser temporários claramente identificados como placeholders.

Nunca introduza assets de jogos comerciais.

O Lunareth poderá futuramente ser distribuído comercialmente.

---

## 8. Performance

Não faça micro-otimizações prematuras.

Entretanto:

- evite criar objetos desnecessariamente a cada frame;
- descarte recursos libGDX corretamente;
- use AssetManager quando o projeto exigir gerenciamento maior de assets;
- mantenha renderização e lógica de jogo organizadas;
- considere que o mundo poderá crescer no futuro.

Primeiro faça funcionar corretamente.
Depois meça.
Depois otimize.

---

## 9. Git

Antes de alterações grandes, verifique o estado do repositório.

Não execute automaticamente:

git push
git reset --hard
git rebase
git clean

Não sobrescreva trabalho existente.

Commits devem representar mudanças pequenas e compreensíveis.

---

## 10. Testes

Depois de alterações relevantes, execute quando possível:

./gradlew build

e/ou:

./gradlew lwjgl3:run

Não considere uma tarefa concluída se o projeto deixou de compilar.

---

## 11. Como responder ao desenvolvedor

Antes de uma implementação significativa, informe:

- o que será implementado;
- quais arquivos serão alterados;
- qualquer decisão arquitetural importante.

Depois da implementação, informe:

- o que foi alterado;
- arquivos principais envolvidos;
- como testar;
- limitações conhecidas;
- próximo passo recomendado.

Se houver mais de uma solução razoável, apresente as opções antes de escolher uma arquitetura difícil de desfazer.

---

## 12. Filosofia do Lunareth

O projeto deve continuar divertido de desenvolver.

Não transforme uma ideia pequena em semanas de infraestrutura.

Sempre procure o menor próximo passo que produza algo visível ou jogável.

Prioridade:

JOGÁVEL > PERFEITO
SIMPLES > DESNECESSARIAMENTE COMPLEXO
INCREMENTAL > GIGANTESCO
FUNCIONANDO > SUPERARQUITETADO