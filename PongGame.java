import javax.swing.*;

public class PongGame extends JFrame
{
    public PongGame()
    {
        // This Constructor creates a window
        setTitle("Pong Game");
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(new GamePanel());
    }
} 
