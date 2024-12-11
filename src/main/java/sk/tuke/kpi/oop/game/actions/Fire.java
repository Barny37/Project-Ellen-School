package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;

public class Fire<T extends Armed> extends AbstractAction<T> {

    public Fire(){
    }

    @Override
    public void execute(float deltaTime) {
        Armed character = getActor();
        if (getActor() == null){
            setDone(true);
            return;
        }
        Fireable ammunition = character.getFirearm().fire();
        if (character.getFirearm().getAmmo()>0) {
            getActor().getScene().addActor(ammunition, getActor().getPosX()+8 , getActor().getPosY()+8 );
            new Move<>(Direction.fromAngle(getActor().getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(ammunition);
            setDone(true);
        }
    }
}
