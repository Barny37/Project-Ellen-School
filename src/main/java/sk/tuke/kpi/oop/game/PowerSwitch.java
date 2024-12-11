package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch extends AbstractActor implements Switchable {

    private Switchable switchable;
    public PowerSwitch(Switchable switchable) {
        Animation buttonAnimation;
        this.switchable=switchable;
        buttonAnimation = new Animation("sprites/switch.png");
        setAnimation(buttonAnimation);
    }
    public void toggle(){
        if (!this.isOn()){
            this.turnOn();
        }else if(this.isOn()){
            this.turnOff();
        }
    }
    public Switchable getDevice(){
        return switchable;
    }
    public void switchOff(){
        if(this.getDevice() != null){
            this.getAnimation().setTint(Color.GRAY);
            this.switchable.turnOff();
        }
    }
    public void switchOn(){
        if(this.getDevice() != null){
            this.getAnimation().setTint(Color.WHITE);
            this.switchable.turnOn();
        }
    }
    @Override
    public void turnOff(){
        switchOff();
    }
    @Override
    public void turnOn(){
        switchOn();
    }
    @Override
    public boolean isOn(){
        return switchable.isOn();
    }
}
