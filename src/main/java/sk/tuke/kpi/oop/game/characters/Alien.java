package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class Alien extends AbstractActor implements Movable,Alive,Enemy {
    private Health health;
    private Behaviour<? super Alien> behaviour;
    public Alien(){
        super("Alien");
        setAnimation(new Animation("sprites/alien.png",32,32,0.1F, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().setRotation(0);
        getAnimation().pause();
        health = new Health(100);
    }

    public Alien(int healthValue, Behaviour<?super Alien> behaviour){
        health = new Health(healthValue);
        this.behaviour = behaviour;
        setAnimation(new Animation("sprites/alien.png",32,32,0.1F, Animation.PlayMode.LOOP_PINGPONG));
    }


    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().stop();
    }

    @Override
    public Health getHealth() {
        if (this.health.getValue()<= 0){
            getScene().removeActor(this);
        }
        return health;
    }
    private void damagingPlayer(){
        getScene().getActors().forEach((foundActor)->{
            if ((foundActor instanceof Alive) &&(!(foundActor instanceof Enemy)) && (foundActor.intersects(this))){
                ((Alive) foundActor).getHealth().drain(1);
            }
        });
    }
    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        if (behaviour != null){
            behaviour.setUp(this);
        }
        getScene().getActors().forEach((foundActor)->{
            new Loop<>(new ActionSequence<>(new Wait<>(1) , new Invoke<>(this::damagingPlayer))).scheduleFor(this);
        });
    }
}
