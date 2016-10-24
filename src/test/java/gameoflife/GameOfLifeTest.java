package gameoflife;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GameOfLifeTest {

    private CellCollector collector;

    @Before
    public void setUp() throws Exception {
        collector = mock(CellCollector.class);
    }

    @Test
    public void new_world_is_empty() {
        World world = new World();
        world.exportLivingCells(collector);

        verify(collector).collect(emptySet());
    }

    @Test
    public void world_with_living_cells_exports_these_cells() throws Exception {
        World world = new World();
        world.spawnAt(1, 1);

        world.exportLivingCells(collector);

        Set<Cell> expected = new HashSet<>(singletonList(new Cell(1, 1)));
        verify(collector).collect(eq(expected));
    }

    @Test
    public void empty_world_evolves_to_empty_world() throws Exception {
        World world = new World();

        world.evolve();

        world.exportLivingCells(collector);
        verify(collector).collect(emptySet());
    }

    @Test
    public void cell_with_no_living_neighbours_dies_on_evolve() throws Exception {
        World world = new World();
        world.spawnAt(1, 1);

        world.evolve();

        world.exportLivingCells(collector);
        verify(collector).collect(emptySet());
    }

    @Test
    public void three_living_cells_in_a_row_evolves_to_middle_cell() throws Exception {
        World world = new World();
        world.spawnAt(0, 1);
        world.spawnAt(1, 1);
        world.spawnAt(2, 1);

        world.evolve();

        world.exportLivingCells(collector);
        Set<Cell> expected = new HashSet<>(singletonList(new Cell(1, 1)));
        verify(collector).collect(eq(expected));
    }

}