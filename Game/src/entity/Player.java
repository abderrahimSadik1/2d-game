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

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
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

    public void update(){

        if(keyH.up){
            direction = "up";
            y -= speed;
        }
        if(keyH.down){
            direction = "down";
            y += speed;
        }
        if(keyH.left){
            direction = "left";
            x -= speed;
        }
        if(keyH.right){
            direction = "right";
            x += speed;
        }

    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = characterImages.getSubimage(32,96,32,32); // cutting only the wanted image
                break;
            case "down":
                image = characterImages.getSubimage(32,0,32,32);
                break;
            case "left":
                image = characterImages.getSubimage(32,32,32,32);
                break;
            case "right":
                image = characterImages.getSubimage(32,64,32,32);
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize, gp.tileSize,null);
    }
}
