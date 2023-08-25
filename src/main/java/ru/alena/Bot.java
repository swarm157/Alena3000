package ru.alena;

import java.util.ArrayList;
import java.util.Objects;

public class Bot {
    int energy;
    ArrayList<Code> gen;

    int position = 0;
    boolean alive;

    int rebornEnergy = World.bornEnergy;

    public Bot(int energy, ArrayList<Code> gen, boolean alive) {
        this.energy = energy;
        this.gen = gen;
        this.alive = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bot bot = (Bot) o;
        return energy == bot.energy && Objects.equals(gen, bot.gen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(energy, gen);
    }

    @Override
    public String toString() {
        return "Bot{" +
                "health=" + energy +
                ", gen=" + gen +
                '}';
    }
}
