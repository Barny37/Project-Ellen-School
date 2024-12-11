package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;


public class Take<T extends Keeper> extends AbstractAction<T> {

    public Take(){

        setDone(false);
    }

    @Override
    public void execute(float deltaTime) {
        Keeper character = getActor();
        if (character != null) {
            try {
                   character.getScene().getActors().forEach((foundActor)->{
                       if (character.intersects(foundActor) && foundActor instanceof Collectible){
                           character.getBackpack().add((Collectible) foundActor);
                           character.getScene().removeActor(foundActor);
                       }
                   });
            } catch (Exception ex){
                character.getScene().getGame().getOverlay().drawText(ex.getMessage(), 0, 0).showFor(2);
            }
        }
        setDone(true);
    }
}
