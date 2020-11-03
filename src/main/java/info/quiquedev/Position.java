package info.quiquedev;

record Position(int x, int y, Direction direction) {
    @Override
    public String toString() {
        return "(%d, %d) %s".formatted(x, y, direction);
    }

    enum Direction {
        NORTH, SOUTH, EAST, WEST
    }
}
