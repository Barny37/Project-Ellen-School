package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Usable;

public class Locker extends AbstractActor implements Usable<Ripley> {
    private boolean used;
    private final Hammer hammer;
    public Locker(){
        hammer = new Hammer();
        used = false;
        setAnimation(new Animation("sprites/locker.png",16,16));
    }

    @Override
    public void useWith(Ripley actor) {
        if (!used ){
            actor.getScene().addActor(hammer, actor.getPosX(), actor.getPosY());
            used = true;
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
