package Game;

import Game.bases.Contraints;
import Game.bases.GameObject;
import Game.enemies.EnemySpawner;
import Game.inputs.InputManager;
import Game.player.Player;
import Game.player.PlayerSpell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

/**
 * Created by Admin on 7/9/2017.
 */
public class GameWindow extends JFrame{
    private BufferedImage background;

    BufferedImage backBufferImage;
    Graphics2D backBufferGraphics2D;
    InputManager inputManager = new InputManager() ;


    int backgroundY;

//    Player player = new Player();
//    ArrayList<PlayerSpell> playerSpells = new ArrayList<>();




    public GameWindow(){
        setupWindow();
        loadImages();
        addPlayer();
        addEnemySpawner();
        addBackGround();

        backgroundY = this.getHeight()-background.getHeight();
        backBufferImage = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics2D = (Graphics2D) backBufferImage.getGraphics();

        this.setVisible(true);
            }

    private void addBackGround() {
    }


    private void addEnemySpawner() {
        GameObject.add(new EnemySpawner());
    }

    private void addPlayer() {
        Player player = new Player();
        player.setInputManager(inputManager);
        player.setContraints(new Contraints(20, this.getHeight(), 0 ,background.getWidth()));
        player.position.set(background.getWidth()/2, this.getHeight() - 50);
        GameObject.add(player);

    }


    long lastUpdateTime;
    public void loop(){
        while(true){

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime > 17) {
                lastUpdateTime = currentTime;
                run();
                render();

            }


            }}


    private void run(){
        if (backgroundY <=0)
            backgroundY ++;

        GameObject.runall();
    }
    private void render() {
        backBufferGraphics2D.setColor(Color.BLACK);
        backBufferGraphics2D.fillRect(0,0,this.getWidth(),this.getHeight());

        backBufferGraphics2D.drawImage(background,0, backgroundY,null);

        GameObject.renderall(backBufferGraphics2D);

        Graphics2D g2d= (Graphics2D)this.getGraphics();
        g2d.drawImage(backBufferImage, 0, 0, null);
    }

    private void loadImages() {

        background = Utils.loadImage("assets/images/background/0.png");

    }


    private void setupWindow() {
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Game do hoi - cover by HieuRuoi");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
                super.windowClosing(e);

            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.keyReleased(e);
            }
        });
    }
   @Override
    public void paint(Graphics g) {
        Graphics2D g2d= (Graphics2D)g; //ep kieu

        g2d.drawImage(backBufferImage,0,0,null);

        //cast, convert
    }

}
