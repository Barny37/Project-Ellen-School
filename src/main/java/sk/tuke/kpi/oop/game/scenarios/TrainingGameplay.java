package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.items.Hammer;

public class TrainingGameplay extends Scenario implements SceneListener {

    @Override
    public void setupPlay(@NotNull Scene scene) {
        Reactor reactor = new Reactor();  //
        scene.addActor(reactor, 64, 64);
        reactor.turnOn();

        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler,100,140);
        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);

        Hammer hammer= new Hammer();
        scene.addActor(hammer,100,180);
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> reactor.repair())
        ).scheduleFor(hammer);
    }


}
