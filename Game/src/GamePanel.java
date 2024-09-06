import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int OriginalTileSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = OriginalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int maxScreenWidth = maxScreenCol * tileSize;
    final int maxScreenHeight = maxScreenRow * tileSize;

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    //set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(maxScreenWidth,maxScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*@Override
    public void run() {
        double drawInterval = (double) 1000000000 /FPS; // 0.01667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null) {
            long currentTime = System.nanoTime();
            // UPDATE: update information
            update();
            // DRAW: draw the screen with updated information
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if(remainingTime < 0)
                    remainingTime = 0;
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    @Override
    public void run(){
        double drawInterval = (double) 1000000000 / (FPS);
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update(){

        if(keyH.up){
            playerY -= playerSpeed;
        }
        if(keyH.down){
            playerY += playerSpeed;
        }
        if(keyH.left){
            playerX -= playerSpeed;
        }
        if(keyH.right){
            playerX += playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.blue);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}