package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;

public class Teleport extends AbstractActor {
    private boolean possibleTelep;
    private Teleport teleportDest;
    public Teleport(Teleport teleportDest){
        Animation liftAnimation;
        this.teleportDest=teleportDest;
        liftAnimation=new Animation("sprites/lift.png");
        setAnimation(liftAnimation);
    }

    public Teleport getDestination(){
        return teleportDest;
    }
    public void setDestination(Teleport destinationTeleport){
        if (this != destinationTeleport){
            teleportDest=destinationTeleport;
        }
    }
    public void teleportPlayer(Player player){
        if(player != null){
            setPossibleTelep();
            player.setPosition(
                this.getPosX()+ (getWidth()-player.getWidth())/2,
                this.getPosY()+(getHeight()-player.getHeight())/2
            );
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new When<>(
            action->{
                Player player =getPlayer();
                return (teleportDest !=null) && (isStandingPlayer(player) && !possibleTelep) ;
            },
            new Invoke<>(() ->{
                teleportDest.teleportPlayer(getPlayer());
            })
        )).scheduleFor(this);
    }
    public void setPossibleTelep(){
        possibleTelep=true;
        new When<>(
            action -> !this.isStandingPlayer(getPlayer()),
            new Invoke<>(this::isNotPossibleTelep)
        ).scheduleFor(this);
    }
    public void isNotPossibleTelep(){
        possibleTelep=false;
    }
    public Player getPlayer(){
        if (getScene()!=null){
            return (Player) getScene().getFirstActorByName("Player");
        }else{
            return null;
        }
    }
    public boolean isStandingPlayer(Player player){
        if (player == null){
            return false;
        }
        int newX = player.getPosX() + player.getWidth()/2;
        int newY = player.getPosY() + player.getHeight()/2;
        Rectangle2D.Float newRectangle = new Rectangle2D.Float(newX,newY,1,1);
        return newRectangle.intersects(getPosX(),getPosY(),getHeight(),getWidth());
    }
}
