import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener
{
    // Timer for game loop
    private Timer timer;
    private final int DELAY = 10; //milliseconds between updates
    
    public GamePanel()
    {
        setBackground(Color.black);
        setFocusable(true);

        // Start the game timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // Creating Paddles
    private int paddleWidth = 10;
    private int paddleHeight = 100;
    private int paddleSpeed = 5;
    // initial y position for paddle 1
    private int paddle1Y = 250;
    // initial y position for paddle 2
    private int paddle2Y = 250;

    // Creating Ball
    private int ballSize = 20;
    private int ballX = 400 - ballSize/2; // center horizontally
    private int ballY = 300 - ballSize/2; // center vertically
    private int ballSpeedX = 3; // horizontal speed
    private int ballSpeedY = 2; // vertical speed

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
        g.fillOval(ballX, ballY, ballSize, ballSize);
    }

    // Move the ball
    private void moveBall()
    {
        // Move ball by its speed
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        
        // Bounce off top and bottom walls
        if (ballY <= 0 || ballY >= getHeight() - ballSize)
            ballSpeedY = -ballSpeedY; // reverse vertical direction
        
        // Reset ball if it goes off left or right edges (temporary - will add scoring later)
        if (ballX <= 0 || ballX >= getWidth() - ballSize) 
            resetBall();
        
    }
    
    // Reset ball to center
    private void resetBall()
    {
        ballX = getWidth() / 2 - ballSize / 2;
        ballY = getHeight() / 2 - ballSize / 2;
        // Randomize direction a bit
        ballSpeedX = (Math.random() > 0.5) ? 3 : -3;
        ballSpeedY = (Math.random() > 0.5) ? 2 : -2;
    }

    // Game Loop, called by Timer
    public void actionPerformed(ActionEvent e)
    {
        moveBall();
        repaint(); // redraw everything
    }
}