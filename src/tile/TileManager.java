package tile;

import main.GamePanel;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile; //criamos um array de blocos("tiles")
    int mapTileNum[][];

    //CONSTRUTOR:
    public TileManager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10]; //significa que terão 10 tipos diferentes de tiles
        mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];
        
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        System.out.println(getClass().getResource("/tiles/grass.png"));
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

    public void loadMap(){

        try{
            InputStream is = getClass().getResourceAsStream("/maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol  && row < gp.maxScreenRow){
                String line = br.readLine();

                while(col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){

        }
    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y , gp.tileSize, gp.tileSize, null);//grass
            col ++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col  = 0;
                x = 0;
                row++;
                y += gp.tileSize;

            }
        }
        

    }
}
