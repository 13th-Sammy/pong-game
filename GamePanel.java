import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener
{
    // Timer for game loop
    Timer timer;
    int DELAY = 10; //milliseconds between updates
    
    public GamePanel()
    {
        setBackground(Color.black);
        setFocusable(true);

        // Start the game timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // Creating Paddles
    int paddleWidth = 10;
    int paddleHeight = 100;
    int paddleSpeed = 5;
    // initial y position for paddle 1
    int paddle1Y = 250;
    // initial y position for paddle 2
    int paddle2Y = 250;

    // Creating Ball
    int ballSize = 20;
    int ballX = getWidth()/2 - ballSize/2; // center horizontally
    int ballY = getHeight()/2 - ballSize/2; // center vertically
    int ballSpeedX = 3; // horizontal speed
    int ballSpeedY = 2; // vertical speed

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

        // Bounce off paddles
        if ((ballX+ballSize >= getWidth()-paddleWidth && ballY >= paddle2Y && ballY <= paddle2Y+paddleHeight) || (ballX <= paddleWidth && ballY >= paddle1Y && ballY <= paddle1Y+paddleHeight))
            ballSpeedX = -ballSpeedX;
        
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

    // Move Right Paddle Automatically
    private void moveRPaddle()
    {
        paddle2Y += paddleSpeed;
        if (paddle2Y <= 0 || paddle2Y >= getHeight() - paddleHeight)
            paddleSpeed = - paddleSpeed;
    }

    // Game Loop, called by Timer
    @Override public void actionPerformed(ActionEvent e)
    {
        moveBall();
        moveRPaddle();
        repaint(); // redraw everything
    }
}