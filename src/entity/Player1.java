package entity;

import main.GamePanel;
import main.KeyHandlerP1;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player1 extends Entity{
    GamePanel gp;
    KeyHandlerP1 keyP1;

    public Player1(GamePanel gp, KeyHandlerP1 keyP1){
        this.gp = gp;
        this.keyP1 = keyP1;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){//set p1 default position
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        //
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack4.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack5.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack6.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack7.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroBack8.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront3.png"));
            down4= ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront4.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront5.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront6.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront7.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroFront8.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroRight3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroRight4.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroLeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroLeft3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/guerreiro/GuerreiroLeft4.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        //PLAYER 1 MOVIMENTACAO:
        if(keyP1.upPressed == true || keyP1.downPressed == true ||
                keyP1.leftPressed == true|| keyP1.rightPressed == true){
            if(keyP1.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if(keyP1.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyP1.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyP1.rightPressed == true){
                direction = "right";
                x += speed;
            }

            //sprites left e right
            spriteCounterLR++;
            if(spriteCounterLR > 12){
                if(spriteNumLR == 1){
                    spriteNumLR = 2;
                }
                else if(spriteNumLR == 2){
                    spriteNumLR = 3;
                }
                else if(spriteNumLR == 3){
                    spriteNumLR = 4;
                }
                else if(spriteNumLR == 4){
                    spriteNumLR = 1;
                }
                spriteCounterLR = 0;
            }
            //sprites up e down
            spriteCounterUPDOWN++;
            if(spriteCounterUPDOWN > 9){
                if(spriteNumUPDOWN == 1){
                    spriteNumUPDOWN = 2;
                }
                else if(spriteNumUPDOWN == 2){
                    spriteNumUPDOWN = 3;
                }
                else if(spriteNumUPDOWN == 3){
                    spriteNumUPDOWN = 4;
                }
                else if(spriteNumUPDOWN == 4){
                    spriteNumUPDOWN = 5;
                }
                else if(spriteNumUPDOWN == 5){
                    spriteNumUPDOWN = 6;
                }
                else if(spriteNumUPDOWN == 6){
                    spriteNumUPDOWN = 7;
                }
                else if(spriteNumUPDOWN == 7){
                    spriteNumUPDOWN = 8;
                }
                else if(spriteNumUPDOWN == 8){
                    spriteNumUPDOWN = 1;
                }
                spriteCounterUPDOWN = 0;
            }
        }


    }
    public void draw(Graphics2D g2){
//        //PLAYER 1(P1)
//        g2.setColor(Color.green);//define a cor que será usada para desenhar objetos
//        g2.fillRect(x, y, gp.charSize, gp.charSize);//desenha um retangulo e preenche ele com a cor definida(x,y,largura,altura)

        BufferedImage image = null;

        //BASEADO NA DIREÇÃO, AS IMAGENS SÃO ESCOLHIDAS
        switch(direction){
            case "up":
                if(spriteNumUPDOWN == 1){
                    image = up1;
                }
                if(spriteNumUPDOWN == 2){
                    image = up2;
                }
                if(spriteNumUPDOWN == 3){
                    image = up3;
                }
                if(spriteNumUPDOWN == 4){
                    image = up4;
                }
                if(spriteNumUPDOWN == 5){
                    image = up5;
                }
                if(spriteNumUPDOWN == 6){
                    image = up6;
                }
                if(spriteNumUPDOWN == 7){
                    image = up7;
                }
                if(spriteNumUPDOWN == 8){
                    image = up8;
                }
                break;
            case "down":
                if(spriteNumUPDOWN == 1){
                    image = down1;
                }
                if(spriteNumUPDOWN == 2){
                    image = down2;
                }
                if(spriteNumUPDOWN == 3){
                    image = down3;
                }
                if(spriteNumUPDOWN == 4){
                    image = down4;
                }
                if(spriteNumUPDOWN == 5){
                    image = down5;
                }
                if(spriteNumUPDOWN == 6){
                    image = down6;
                }
                if(spriteNumUPDOWN == 7){
                    image = down7;
                }
                if(spriteNumUPDOWN == 8){
                    image = down8;
                }
                break;
            case "left":
                if(spriteNumLR == 1){
                    image = left1;
                }
                if(spriteNumLR == 2){
                    image = left2;
                }
                if(spriteNumLR == 3){
                    image = left3;
                }
                if(spriteNumLR == 4){
                    image = left4;
                }
                break;
            case "right":
                if(spriteNumLR == 1){
                    image = right1;
                }
                if(spriteNumLR == 2){
                    image = right2;
                }
                if(spriteNumLR == 3){
                    image = right3;
                }
                if(spriteNumLR == 4){
                    image = right4;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.charSize, gp.charSize, null);
    }
}
