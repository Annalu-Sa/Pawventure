package main;

import entity.Player1;
import entity.Player2;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS(16x16 pixels):
    final int originalTitleSize = 16;//16x16 title: tamanho default
    final int scale = 3;//reajustar a escala do pixel pra nao ficar minusculo na tela
    public final int tileSize = originalTitleSize * scale;//48x48 pixels: 3*16

    // Assim a janela tem 2380 × 1024, bem perto de um notebook e a grade preenche a tela inteira.
    final int maxScreenCol = 28;// 28 colunas * 48 pixels  por tile   = 1344
    final int maxScreenRow = 16;//16 fileiras * 48 pixels = 768

    //TAMANHO DO PERSONAGEM (32x32 pixels):
    public final int originalCharSize = 32;//precisa ser public pra acessar de outro package
    public final int charSize = originalCharSize * scale;//32*3 = 96 pixels na tela pro charracter

    //TAMANHO DA TELA DO JOGO:
    final int screenWidth = 85 * maxScreenCol;//85x28 = 2380 pixels = comp de um notebook médio
    final int screenHeight = 64 * maxScreenRow;//64x16 = 1024 pixels = alt de um notebook médio

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandlerP1 keyP1 = new KeyHandlerP1();
    KeyHandlerP2 keyP2 = new KeyHandlerP2();
    //VAMOS CRIAR O TEMPO DO JOGO(GAME CLOCK): tempo precisa rodar no jogo como na vida real
    Thread gameThread;
    Player1 player1 = new Player1(this, keyP1);
    Player2 player2 = new Player2(this, keyP2);


    //CONSTRUTOR DO GamePanel
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));//define o tamanho dessa classe JPanel
        this.setBackground(Color.black);//cor de fundo da tela default
        this.setDoubleBuffered(true);//se true, todo o desenho deste componente vai ficar offscreen painting buffer meaning
        //offscreen painting buffer meaning: onde uma imagem é desenhada nos bastidores antes de ser exibida na tela principal
        this.addKeyListener(keyP1);
        this.addKeyListener(keyP2);
        this.setFocusable(true);//com isso, GamePanel "foca" em receber tecla (key input)
    }
    //INICIALIZANDO A THREAD
    public void startGameThread() {
        //passando a classe GamePanel para esse(this) construtor
        gameThread = new Thread(this);//"this" é o Runnable
        gameThread.start();//cria a thread e chama run()
    }
    @Override
    public void run(){ //esse run() roda na thread criada("gameThread")
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    //1. metodo UPDATE INFORMATION, como posicao de personagem
    public void update(){
        player1.update();
        player2.update();
    }
    //2. metodo DRAW THE SCREEN WITH THE UPDATED INFORMATION
    public void paintComponent(Graphics g){//esse metodo "paintComponent" é a forma default de desenhar no JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;//tem mais funcoes que Graphics

        //É necessário desenhar o "chão"(tile) antes do player = é como se fosse um layer
        //Se for o contrário o tiles vão cobrir o player character
        tileM.draw(g2);
        player1.draw(g2);
        player2.draw(g2);

        g2.dispose();
    }
}
