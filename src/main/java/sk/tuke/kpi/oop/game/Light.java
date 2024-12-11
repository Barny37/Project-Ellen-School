package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable,EnergyConsumer{
    private Animation lightOnAnimation;
    private Animation lightOffAnimation;
    private boolean lightState;
    private boolean electricityState;

    public Light(){
        lightState=false;
        electricityState=false;
        this.lightOnAnimation = new Animation("sprites/light_on.png");
        this.lightOffAnimation = new Animation("sprites/light_off.png");
        updateAnimation();
    }
    public void toggle(){
        if (!lightState){
            this.lightState=true;
            updateAnimation();
        }else{
            this.lightState=false;
            updateAnimation();
        }
    }
    public void setElectricityFlow(boolean flow){
        this.electricityState=flow;
        updateAnimation();
    }
    public void updateAnimation(){
        if(lightState && electricityState){
            setAnimation(lightOnAnimation);
        }else{
            setAnimation(lightOffAnimation);
        }
    }
    public void setLightState(boolean state){
        this.lightState=state;
    }
    @Override
    public void turnOn(){
        lightState= true;
        updateAnimation();
    }
    @Override
    public void turnOff(){
        lightState= false;
        updateAnimation();
    }
    @Override
    public boolean isOn(){
        return lightState;
    }
    @Override
    public void setPowered(boolean powered){
        setElectricityFlow(powered);
    }

}
