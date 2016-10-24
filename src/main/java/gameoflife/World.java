package gameoflife;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static gameoflife.Location.createLocationAt;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.rangeClosed;

public class World {

    public static World createEmpty() {
        return new World();
    }

    public static World create(Location... locations) {
        return new World(locations);
    }

    private Set<Location> livingCellLocations;

    public World(Location... locations) {
        this(new HashSet<>(Arrays.asList(locations)));
    }

    public World(Set<Location> livingCellLocations) {
        this.livingCellLocations = livingCellLocations;
    }

    public boolean containsLivingCells() {
        return !livingCellLocations.isEmpty();
    }

    public boolean cellLivesAt(Location refLocation) {
        return livingCellLocations.contains(refLocation);
    }

    public Set<Location> getNeighbourLocations(Location location) {
        return rangeClosed(-1, 1).mapToObj(dx ->
                rangeClosed(-1, 1).mapToObj(dy ->
                    createLocationAt(location.getX() + dx, location.getY() + dy))
                ).flatMap(identity()).filter(neighbour ->
                    !location.equals(neighbour)
            ).collect(toSet());
    }

    public World evolve() {
        Set<Location> survivingCells = new HashSet<>();
        for (Location livingCellLocation : livingCellLocations) {
            Set<Location> neighbourLocations = getNeighbourLocations(livingCellLocation);
            neighbourLocations.retainAll(livingCellLocations);
            if (neighbourLocations.size() == 2) {
                survivingCells.add(livingCellLocation);
            }
        }
        return new World(survivingCells);
    }

}
