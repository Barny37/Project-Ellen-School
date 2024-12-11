package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int changingAmmo, maxAmmo;
    public Firearm(int startAmmo,int maxAmmo){
        changingAmmo=startAmmo;
        this.maxAmmo=maxAmmo;
    }
    public Firearm(int startAmmo){
        changingAmmo= startAmmo;
        maxAmmo = startAmmo;
    }

    public int getAmmo() {
        return changingAmmo;
    }
    public void reload(int newAmmo){
        if (changingAmmo+newAmmo < maxAmmo){
            changingAmmo += newAmmo;
        }else {
            changingAmmo = maxAmmo;
        }
    }
    protected abstract Fireable createBullet();

    public Fireable fire(){
        if (changingAmmo > 0){
            changingAmmo--;
            return createBullet();
        }
        return null;
    }


}
