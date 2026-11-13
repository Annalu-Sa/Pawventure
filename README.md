# Pawventure
## Sobre o jogo
- Action RPG com Co-op local, em tempo real e híbrido(aventura com batalhas de turnos);
- Multiplayer de 2 jogadores;
- Estilo The Legend of Zelda 1986 e Chrono Trigger 1995;
## História
Pawventure acompanha dois heróis (ou guerreiro, ou ladino, ou mago) em uma jornada épica para recuperar o Osso Sagrado e restaurar a harmonia do Reino do Bosque. 
Sauron, o lorde das sombras, roubou o Osso Sagrado com o objetivo de mergulhar o Reino do Bosque no medo e escuridão. Então, a Rainha Idril convoca 2 heróis (players 
1 e 2) para recuperar o Osso Sagrado e restaurar a paz.
## Personagens
### Guerreiro
- Nome: Théo
- Idade:
- Descrição: combate corpo a corpo, alta defesa (tank).
- Modifica qual regra: tem a passiva Escudo Leal que pode ser usada 1x por batalha; com ela o personagem assume o dano que seria recebido pelo aliano.
### Ladino
- Nome: Tobias
- Idade:
- Descrição: especialista em furtividade, agilidade e é sagaz.
- Modifica qual regra: passiva Patas Ligeiras -> sua fuga de batalha sempre funciona.
### Mago
- Nome: Tito
- Idade:
- Descrição: estudioso das artes arcanas, sabe magias de ataque e cura.
- Modifica qual regra: passiva Proteção Arcana -> a cada 2 turnos de batalha, cria automaticamente um escudo para o aliado de duração de 1 turno que bloqueia o dano 
recebido pelo aliado (caso ele receba dano enquanto estiver com o escudo).
## Batalhas
- Por turno;
- Fluxo geral:
  1. No mapa, o jogador entra no raio do monstro e a batalha começa;
  2. O mundo congela, o "modo batalha" assume;
  3. Ordem dos turnos: P1, P2, monstro = {n1, n2,...,nk}, repete;
     - Na vez do P1: só as teclas de P1 funcionam;
     - Na vez do P2: só as teclas de P2 funcionam;
     - A classe controle decidirá o input que será lido na batalha.
  4. A batalha termina em vitória, derrota ou fuga;
  5. 3 possibilidades de desfecho:
     - VITÓRIA: remove o monstro do mapa;
     - DERROTA: game-over se pelo menos 1 jogador tiver com hp = 0;
     - FUGA: se a fuga for um SUCESSO, o player que escolheu a fuga pula pra fora do raio do monstro junto com o outro player.
    
## Regras
## Bibliografia
- Tutorial RPG 2D em JAVA do RyiSnow;
- ...
