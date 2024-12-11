package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving implements Behaviour<Movable>{
//    private boolean used;
    public RandomlyMoving(){
//        used = false;
   }

    @Override
    public void setUp(Movable actor) {
        if (actor == null){
            return;
        }
        new Loop<>(new ActionSequence<>(new Wait<>(1), new Invoke<>( () -> random(actor) ) ) ).scheduleFor(actor);
    }

    private void random(Movable actor){
        int rand = (int) (Math.random()*8);
        switch (rand){
            case 0:
                new Move<>(Direction.NORTH,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 1:
                new Move<>(Direction.NORTHWEST,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 2:
                new Move<>(Direction.WEST,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 3:
                new Move<>(Direction.SOUTHWEST,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 4:
                new Move<>(Direction.SOUTH,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 5:
                new Move<>(Direction.SOUTHEAST,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 6:
                new Move<>(Direction.EAST,Float.MAX_VALUE).scheduleFor(actor);
                break;
            case 7:
                new Move<>(Direction.NORTHEAST,Float.MAX_VALUE).scheduleFor(actor);
                break;
            default:
                break;
        }
    }
}
