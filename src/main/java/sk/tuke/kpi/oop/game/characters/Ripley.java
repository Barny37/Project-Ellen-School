package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;




public class Ripley extends AbstractActor implements Movable,Keeper,Alive,Armed {
    private Backpack backpack;
    private Health health;
    private Firearm weapon;
    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley die", Ripley.class);

    public Ripley(){
        super("Ellen");
        setAnimation (new Animation("sprites/player.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG));
        getAnimation().setRotation(0);
        getAnimation().pause();
        health = new Health(100,100);
        weapon = new Gun(50,500);
        backpack = new Backpack("Ripley's backpack",10);

    }

    @Override
    public Firearm getFirearm() {
        return weapon;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        this.weapon = weapon;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        health.onExhaustion(this::ripleyDeadMassage);
    }

    @Override
    public int getSpeed() {
        return 2;
    }

    @Override
    public void startedMoving(Direction direction) {
        getAnimation().setRotation(direction.getAngle());
        getAnimation().play();
    }

    @Override
    public void stoppedMoving() {
        getAnimation().stop();
    }

    @Override
    public Backpack getBackpack() {
        return backpack;
    }

    @Override
    public Health getHealth() {
        return health;
    }
    private void ripleyDeadMassage(){
        getScene().getMessageBus().publish(RIPLEY_DIED,this);
        setAnimation(new Animation("sprites/player_die.png",32,32,0.1F,Animation.PlayMode.ONCE));

    }


}

