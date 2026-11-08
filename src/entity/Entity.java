package entity;

import java.awt.image.BufferedImage;

//CLASSE MÃE DE PLAYER, MONSTRO E NPC CLASS
public class Entity {

    public int x, y;
    public int speed;

    public int spriteCounterUPDOWN = 0;
    public int spriteNumUPDOWN = 1;

    public int spriteCounterLR = 0;
    public int spriteNumLR = 1;

    //BufferedImage usamos pra guardar e usar nossas imgs
    public BufferedImage right1, right2, right3, right4,
                         left1, left2, left3, left4,
                         down1, down2, down3, down4, down5, down6, down7, down8,/*down = front*/
                         up1, up2, up3, up4, up5, up6, up7, up8; //back
    public String direction;
}
