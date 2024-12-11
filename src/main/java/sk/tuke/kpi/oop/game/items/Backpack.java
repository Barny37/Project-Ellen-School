package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> content;

    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        content = new ArrayList<>();
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @NotNull
    @Override
    public List<Collectible> getContent() {
        return List.copyOf(content);
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return content.size();
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if (content.size() < capacity){
            content.add(actor);
        }else {
            throw new IllegalStateException(name +"is full");
        }
    }

    @Override
    public void remove(@NotNull Collectible actor) {
            content.remove(actor);

    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return content.iterator();
    }

    @Override
    public @Nullable Collectible peek() {
        if (content.isEmpty()){
            return null;
        }
        return content.get(getSize()-1);
    }

    @Override
    public void shift() {
        Collections.rotate(content,1);
    }
}



