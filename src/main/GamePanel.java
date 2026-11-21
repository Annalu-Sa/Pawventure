package main;

import entity.Player1;
import entity.Player2;
import tile.TileManager;
import object.SuperObject;

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

    public final int maxScreenCol = 16;// 28 colunas * 48 pixels  por tile   = 1344  ***alteração Largura: 16 × 48 = 768 pixels.
    public final int maxScreenRow = 12;//16 fileiras * 48 pixels = 768               ***alteraçãoAltura: 12 × 48 = 576 pixels.

    //TAMANHO DO PERSONAGEM (32x32 pixels)
    public final int originalCharSize = 32;//precisa ser public pra acessar de outro package
    public final int charSize = originalCharSize * scale;//32*3 = 96 pixels na tela pro charracter

    //TAMANHO DA TELA DO JOGO:
    // public final int screenWidth = 85 * maxScreenCol;//85x28 = 2380 pixels = comp de um notebook médio
    // public final int screenHeight = 64 * maxScreenRow;//64x16 = 1024 pixels = alt de um notebook médio
    // TAMANHO DA TELA DO JOGO:
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;


    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public int cameraX;
    public int cameraY;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandlerP1 keyP1 = new KeyHandlerP1();
    KeyHandlerP2 keyP2 = new KeyHandlerP2();
    //VAMOS CRIAR O TEMPO DO JOGO(GAME CLOCK): tempo precisa rodar no jogo como na vida real
    Thread gameThread;
    public CollisionChecker collisionCheck = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player1 player1 = new Player1(this, keyP1);
    public Player2 player2 = new Player2(this, keyP2);
    public SuperObject obj[] = new SuperObject[10];



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
    
    public void setupGame (){ 

        aSetter.setObject();
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

        // câmera centrada no centro do sprite do P1
        cameraX = player1.worldX + charSize / 2; //Somando charSize / 2 à câmera, o centro do sprite do P1 é que fica no centro
        cameraY = player1.worldY  + charSize / 2;
        //corrige a camera indo pra fora da tela; impõe um teto(Math.min) e um piso(Math.max)
        cameraX = Math.max(screenWidth / 2,  Math.min(cameraX, worldWidth  - screenWidth / 2));
        cameraY = Math.max(screenHeight / 2, Math.min(cameraY, worldHeight - screenHeight / 2));
        limitPlayer1ToScreen();

        player2.update();
        if (keyP2.teleportPressed) {
            player2.worldX = player1.worldX + charSize/6;   // ao lado do P1, não em cima
            player2.worldY = player1.worldY;
            keyP2.teleportPressed = false;                // consome o aperto: 1 tecla = 1 teleporte
        }
        limitPlayer2ToScreen();   // depois de mover o P2 e de atualizar a câmera
    }

    private void limitPlayer2ToScreen(){
        int minX = cameraX - screenWidth / 2;
        int maxX = cameraX + screenWidth / 2 - charSize;
        int minY = cameraY - screenHeight / 2;
        int maxY = cameraY + screenHeight / 2 - charSize;

        player2.worldX = Math.max(minX, Math.min(player2.worldX, maxX));
        player2.worldY = Math.max(minY, Math.min(player2.worldY, maxY));
    }

    private void limitPlayer1ToScreen(){
        int minX = cameraX - screenWidth / 2;
        int maxX = cameraX + screenWidth / 2 - charSize;
        int minY = cameraY - screenHeight / 2;
        int maxY = cameraY + screenHeight / 2 - charSize;

        player1.worldX = Math.max(minX, Math.min(player1.worldX, maxX));
        player1.worldY = Math.max(minY, Math.min(player1.worldY, maxY));
    }

    //2. metodo DRAW THE SCREEN WITH THE UPDATED INFORMATION
    public void paintComponent(Graphics g){//esse metodo "paintComponent" é a forma default de desenhar no JPanel
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;//tem mais funcoes que Graphics

        

        //É necessário desenhar o "chão"(tile) antes do player = é como se fosse um layer
        //Se for o contrário o tiles vão cobrir o player character
        tileM.draw(g2);

        //OBJECT
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        player1.draw(g2);
        player2.draw(g2);

        g2.dispose();
    }
}
