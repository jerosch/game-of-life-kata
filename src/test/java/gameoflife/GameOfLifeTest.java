package gameoflife;

import org.junit.Test;

import static gameoflife.Location.anyLocation;
import static gameoflife.Location.createLocationAt;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GameOfLifeTest {

    @Test
    public void empty_world_contains_no_living_cells() {
        World world = World.createEmpty();
        assertThat(world.containsLivingCells(), is(false));
    }

    @Test
    public void populated_world_contains_living_cells() throws Exception {
        World world = World.create(anyLocation());
        assertThat(world.containsLivingCells(), is(true));
    }

    @Test
    public void cell_at_initialized_location_is_alive() throws Exception {
        World world = World.create(createLocationAt(0, 0));
        assertThat(world.cellLivesAt(createLocationAt(0, 0)), is(true));
    }

    @Test
    public void cells_in_adjacent_locations_are_neighbours() throws Exception {
        World world = World.create(createLocationAt(0, 0));
        assertThat(world.getNeighbourLocations(createLocationAt(0, 0)).size(), is(equalTo(8)));
        assertThat(world.getNeighbourLocations(createLocationAt(0, 0)), hasItems(
                createLocationAt(-1,  1), createLocationAt(0,  1), createLocationAt(1,  1),
                createLocationAt(-1,  0),                          createLocationAt(1,  0),
                createLocationAt(-1, -1), createLocationAt(0, -1), createLocationAt(1, -1)
        ));
    }

    @Test
    public void living_cell_without_living_neighbours_dies_after_evolve() throws Exception {
        World world = World.create(createLocationAt(0, 0));
        World newWorld = world.evolve();
        assertThat(newWorld.containsLivingCells(), is(false));
    }

    @Test
    public void living_cell_with_one_living_neighbour_dies_after_evolve() throws Exception {
        World world = World.create(createLocationAt(0, 0), createLocationAt(1, 0));
        World newWorld = world.evolve();
        assertThat(newWorld.containsLivingCells(), is(false));
    }

    @Test
    public void living_cell_with_two_living_neighbours_survives_after_evolve() throws Exception {
        World world = World.create(createLocationAt(-1, 0), createLocationAt(0, 0), createLocationAt(1, 0));
        World newWorld = world.evolve();
        assertThat(newWorld.cellLivesAt(createLocationAt(0, 0)), is(true));
    }

}
