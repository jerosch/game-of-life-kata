package gameoflife;

public class Location {

    public static Location createLocationAt(int x, int y) {
        return new Location(x, y);
    }

    public static Location anyLocation() {
        return null;
    }

    private final int x;

    private final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Location location = (Location) o;

        if (x != location.x) {
            return false;
        }
        return y == location.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
