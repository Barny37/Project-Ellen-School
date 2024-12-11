package sk.tuke.kpi.oop.game;


import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.concurrent.atomic.AtomicInteger;

public class Helicopter extends AbstractActor {

    public Helicopter(){
        Animation HeliAnimation;
        HeliAnimation=new Animation("sprites/heli.png",64,64,0.02F,Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(HeliAnimation);
    }
    private void rotation(int rotation){
        if (rotation<0){
            return;
        }
        this.getAnimation().setRotation(rotation);
    }

    private Player getPlayer(){
        if (this.getScene() != null){
            return (Player) this.getScene().getFirstActorByName("Player");
        }
        return null;
    }

    public void searchAndDestroy(){
        new Loop<>(new Invoke<>(this::persecution)).scheduleFor(this);
    }


    private void persecution(){
        Player player = getPlayer();

        if (player != null){
            int y = player.getPosY();
            int x = player.getPosX();

            int nextY, nextX;
            AtomicInteger rotation = new AtomicInteger();

            if (y > this.getPosY()){
                nextY = getPosY()+1;
                rotation.set(270);
            }else{
                nextY=this.getPosY() -1;
                rotation.set(90);
            }
            if (x > this.getPosX()){
                nextX = getPosX()+1;
                rotation.set(0);
            }else{
                nextX=this.getPosX() -1;
                rotation.set(180);
            }
            this.setPosition(nextX,nextY);
            this.rotation(rotation.get());
            if (intersects(player)){
                player.setEnergy(player.getEnergy()-1);
            }
        }
    }

}
