package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction<Reactor> {
    private int increas;

    public PerpetualReactorHeating(int increas){
        this.increas=increas;
    }

    public void execute(float deltaTime){
        if (this.getActor() != null){
            this.getActor().increaseTemperature(this.increas);
        }
    }
}
