package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {
    private int remainingUses;
    public BreakableTool(int uses){
        remainingUses=uses;
    }

    public int getRemainingUses(){
        return remainingUses;
    }
    public void setRemainingUses(int remainingUses){
        this.remainingUses=remainingUses;
    }
    @Override
    public void useWith(A actor){
        remainingUses -=1;
        if (remainingUses<=0 && getScene() != null){
            getScene().removeActor(this);
        }
    }
}
