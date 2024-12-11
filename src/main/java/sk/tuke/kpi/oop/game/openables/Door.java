package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;


public class Door extends AbstractActor implements Openable, Usable<Actor> {
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    private boolean opened;
    private Orientation orientation;
    public Door(String name,Orientation orientation){
        super(name);
        this.orientation = orientation;
        Animation animation= orientation.getAnimation();
        setAnimation(animation);
        animation.pause();
        opened = false;
    }

    public enum Orientation {
        VERTICAL(new Animation("sprites/vdoor.png",16,32,0.1F)),
        HORIZONTAL(new Animation("sprites/hdoor.png",32,16,0.1F));
        private Animation doorAnimation;

        Orientation(Animation animation){
            doorAnimation = animation;
        }

        public Animation getAnimation(){
            return doorAnimation;
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        close();
    }

    @Override
    public void open() {

        if(!isOpen()){
            return;
        }

        opened= true;
        if (orientation==Orientation.VERTICAL){
            getScene().getMap().getTile(getPosX()/16,getPosY()/16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile(getPosX()/16,(getPosY()/16)+1 ).setType(MapTile.Type.CLEAR);

        }else if (orientation == Orientation.HORIZONTAL){
            getScene().getMap().getTile(getPosX()/16,getPosY()/16).setType(MapTile.Type.CLEAR);
            getScene().getMap().getTile((getPosX()/16)+1,getPosY()/16 ).setType(MapTile.Type.CLEAR);
        }

        getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        getAnimation().play();
        getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        getAnimation().play();
        getScene().getMessageBus().publish(DOOR_OPENED,this);
    }

    @Override
    public void close() {
        if(isOpen()){
            return;
        }

        opened= false;

        if (orientation == Orientation.VERTICAL){
            getScene().getMap().getTile(getPosX()/16,getPosY()/16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile(getPosX()/16,(getPosY()/16)+1 ).setType(MapTile.Type.WALL);

        }else if (orientation == Orientation.HORIZONTAL) {
            getScene().getMap().getTile(getPosX()/16,getPosY()/16).setType(MapTile.Type.WALL);
            getScene().getMap().getTile((getPosX()/16)+1,getPosY()/16 ).setType(MapTile.Type.WALL);
        }
        getScene().getMessageBus().publish(DOOR_CLOSED,this);
        getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        getAnimation().play();
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public void useWith(Actor actor) {
        if (isOpen()){
            open();
        }else {
            close();
        }
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
