//package sk.tuke.kpi.oop.game.scenarios;
//
//import org.jetbrains.annotations.NotNull;
//import org.jetbrains.annotations.Nullable;
//import sk.tuke.kpi.gamelib.*;
//
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
//import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.oop.game.Locker;
//import sk.tuke.kpi.oop.game.Ventilator;
//import sk.tuke.kpi.oop.game.characters.Ripley;
//import sk.tuke.kpi.oop.game.controllers.KeeperController;
//import sk.tuke.kpi.oop.game.controllers.MovableController;
//import sk.tuke.kpi.oop.game.items.AccessCard;
//import sk.tuke.kpi.oop.game.items.Energy;
//import sk.tuke.kpi.oop.game.openables.Door;
//import sk.tuke.kpi.oop.game.openables.LockedDoor;
//
//
//import java.util.concurrent.atomic.AtomicReference;
//
//public class MissionImpossible implements SceneListener {
//    public static class Factory implements ActorFactory {
//        @Override
//        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
//            switch (name) {
//                case "ellen":
//                    return new Ripley();
//                case "energy":
//                    return new Energy();
//                case "access card":
//                    return new AccessCard();
////                case "door":
////                    return new LockedDoor("name", Door.Orientation.VERTICAL);
//                case "locker":
//                    return new Locker();
//                case "ventilator":
//                    return new Ventilator();
//                case "door":
//                    return new Door("door", Door.Orientation.VERTICAL);
//                }
//                return null;
//            }
//        }
//
//        private void damagingPlayer(@NotNull Scene scene, Ripley character) {
//            character.getHealth().drain(1);
//        }
//
//        private boolean turnOffDamaging(@NotNull Scene scene) {
//            Ventilator ventilator = (Ventilator) scene.getFirstActorByName("Ventilator");
//            return ventilator.isState();
//        }
//
//        private void callingAS(@NotNull Scene scene, Ripley character) {
//            new ActionSequence<>(new Wait<>(2), new Invoke<>(() -> {
//                damagingPlayer(scene, character);
//
//            })).scheduleFor(character);
//        }
//
//        @Override
//        public void sceneInitialized(@NotNull Scene scene) {
//            Ripley character = (Ripley) scene.getFirstActorByName("Ellen");
//            Door door = (Door) scene.getFirstActorByName("Door");
//            AtomicReference<Loop<?>> loop = new AtomicReference<>();
//            MovableController movableController = new MovableController(character);
//            KeeperController keeperController = new KeeperController(character);
//            Disposable moves = scene.getInput().registerListener(movableController);
//            Disposable keeprs = scene.getInput().registerListener(keeperController);
//            scene.follow(character);
//
//
//        scene.getMessageBus().subscribe(character.RIPLEY_DIED,(Ripley ->{
//            moves.dispose();
//            keeprs.dispose();
//        }));
//        }
//
//        @Override
//        public void sceneUpdating(@NotNull Scene scene) {
//            int windowHeight = scene.getGame().getWindowSetup().getHeight();
//            int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
//            Ripley character = (Ripley) scene.getFirstActorByName("Ellen");
//            scene.getGame().pushActorContainer(character.getBackpack());
//            scene.getGame().getOverlay().drawText(" | Energy" + character.getHealth().getValue(), 100, yTextPos);
//            scene.getGame().getOverlay().drawText(" | Ammo" + character.getFirearm().getAmmo(), 230, yTextPos);
//        }
//    }

