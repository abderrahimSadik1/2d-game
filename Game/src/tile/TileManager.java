package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;
    BufferedImage imageDungeon;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap();
        getTileImage();
    }

    public void getTileImage(){
        try{
            imageDungeon = ImageIO.read(getClass().getResourceAsStream("/0x72_DungeonTilesetII_v1.7.png"));
            tile[0] = new Tile();
            tile[0].image = imageDungeon.getSubimage(16,64,16,16);// default floor

            tile[1] = new Tile();
            tile[1].image = imageDungeon.getSubimage(16,16,16,16);// center wall
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = imageDungeon.getSubimage(32,64,16,16);// crack floor

            tile[3] = new Tile();
            tile[3].image = imageDungeon.getSubimage(48,32,16,16);// center cracked wall

            tile[4] = new Tile();
            tile[4].image = imageDungeon.getSubimage(0,155,16,16);// side left wall
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = imageDungeon.getSubimage(16,0,16,16);// top wall

            tile[6] = new Tile();
            tile[6].image = imageDungeon.getSubimage(21,120,16,16);// Left up corner

            tile[7] = new Tile();
            tile[7].image = imageDungeon.getSubimage(96,80,16,16);// up tower

            tile[8] = new Tile();
            tile[8].image = imageDungeon.getSubimage(96,96,16,16);// mid tower
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = imageDungeon.getSubimage(96,112,16,16);// ground tower

            tile[10] = new Tile();
            tile[10].image = imageDungeon.getSubimage(48,64,16,16);// cracked floor 2

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/maps/map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
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
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

            while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
              int tileNum = mapTileNum[worldCol][worldRow];

              int worldX = worldCol * gp.tileSize;
              int worldY = worldRow * gp.tileSize;
              int screenX = worldX - gp.player.worldX + gp.player.screenX;
              int screenY = worldY - gp.player.worldY + gp.player.screenY;
              if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                 worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                  g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
              }

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }
        }
    }
}
