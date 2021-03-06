package Entity.Character;

import Entity.Entity;
import Handler.Handler;
import State.LevelState;
import Tiles.Tile;

public abstract class Creature extends Entity {

    //CREATURES AND COLLISION
    public static final int DEFAULT_CREATURE_WIDTH = 48;
    public static final int DEFAULT_CREATURE_HEIGHT = 48;

    protected float speed;
    protected float xMove;
    protected float yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        if(LevelState.speed > 0 ){
            speed = LevelState.speed;
        }else{
            speed = 1;
        }
        xMove = 0;
        yMove = 0;
    }

    //CHECK ENTITY COLLISION
    public void move(){
        if(!checkEntityCollision(xMove, 0f)){
            moveX();
        }
        if(!checkEntityCollision(0f, yMove)){
            moveY();
        }
    }

    //DAM BAO NHAN VAT KHONG DI QUA DUOC TILES SOLID
    private void moveX(){
        if(xMove > 0 ){//MOVING RIGHT
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
            && !collisionWithTile(tx, (int) (y + bounds.y +bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            }else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width -1;
            }
        }else if(xMove < 0 ){//MOVING LEFT
            int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT)
                    && !collisionWithTile(tx, (int) (y + bounds.y +bounds.height) / Tile.TILE_HEIGHT)){
                x += xMove;
            }else {
                x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
            }
        }
    }

    //DAM BAO NHAN VAT KHONG DI QUA DUOC TILES SOLID
    private void moveY(){
        if(yMove < 0){//UP
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty)
            && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            }else {
                y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
            }
        }else if(yMove > 0){//DOWN
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
            if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty)
                    && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)){
                y += yMove;
            }else{
                y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height -1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //GETTER AND SETTER
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int amt) {
        this.health = health + amt;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
