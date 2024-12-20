package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible{


    public FireExtinguisher(){
        super(1);
        Animation fireEx;
        fireEx =new Animation("sprites/extinguisher.png");
        setAnimation(fireEx);
    }

   @Override
    public void useWith(Reactor actor) {
        if (actor != null && actor.extinguish()){
            super.useWith(actor);
        }
    }

    @Override
    public Class<Reactor> getUsingActorClass() {
        return Reactor.class;
    }
}
