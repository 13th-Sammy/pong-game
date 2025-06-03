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
