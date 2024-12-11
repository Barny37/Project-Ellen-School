package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {
    private Animation onFanAnimation;
    private Animation offFanAnimation;
    private Reactor reactor;
    private boolean state;
    public  Cooler(Reactor reactor){
        this.reactor=reactor;
        this.state = false;
        onFanAnimation = new Animation("sprites/fan.png",32,32,0.2F,Animation.PlayMode.LOOP_PINGPONG);
        offFanAnimation = new Animation("sprites/fan.png",32,32,0,Animation.PlayMode.ONCE_REVERSED);
        updateAnimation();
    }

    public void coolReactor(){
        if (isOn() && reactor != null && reactor.getTemperature()>=0){
            this.reactor.decreaseTemperature(1);
        }
    }


    public void updateAnimation(){
        if(this.state==false){
            setAnimation(offFanAnimation);
        }else {
            setAnimation(onFanAnimation);
        }
    }

    public Reactor getReactor(){
        return reactor;
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
    @Override
    public void turnOn(){
        state = true;
        updateAnimation();
    }
    @Override
    public void turnOff(){
        state = false;
        updateAnimation();
    }
    @Override
    public boolean isOn(){
        return state;
    }
}
