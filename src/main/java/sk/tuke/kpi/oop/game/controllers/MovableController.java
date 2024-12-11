package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MovableController implements KeyboardListener {
    private Movable movable;
    private Set<Direction> dirInTime;
    private Move<Movable> move;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST),
        Map.entry(Input.Key.RIGHT, Direction.EAST)
    );


    public MovableController(Movable movable) {
        dirInTime = new HashSet<>();
        this.movable = movable;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        Direction setDirection;
        Direction cDirection = Direction.NONE;
        if (keyDirectionMap.containsKey(key)){
            setDirection = keyDirectionMap.get(key);
            if (move != null){
                move.stop();
            }
            if (!dirInTime.isEmpty()){
                for (Direction a : dirInTime){
                    cDirection = setDirection.combine(a);
                }
                move = new Move<>(cDirection, Float.MAX_VALUE);
            }else{
                move = new Move<>(setDirection, Float.MAX_VALUE);
            }
            dirInTime.add(setDirection);
            if (this.movable.getScene() != null){
                movable.getScene().scheduleAction(move, movable);
            }
        }
    }


    @Override
    public void keyReleased(@NotNull Input.Key key) {
        Direction[] allDirections = new Direction[dirInTime.size()];
        Direction outDirection ;
        if ((move != null)&&(keyDirectionMap.containsKey(key))){
            dirInTime.remove(keyDirectionMap.get(key));
            if (!dirInTime.isEmpty()){
                this.dirInTime.toArray(allDirections);
                outDirection = allDirections[0];
                for (int i = 1; i < allDirections.length; i++) {
                    if (allDirections[i] != null)
                        outDirection = outDirection.combine(allDirections[i]);
                }
                move.stop();
                move = new Move<>(outDirection, Float.MAX_VALUE);
                if (movable.getScene() != null){
                    movable.getScene().scheduleAction(move, movable);
                }
            } else {
                move.stop();
            }
        }
    }
}

