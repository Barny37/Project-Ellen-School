package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;



public class Move <T extends Movable> implements Action<T> {
    private boolean state;
    private T actor;
    private Direction direction;
    private float time;
    private boolean firstTime;

    public Move(Direction direction, float duration) {
        this.direction = direction;
        time = duration;
        state = false;
        firstTime = false;
    }

    @Override
    public T getActor() {
        return actor;
    }

    @Override
    public void setActor(T actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return state;
    }

    @Override
    public void reset() {
        state = false;
    }

    public void stop() {
        if (actor == null && isDone()) return;
        state = true;
        getActor().stoppedMoving();
    }

    @Override
    public void execute(float deltaTime) {

        if (actor == null) return;

        if (!state) {
            if (!firstTime) {
                firstTime = true;
                getActor().startedMoving(direction);
            }

            int newX =direction.getDx() * getActor().getSpeed();
            int newY =direction.getDy() * getActor().getSpeed();

            actor.setPosition(actor.getPosX()+newX, actor.getPosY()+newY);

            if (actor.getScene().getMap().intersectsWithWall(actor)) {
                actor.setPosition(actor.getPosX()-newX,actor.getPosY()-newY);
                actor.collidedWithWall();
            }

        }

            time -= deltaTime;
            if (time <= 0) {
                stop();
            }
        }
    }

