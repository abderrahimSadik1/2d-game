import javax.swing.JFrame;

public class Window extends JFrame{

    public Window(){
        GamePanel gamePanel = new GamePanel();
        this.setTitle("Game test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.add(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        gamePanel.startGameThread();
    }
}
