package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class TimeBomb extends AbstractActor {

    private float time;
    private boolean active;
    private Animation bombActiveAnimation;
    private Animation explosionAnimation;


    public TimeBomb(float time){
        Animation bombAnimation;
        this.time=time;
        active=false;
        bombAnimation = new Animation("sprites/bomb.png");
        bombActiveAnimation= new Animation("sprites/bomb_activated.png",16,16,0.1F,Animation.PlayMode.LOOP_PINGPONG);
        explosionAnimation = new Animation("sprites/small_explosion.png",16,16,0.1F,Animation.PlayMode.ONCE);
        setAnimation(bombAnimation);
    }
    public boolean isActivated(){
        return this.active;
    }
    public void explosion(){
        setAnimation(explosionAnimation);
        new When<>(
            (action) -> getAnimation().getFrameCount() == getAnimation().getCurrentFrameIndex()+1,
            new Invoke<>(()-> {
                Objects.requireNonNull(getScene()).removeActor(this);
            })

        ).scheduleFor(this);

    }
    public void activate(){
        this.active = true;
        setAnimation(bombActiveAnimation);
        new ActionSequence<>(new Wait<>(this.time), new Invoke<>(this::explosion)).scheduleFor(this);

    }

}
