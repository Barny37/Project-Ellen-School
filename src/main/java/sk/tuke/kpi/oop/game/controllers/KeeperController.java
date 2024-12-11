package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;



public class KeeperController implements KeyboardListener {
    private Keeper character;

    public KeeperController(Keeper keeper) {
        character = keeper;
    }

    @Override
    public void keyPressed(@NotNull Input.Key key) {
        switch (key) {
            case ENTER:
                new Take<>().scheduleFor(character);
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(character);
                break;
            case S:
                new Shift<>().scheduleFor(character);
                break;
            case U:
                this.character.getScene().getActors().forEach((foundActor) -> {
                    if (foundActor instanceof Usable && character.intersects(foundActor)) {
                        new Use<>((Usable<?>) foundActor).scheduleForIntersectingWith(character);
                    }
                });

                break;
            case B:
                this.character.getScene().getActors().forEach((foundActor) -> {
                    if ( !(character.getBackpack().peek() instanceof Usable) || character.getBackpack().peek() == null || character.getBackpack().getSize() == 0 ){
                        return;
                    }
                    new Use<>((Usable<?>) character.getBackpack().peek()).scheduleForIntersectingWith(character);
                });
                break;
            default:
                break;
        }
    }
}
