package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;


public class EscapeRoom implements SceneListener {
    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            switch (name){
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "ammo":
                    return new Ammo();
                case "alien":
                    if (type.equals("running")){
                        return new Alien(100,new RandomlyMoving());
                    }else{
                        return new Alien(100,null);
                    }
                case "alien mother":
                    if (type.equals("running")){
                        return new AlienMother(100,new RandomlyMoving());
                    }else{
                        return new AlienMother(100,null);
                    }
                default:
                    break;
            }
            return null;
        }
    }
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley character = (Ripley) scene.getFirstActorByName("Ellen");
        MovableController movableController = new MovableController(character);
        KeeperController keeperController = new KeeperController(character);
        ShooterController shooterController = new ShooterController(character);
        Disposable moves = scene.getInput().registerListener(movableController);
        Disposable keeprs = scene.getInput().registerListener(keeperController);
        Disposable shoots = scene.getInput().registerListener(shooterController);
        scene.follow(character);
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED,(Ripley ->{
            moves.dispose();
            keeprs.dispose();
            shoots.dispose();
        }));
    }
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        Ripley character =(Ripley) scene.getFirstActorByName("Ellen");
        scene.getGame().pushActorContainer(character.getBackpack());
        scene.getGame().getOverlay().drawText(" | Ammo" + character.getFirearm().getAmmo(),230,yTextPos);
        scene.getGame().getOverlay().drawText(" | Energy" + character.getHealth().getValue(),100,yTextPos);

    }
    @Override
    public void sceneCreated(@NotNull Scene scene) {
        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, (Actor alien)->{
            if (alien instanceof Alien){
                (alien).addedToScene(scene);
            }
        });
    }
}
