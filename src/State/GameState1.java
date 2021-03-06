package State;

import Handler.Handler;
import Inventory.Inventory;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import World.World;
import graphics.Asset;

import java.awt.*;

public class GameState1 extends State{
    private World world;
    private int color;
    public static int mustHaveItem = 5;

    //NUT INVENTORY
    public UIManager uiManager;

    public GameState1(Handler handler, int color) {
        super(handler);
        State.isMap = 2;
        this.color = color;
        world = new World(handler, "res/World/World2.txt", color, handler.getWorld().getEntityManager().getPlayer().getHealth(),
                handler.getWorld().getEntityManager().getPlayer().getMp(),
                handler.getWorld().getEntityManager().getPlayer().getInventory());
        handler.setWorld(world);

        //NUT INVENTORY
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(700,10, 78, 60, Asset.buttonBag, new ClickListener(){
            public void onClick(){
                Inventory.active = !Inventory.active;
            }
        }));

        handler.getAudioPlayer().playMusic("map2Music.wav");
    }

    @Override
    public void tick() {
        world.tick();

        if(handler.getMouseManager().isRightPressed()){
            System.exit(0);
        }

        if(!handler.getEntityManager().getPlayer().isActive()){
            handler.getAudioPlayer().stopPlaying();
            State.setState(new LoseState(handler));
        }

        if(handler.getWorld().getEntityManager().getPlayer().getX() < 380 &&
                handler.getWorld().getEntityManager().getPlayer().getX() > 320 &&
                handler.getWorld().getEntityManager().getPlayer().getY() > 150 &&
                handler.getWorld().getEntityManager().getPlayer().getY() < 210&&
                handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItem().size() == mustHaveItem){
            handler.getAudioPlayer().stopPlaying();
            State.setState(new GameState2(handler, color));
        }

        //NUT INVENTORY
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);

        //NUT INVENTORY
        uiManager.render(g);
    }
}
