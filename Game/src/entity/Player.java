package entity;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle(8,16,32,32);

        screenX = gp.maxScreenWidth/2 - gp.tileSize/2;
        screenY = gp.maxScreenHeight/2 - gp.tileSize/2;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        // player spawn
        worldX = gp.tileSize * 2;
        worldY = gp.tileSize * 4;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            characterImages = ImageIO.read(getClass().getResourceAsStream("/player/$gothGuy.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void frameUpdate(){
        frameCounter++;
        if (frameCounter > frameDelay) {
            frameCounter = 0;
            frameIndex = (frameIndex + 1) % totalFrames;
        }
    }

    public void update(){

        if(keyH.up || keyH.down || keyH.left || keyH.right) {
            if (keyH.up) {
                direction = "up";
                frameUpdate();
            } else if (keyH.down) {
                direction = "down";
                frameUpdate();
            } else if (keyH.left) {
                direction = "left";
                frameUpdate();
            } else if (keyH.right) {
                direction = "right";
                frameUpdate();
            }
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                }
            }
        }else {
            frameIndex = 1;
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int frameX = frameIndex * 32;
        switch(direction){
            case "up":
                image = characterImages.getSubimage(frameX,96,32,32); // cutting only the wanted image
                break;
            case "down":
                image = characterImages.getSubimage(frameX,0,32,32);
                break;
            case "left":
                image = characterImages.getSubimage(frameX,32,32,32);
                break;
            case "right":
                image = characterImages.getSubimage(frameX,64,32,32);
                break;
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize, gp.tileSize,null);
    }
}
