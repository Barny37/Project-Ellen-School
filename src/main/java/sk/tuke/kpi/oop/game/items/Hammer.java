package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Repairable;

public class Hammer extends BreakableTool<Repairable> implements Collectible{

    private Animation hammerAnimation;

    public Hammer(){
        super(1);
        this.hammerAnimation = new Animation("sprites/hammer.png");
        setAnimation(hammerAnimation);
    }
    public Hammer(int used){
        super(used);
        this.hammerAnimation = new Animation("sprites/hammer.png");
        setAnimation(hammerAnimation);
    }

   @Override
    public void useWith(Repairable repairable) {
        if (repairable !=null ) {
            super.useWith(repairable);
            repairable.repair();
        }
    }

    @Override
    public Class<Repairable> getUsingActorClass() {
        return Repairable.class;
    }
}
