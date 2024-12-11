package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;


import java.util.HashSet;
import java.util.Set;

public class Reactor extends AbstractActor implements Switchable,Repairable {
    private int temperature;
    private int damage;
    private boolean state;
    private Set<EnergyConsumer> devices;
    private Animation reactorOffAnimation;
    private Animation normalAnimation;
    private Animation redAnimation;
    private Animation destroyedAnimation;
    private Animation reactorE;




    public Reactor(){
        devices = new HashSet<>();
        temperature = 0;
        damage = 0;
        state = false;
        reactorOffAnimation = normalAnimation = new Animation("sprites/reactor.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        redAnimation = new Animation("sprites/reactor_hot.png",80,80,0.05f,Animation.PlayMode.LOOP_PINGPONG);
        destroyedAnimation = new Animation("sprites/reactor_broken.png",80,80,0.1f,Animation.PlayMode.LOOP_PINGPONG);
        reactorE = new Animation("sprites/reactor_extinguished.png");
        updateAnimation();
    }
    @Override
    public boolean isOn(){
        return state;
    }
    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }
    @Override
    public void turnOff(){
        this.state=false;
        this.updateAnimation();
        devices.forEach(device->{
            device.setPowered(false);
        });
    }
    @Override
    public void turnOn(){
        if (this.getDamage()<100)
        this.state=true;
        this.updateAnimation();
        devices.forEach(device->{
            device.setPowered(true);
        });
    }
    private void updateAnimation() {
        if (!this.isOn()  && this.getDamage() < 100 ) {
            setAnimation(this.reactorOffAnimation);
        } else {
            if (this.getTemperature() >= 6000)  {
                setAnimation(this.destroyedAnimation);
            } else if (this.getTemperature() >= 4000 && this.getTemperature() < 6000) {
                setAnimation(this.redAnimation);
            } else if (this.getTemperature() < 4000){
                setAnimation(this.normalAnimation);
            }
        }
        }


    public void increaseTemperature(int increment){
        if (increment<= 0 || this.damage>=100 || !this.state ){
            return;
        }
        if (this.damage>66){
            temperature+= (int)Math.ceil(increment*2);
        } else if (damage<33){
            temperature += increment;
        }else {
            temperature += (int)Math.ceil(increment * 1.5);
        }
        if (this.getTemperature()>2000){
            int damage =  Math.min((this.getTemperature()-2000)/40,100) ;
            setDamage(Math.max(damage,this.damage));
        }
        this.updateAnimation();
    }

    public void decreaseTemperature(int decrement){
        if (decrement< 0 || this.damage>=100 || !this.state ){
            return;
        }
        if (this.damage >= 50 && decrement >=2){
            temperature -=  (int)Math.ceil(decrement/2);
        } else if(this.damage<50){
            temperature -= decrement;
        }
        this.updateAnimation();
    }


    public boolean extinguish(){
        if(damage < 100 ){
            return false;
        }
        setAnimation(reactorE);
        setTemperature(4000);
        return true;
    }
    public boolean isRunning(){
        return isOn();
    }

    public void addDevice(EnergyConsumer device){
        devices.add(device);
        device.setPowered(this.getDamage()<100 && this.isOn());
    }
    public void removeDevice(EnergyConsumer device){
        devices.remove(device);
        device.setPowered(false);
    }
    public void setDamage(int damage){
        this.damage=damage;
        if (this.damage >=100){
            this.turnOff();
        }
    }
    public void setTemperature(int temperature){
        this.temperature=temperature;
    }
    @Override
    public void addedToScene(Scene scene){
        super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleFor(this);
    }

    public boolean repair(){
        if (damage <=0){
            return false;
        }
        if (getDamage()>=50 && getDamage()<100){
            this.setDamage(getDamage()-50);
        }else {
            damage = 0;
            setDamage(0);
        }
        int temperature = 2000 + (damage * 40);
        if (temperature< getTemperature()){
            setTemperature(temperature);
        }
        return true;
    }
}






