package Entity.Weapon;

import Entity.Weapon.Sword;
import Handler.Handler;
import graphics.Asset;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sword1 extends Sword {
    BufferedImage img;

    //THOI GIAN CACH NHAU GIUA MOI TRU MAU
    private long lastAttackTimer;
    private long attackCooldown = 200;// CAN CHINH THOI GIAN QUY DINH THOI GIAN TRU MAU (TRU MAU NHANH HON HAY CHAM HON)
    private long attackTimer = attackCooldown;

    private int xMove;
    private int yMove;
    private int speed = 2;

    public Sword1(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 21;
        bounds.height = 25;
    }

    @Override
    public void tick() {
        getInput();
        x += xMove;
        y += yMove;

        //TRU MAU CUA QUAI KHI CHAM VAO SWORD
        checkAttack(1);
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int)(x - handler.getGameCamera().getxOffset() + 10 ), (int)(y - handler.getGameCamera().getyOffset() + 10 ), width, height, null);
        //g.fillRect((int)(x - handler.getGameCamera().getxOffset() + 10 ), (int)(y - handler.getGameCamera().getyOffset() + 10 ), bounds.width, bounds.height);
    }

    public void getInput(){
        if(handler.getKeyManager().aUp && xMove == 0 && yMove == 0){
            yMove = -speed;
            xMove = 0;
            img = Asset.swordUp;
            handler.getAudioPlayer().playSound("throwknife.wav");
        }
        if(handler.getKeyManager().aDown && xMove == 0 && yMove == 0){
            yMove = speed;
            xMove = 0;
            img = Asset.swordDown;
            handler.getAudioPlayer().playSound("throwknife.wav");
        }
        if(handler.getKeyManager().aLeft && xMove == 0 && yMove == 0){
            xMove = -speed;
            yMove = 0;
            img = Asset.swordLeft;
            handler.getAudioPlayer().playSound("throwknife.wav");
        }
        if(handler.getKeyManager().aRight && xMove == 0 && yMove == 0){
            xMove = speed;
            yMove = 0;
            img = Asset.swordRight;
            handler.getAudioPlayer().playSound("throwknife.wav");
        }
    }
}
