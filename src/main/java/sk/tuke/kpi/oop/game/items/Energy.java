package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;

public class Energy extends AbstractActor implements Usable<Alive>{
    public Energy(){
        super("Energy");
        setAnimation(new Animation("sprites/energy.png",16,16));
    }

    @Override
    public void useWith(Alive actor) {
        if (getScene() != null && actor != null && (actor).getHealth().getValue()<100) {
                ((Alive)actor).getHealth().restore();
                getScene().removeActor(this);
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
