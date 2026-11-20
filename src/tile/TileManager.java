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
        mapTileNum = new int [gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap();
    }

    public void getTileImage(){
        System.out.println(getClass().getResource("/tiles/grass.png"));
        try{
            tile[0] = new Tile();//grass
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();//wall
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));

            tile[2] = new Tile();//water
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            tile[3] = new Tile();//earth
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();//tree
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));

            tile[5] = new Tile();//sand
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){

        try{
            InputStream is = getClass().getResourceAsStream("/maps/WorldMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol  && row < gp.maxWorldRow){
                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();  //se acontecer algum erro no carregamento do mapa aparece a mensagem no terminal.
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.cameraX + gp.screenWidth / 2;
            int screenY = worldY - gp.cameraY + gp.screenHeight / 2;

            //Esse if serve para desenhar somente os tiles que estão próximos da câmera, evitando percorrer 
            //e desenhar partes do mapa que estão fora da tela.
            if(worldX > gp.cameraX - gp.screenWidth / 2 - gp.tileSize &&
                worldX < gp.cameraX + gp.screenWidth / 2 + gp.tileSize &&
                worldY > gp.cameraY - gp.screenHeight / 2 - gp.tileSize &&
                worldY < gp.cameraY + gp.screenHeight / 2 + gp.tileSize){

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            worldCol ++;

            if(worldCol == gp.maxWorldCol){
                worldCol  = 0;
                worldRow++;

            }
        }
    }
}
