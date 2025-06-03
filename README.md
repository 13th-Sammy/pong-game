# pong-game
This is My Second Project. 

# Basic Info
Language - Java <br>
Compile - Inside pong-game folder, run > javac *.java -d classFiles/
Run - Inside pong-game folder, run > java -cp classFiles Main

# Code Structure 
- Created PongGame.java with PongGame.class, it extends JFrame which has <br> 
methods to render window. It's constructor creates a window. <br>
- Created GamePanel.java with GamePanel class which extends JPanel, which has <br>
various grahics and input related methods, set game panel background color. 
- Added GamePanel to PongGame window through adding getContentPane.add(new GamePanel()) <br>
inside PongGame constructor.
- PongGame object which initialises a window is called from Main.java <br>

Implement Paddles :-
- Paddles will be inside GamePanel. <br>
- We create paddle height width and speed and their initial y positions. <br>
- We override paintComponent method to add two filled rectangles, <br>
across the screen with paddle width and height. Whatever is inside paintComponent <br>
gets painted. The painter object g is of Graphics class.