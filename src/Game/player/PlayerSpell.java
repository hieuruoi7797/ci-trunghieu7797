package Game.player;

import Game.Utils;
import Game.bases.ImageRenderer;
import Game.bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by Admin on 7/11/2017.
 */
public class PlayerSpell {
    public Vector2D position;
    public ImageRenderer imageRenderer;

    public PlayerSpell(){
        position = new Vector2D();
        this.imageRenderer = new ImageRenderer(Utils.loadAssetImage("player-spells/a/0.png"));
    }

    public void move(){
        this.position.addUp(0, -10);
    }
    public void render(Graphics2D g2d){
        imageRenderer.render(g2d, this.position);

    }


}
