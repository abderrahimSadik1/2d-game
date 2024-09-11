package entity;

import java.awt.image.BufferedImage;

/**
 * Stores variables used by playable and non-playable charachters
 */
public class Entity {
    public int worldX,worldY;
    public int speed;

    public BufferedImage characterImages;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public int frameCounter = 0;
    public int frameDelay = 10;  // Adjust this for animation speed
    public int frameIndex = 1;   // Current frame of animation
    public int totalFrames = 3;  // Number of frames per direction
}
