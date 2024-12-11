package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{
    public Gun(int changingAmmo,int maxAmmo){
        super(changingAmmo,maxAmmo);
    }
    public Gun(int changingAmmo){
        super(changingAmmo);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
