package inf101v22.grid;

import java.util.Objects;

public final class CoordinateItem<E> {
    public final Coordinate coordinate;
    public final E item;

    public CoordinateItem(Coordinate coordinate, E item){
        this.coordinate = coordinate;
        this.item = item;
    }

    @Override
    public String toString() {
        return "{ " + "coordinate='{ row='" + coordinate.row +"', col='"+coordinate.col + "' }', item='" + item + "' }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CoordinateItem<?> that))
            return false;

        return Objects.equals(coordinate, that.coordinate) && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, item);
    }
}
