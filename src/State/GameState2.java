package State;

import Entity.EntityManager;
import Handler.Handler;
import World.World;

import java.awt.*;

public class GameState2 extends State{

    private World world;
    private int color;

    public GameState2(Handler handler, int color) {
        super(handler);
        State.isMap = 3;
        this.color = color;
        world = new World(handler, "res/World/World3.txt", color, handler.getWorld().getEntityManager().getPlayer().getHealth(),
                handler.getWorld().getEntityManager().getPlayer().getMp(),
                handler.getWorld().getEntityManager().getPlayer().getInventory());
        handler.setWorld(world);

        handler.getAudioPlayer().playMusic("map3Music.wav");
    }

    @Override
    public void tick() {
        world.tick();

        if(EntityManager.winFlag == true){
            handler.getAudioPlayer().stopPlaying();
            State.setState(new WinState(handler));
        }

        if(handler.getMouseManager().isRightPressed()){
            System.exit(0);
        }

        if(!handler.getEntityManager().getPlayer().isActive()){
            handler.getAudioPlayer().stopPlaying();
            State.setState(new LoseState(handler));
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
