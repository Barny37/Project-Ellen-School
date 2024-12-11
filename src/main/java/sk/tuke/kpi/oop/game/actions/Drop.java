package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

public class Drop<T extends Keeper> extends AbstractAction<T> {

    public Drop(){
    }

    @Override
    public void execute(float deltaTime) {
        Keeper character = getActor();
        if (character == null &&character.getBackpack().peek()==null){
            setDone(true);
            return;
        }
            Collectible removeItem = getActor().getBackpack().peek();
            character.getScene().addActor(removeItem, character.getPosX(), character.getPosY());
            character.getBackpack().remove(removeItem);
       setDone(true);
    }
}

