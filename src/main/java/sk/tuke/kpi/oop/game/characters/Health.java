package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int changingHealth, maxHealth;
    private List<ExhaustionEffect> effects;
    private boolean dead;
    public Health(int changingHealth, int maxHealth){
        this.changingHealth = changingHealth;
        this.maxHealth = maxHealth;
        effects = new ArrayList<>();
        dead = false;
    }

    public Health (int changingHealth){
        maxHealth = changingHealth;
        this.changingHealth = changingHealth;
    }

    public int getValue(){
        return changingHealth;
    }

    public void refill(int amount){
        if (changingHealth + amount > maxHealth) {
            changingHealth = maxHealth;

        }else {
            changingHealth += amount;
        }
    }

    public void restore(){
        changingHealth = maxHealth;
    }
    public void exhaust(){
        if (dead) return;
        dead = true;
        effects.forEach(ExhaustionEffect::apply);
        changingHealth = 0;
    }
    public void drain(int amount){
        if (amount == 0) return;

        if (changingHealth > 0){
            changingHealth -= amount;
            if (changingHealth <= 0){
                changingHealth = 0;
                effects.forEach(ExhaustionEffect::apply);
            }
        }
    }



    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }
    public void onExhaustion(ExhaustionEffect effect){
            effects.add(effect);
    }
}
