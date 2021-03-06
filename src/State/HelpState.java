package State;

import Handler.Handler;
import UI.ClickListener;
import UI.UIBackGround;
import UI.UIImageButton;
import UI.UIManager;
import graphics.Asset;

import java.awt.*;

public class HelpState extends State{
    public UIManager uiManager;

    public HelpState(Handler handler) {
        super(handler);

        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIBackGround(0,0, 800, 500, Asset.helpState));

        uiManager.addObject(new UIImageButton(600,400, 200, 71, Asset.buttonRestart, new ClickListener(){
            public void onClick(){
                handler.getMouseManager().setUiManager(null);
                State.setState(new MenuState(handler));
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
