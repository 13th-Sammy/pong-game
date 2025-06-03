import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel
{
    public GamePanel()
    {
        setBackground(Color.black);
    }

    // Creating Paddles
    private int paddleWidth = 10;
    private int paddleHeight = 100;
    private int paddleSpeed = 5;
    // initial y position for paddle 1
    private int paddle1Y = 250;
    // initial y position for paddle 2
    private int paddle2Y = 250;

    // Paint things on Game Panel
    @Override protected void paintComponent(Graphics g)
    {
        // g paints things
        super.paintComponent(g);

        // set paddle color
        g.setColor(Color.green);
        // draw two rectangular paddles
        g.fillRect(0, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(getWidth()-paddleWidth, paddle2Y, paddleWidth, paddleHeight);
    }
}