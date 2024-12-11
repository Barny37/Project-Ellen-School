package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

public class AlienMother extends Alien{
    private Animation animation;
    private Health health;
    public AlienMother(){
        animation=new Animation("sprites/mother.png",112,162,0.2F,Animation.PlayMode.LOOP_PINGPONG);
        animation.pause();
        setAnimation(animation);
        health = new Health(200);
    }

    public AlienMother(int healthValue, Behaviour<?super Alien> behaviour){
        super(healthValue,behaviour);
        setAnimation(new Animation("sprites/mother.png",112,162,0.2F, Animation.PlayMode.LOOP_PINGPONG));
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public void startedMoving(Direction direction) {
        super.startedMoving(direction);
        animation.setRotation(direction.getAngle());
        animation.play();

    }

    @Override
    public void stoppedMoving() {
        super.stoppedMoving();
        animation.stop();

    }

    @Override
    public Health getHealth() {
        return health;
    }
}
