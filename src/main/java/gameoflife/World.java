package gameoflife;

import static java.util.Collections.unmodifiableSet;

import java.util.HashSet;
import java.util.Set;

public class World {

    private Set<Cell> livingCells = new HashSet<>();

    public void exportLivingCells(CellCollector collector) {
        collector.collect(unmodifiableSet(livingCells));
    }

    public void spawnAt(int x, int y) {
        livingCells.add(new Cell(x, y));
    }

    public void evolve() {
        HashSet<Cell> newLivingCells = new HashSet<>();
        evolve(cellSet -> newLivingCells.addAll(cellSet));
        livingCells = newLivingCells;
    }

    private void evolve(CellCollector cellCollector) {
        Set<Cell> newLivingCells = new HashSet<>();
        for (Cell livingCell : livingCells) {
            livingCell.exportNeighbours(cellCollector);
        }
        cellCollector.collect(newLivingCells);
    }

}
