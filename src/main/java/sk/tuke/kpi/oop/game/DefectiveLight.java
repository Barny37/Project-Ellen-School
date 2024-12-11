package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light implements Repairable {

    private Disposable lightF;

    public DefectiveLight() {
        super();
        this.setLightState(true);
    }

    private void flashing() {
        if ((new Random().nextInt(20) + 1) == 1) {
            this.toggle();
        }
    }

    public void simulate() {
        this.lightF = new Loop<>(new Invoke<>(this::flashing)).scheduleFor(this);
    }

    public void stopSimulate() {
        if (lightF != null) {
            lightF.dispose();
            lightF = null;
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        simulate();
    }

    @Override
    public boolean repair() {
        if (lightF == null){
            return false;
        }
        if (isOn()){
            turnOn();
        }
        new ActionSequence<>(
            new Invoke<>(this::stopSimulate), new Wait<>(10), new Invoke<>(this::simulate)
        ).scheduleFor(this);
        return true;
    }
}

