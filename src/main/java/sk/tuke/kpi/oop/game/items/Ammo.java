package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;


public class Ammo extends AbstractActor implements Usable<Armed> {
    public Ammo(){
        setAnimation(new Animation("sprites/ammo.png",16,16));
    }

    @Override
    public void useWith(Armed actor) {
        if (getScene() != null && actor != null){
           (actor).getFirearm().reload(50);
           getScene().removeActor(this);
        }
    }

    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }

}
