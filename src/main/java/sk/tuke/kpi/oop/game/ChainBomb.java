package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;


public class ChainBomb extends TimeBomb {


    public ChainBomb(float time) {
        super(time);
    }

    @Override
    public void explosion() {
            super.explosion();
            Ellipse2D.Float newEclipse = new Ellipse2D.Float();
            newEclipse.setFrame(
                this.getPosX() - 42,
                this.getPosY() - 42,
                50 * 2,
                50 * 2
            );
            for (Actor bomb : getScene().getActors()) {
                Rectangle2D.Float newRectangle = new Rectangle2D.Float(bomb.getPosX(), bomb.getPosY(), bomb.getHeight(), bomb.getWidth());
                if ((bomb instanceof ChainBomb) && !((ChainBomb) bomb).isActivated() && newEclipse.intersects(newRectangle) && bomb != this ) {
                    ((ChainBomb) bomb).activate();
                }
            }
        }
    }

