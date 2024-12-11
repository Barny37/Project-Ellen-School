package sk.tuke.kpi.oop.game.scenarios;

import sk.tuke.kpi.gamelib.SceneListener;



public class FirstSteps implements SceneListener {

    public FirstSteps() {

    }
//
//    @Override
//    public void sceneInitialized(@NotNull Scene scene) {
//        Ripley ripley = new Ripley();
//        Energy energy = new Energy();
//        Ammo ammo = new Ammo();
//        Wrench wrench = new Wrench();
//        Hammer hammer = new Hammer();
//        FireExtinguisher fireExtinguisher = new FireExtinguisher();
//        scene.addActor(ripley, 0, 0);
//        scene.addActor(energy,100,100);
//        scene.addActor(ammo,200,100);
//        // scene.addActor(wrench,100,200);
//        // scene.addActor(hammer,300,100);
//        // scene.addActor(fireExtinguisher,100,150);
//        ripley.getBackpack().add( fireExtinguisher);
//        ripley.getBackpack().add( wrench);
//        ripley.getBackpack().add( hammer);
//        ripley.getHealth().restore();
//        MovableController movableController = new MovableController(ripley);
//        KeeperController keeperController = new KeeperController(ripley);
//        Disposable moves = scene.getInput().registerListener(movableController);
//        Disposable keeprs = scene.getInput().registerListener(keeperController);
//    }
//
//    @Override
//    public void sceneUpdating(@NotNull Scene scene) {
//        int windowHeight = scene.getGame().getWindowSetup().getHeight();
//        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
//        Ripley player =(Ripley) scene.getFirstActorByName("Ellen");
//        scene.getGame().pushActorContainer(player.getBackpack());
//        scene.getGame().getOverlay().drawText(" | Energy" + player.getHealth().getValue(),100,yTextPos);
//        scene.getGame().getOverlay().drawText(" | Ammo" + player.getFirearm().getAmmo(),230,yTextPos);
//    }
}

