package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.characters.Alive;


public class Bullet extends AbstractActor implements Fireable {
    private Animation animation;
    public Bullet(){
        animation = new Animation("sprites/bullet.png",16,16);
        setAnimation(animation);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::hit)).scheduleFor(this);
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    @Override
    public void startedMoving(Direction direction) {
        Fireable.super.startedMoving(direction);
        animation.setRotation(direction.getAngle());
        animation.play();
    }

    @Override
    public void stoppedMoving() {
        Fireable.super.stoppedMoving();
        animation.stop();
    }

    @Override
    public void collidedWithWall() {
        getScene().removeActor(this);
    }

    private void hit(){
        getScene().getActors().forEach((foundObject)->{
            if (foundObject instanceof Alive && foundObject.intersects(this) && !(foundObject instanceof Keeper)){
                ((Alive) foundObject).getHealth().drain(10);
                getScene().removeActor(this);
            }
        });
    }
}
