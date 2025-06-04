# pong-game
This is My Second Project. 

# Basic Info
Language - Java <br>
Compile - Inside pong-game folder, run > javac *.java -d classFiles/ <br>
Run - Inside pong-game folder, run > java -cp classFiles Main

# Code Structure 
- Created PongGame.java with PongGame.class, it extends JFrame which has <br> 
methods to render window. It's constructor creates a window. <br>
- Created GamePanel.java with GamePanel class which extends JPanel, which has <br>
various grahics and input related methods, set game panel background color. 
- Added GamePanel to PongGame window through adding getContentPane.add(new GamePanel()) <br>
inside PongGame constructor.
- PongGame object which initialises a window is called from Main.java <br>

Implement Paddles :- <br>
- Paddles will be inside GamePanel. <br>
- We create paddle height width and speed and their initial y positions. <br>
- We override paintComponent method to add two filled rectangles, <br>
across the screen with paddle width and height. Whatever is inside paintComponent <br>
gets painted. The painter object g is of Graphics class.

Implement Ball :- <br>
- Ball will be inside GamePanel. <br>
- Defined ball position size and velocity. Drew ball at position ballX,ballY <br>
inside paintComponent with g. <br>
- Created ball movement method to increment position with velocity and wall <br>
bounce back where if ballY < 0 or > getHeight() then vel = -vel. And ball resets to <br>
initial position for x axis out-of-screen. <br>
- Created Timer object and started it at constructor call with delay = 10ms. Method actionPerformed <br>
runs along with timer and calls moveball method and repaints everything every frame. It is an <br>
overriden method from Actionlistener.

Implement Paddle Movement :- <br>
- Made Right Paddle move automatically by incrementing paddle2Speed to y position and boundary check, <br>
called by actionPerformed block. <br>
- Implemented KeyListener class which has keyPressed(KeyEvent e), keyReleased and keyTyped. <br>
We catch W and S press inside overriden keyPressed by e.getKeycode() and increment paddle1Speed <br>
accordingly. Also added getToolkit.sync() inside timer block for smooth animations when idle. 