import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener
{
    // Timer for game loop
    Timer timer;
    int DELAY = 10; //milliseconds between updates
    boolean uPressed = false;
    boolean dPressed = false;
    boolean gameover = false;
    
    public GamePanel()
    {
        setBackground(Color.black);
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);

        // Start the game timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // Creating Paddles
    int paddleWidth = 10;
    int paddleHeight = 100;
    int paddle1Speed = 5; 
    int paddle2Speed = 5;
    // initial y position for paddle 1
    int paddle1Y = 250;
    // initial y position for paddle 2
    int paddle2Y = 250;

    // Creating Ball
    int ballSize = 20;
    int ballX = 400 - ballSize/2; // center horizontally
    int ballY = 300 - ballSize/2; // center vertically
    int ballSpeedX = 3; // horizontal speed
    int ballSpeedY = 2; // vertical speed

    // Paint things on Game Panel
    @Override protected void paintComponent(Graphics g)
    {
        // g paints things
        super.paintComponent(g);

        if (!gameover)
        {
            // set paddle color
            g.setColor(Color.green);
            // draw two rectangular paddles
            g.fillRect(0, paddle1Y, paddleWidth, paddleHeight);
            g.fillRect(getWidth()-paddleWidth, paddle2Y, paddleWidth, paddleHeight);
            // draw ball
            g.fillOval(ballX, ballY, ballSize, ballSize);
        }
        else
        {
            // gameover message
            g.setColor(Color.red);
            g.drawString("GAME OVER, SPACE TO CONTINUE", 320, 300);
        }
    }

    // Move the ball
    public void moveBall()
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
        {
            resetBall();
            gameover = true;
        }
        
    }

    // Reset ball to center
    public void resetBall()
    {
        ballX = 400 - ballSize / 2;
        ballY = 300 - ballSize / 2;
        // Randomize direction a bit
        ballSpeedX = (Math.random() > 0.5) ? 3 : -3;
        ballSpeedY = (Math.random() > 0.5) ? 2 : -2;
    }

    // Move Right Paddle with AI
    public void moveRPaddle() 
    {
        // Calculate the center of the paddle and ball
        int paddleCenter = paddle2Y + paddleHeight / 2;
        int ballCenter = ballY + ballSize / 2;
        
        // Only move if ball is coming towards the right paddle
        if (ballSpeedX > 0) 
        {
            // Move paddle towards ball position
            if (ballCenter < paddleCenter - 10) 
                paddle2Y -= paddle2Speed;
            else if (ballCenter > paddleCenter + 10) 
                paddle2Y += paddle2Speed;
        }
        
        // Keep paddle within screen bounds
        if (paddle2Y < 0) 
            paddle2Y = 0;
        if (paddle2Y > getHeight() - paddleHeight)
            paddle2Y = getHeight() - paddleHeight;
    }

    // Catch W and S press
    @Override public void keyPressed(KeyEvent e) 
    {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP)
            uPressed = true;  // Set flag instead of moving paddle
        if (k == KeyEvent.VK_DOWN)
            dPressed = true;
        // gameover check
        if (gameover == true)
        {
            if (k == KeyEvent.VK_SPACE)
                gameover = false;
        }
    }
    @Override public void keyReleased(KeyEvent e) 
    {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP)
            uPressed = false;  // Clear flag when key is released
        if (k == KeyEvent.VK_DOWN)
            dPressed = false;
    }
    @Override public void keyTyped(KeyEvent e) {}

    // Move Left Panel according to W and S press
    public void moveLPaddle()
    {
        // Move Paddle
        if (uPressed == true)
            paddle1Y -= paddle1Speed;
        if (dPressed == true)
            paddle1Y += paddle1Speed;

        // Keep Paddle Within Screen Bounds
        if (paddle1Y < 0) 
            paddle1Y = 0;
        if (paddle1Y > getHeight() - paddleHeight)
            paddle1Y = getHeight() - paddleHeight;
    }

    // Game Loop, called by Timer
    @Override public void actionPerformed(ActionEvent e)
    {
        if (gameover == false)
        {
            moveBall();
            moveRPaddle();
            moveLPaddle();
        }
        repaint(); // redraw everything
        getToolkit().sync(); // Force the system to process the repaint immediately for smooth fps even when idle
    }
}