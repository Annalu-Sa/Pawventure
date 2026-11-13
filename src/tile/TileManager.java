package tile;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile; //criamos um array de blocos("tiles")

    //CONSTRUTOR:
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10]; //significa que terão 10 tipos diferentes de tiles
        getTileImage();
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        //0 = grass, 1 = wall, 2 = water,
        g2.drawImage(tile[1].image, 0, 0 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 48, 0 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 96, 0 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 144, 0 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 192, 0 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 240, 0 , gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 48 , gp.tileSize, gp.tileSize, null);//wall
        g2.drawImage(tile[0].image, 48, 48 , gp.tileSize, gp.tileSize, null);//grass
        g2.drawImage(tile[0].image, 96, 48 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 48 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 192, 48 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[1].image, 240, 48 , gp.tileSize, gp.tileSize, null);//wall
        g2.drawImage(tile[1].image, 288, 48 , gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 96 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 96 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 96 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 96 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 192, 96 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 240, 96 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 288, 96 , gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 144 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 48, 144 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 96, 144 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 144, 144 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 192, 144 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 240, 144 , gp.tileSize, gp.tileSize, null);
        g2.drawImage(tile[0].image, 288, 144 , gp.tileSize, gp.tileSize, null);

        g2.drawImage(tile[1].image, 0, 192 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 48, 192 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96, 192 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 144, 192 , gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 192, 192 , gp.tileSize, gp.tileSize, null);

    }
}
