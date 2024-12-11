package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Ventilator extends AbstractActor implements Repairable {
    private boolean state;
    public Ventilator(){
        super("Ventilator");
        state =false;
        setAnimation(new Animation("sprites/ventilator.png",32,32,0.1F, Animation.PlayMode.ONCE));
    }
    public boolean isState(){
        return state;
    }
    @Override
    public boolean repair() {
        setAnimation(new Animation("sprites/ventilator.png",32,32,0.1F, Animation.PlayMode.LOOP_PINGPONG));
        state = true;

        return true;
    }
}
