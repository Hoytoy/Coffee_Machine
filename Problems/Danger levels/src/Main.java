enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int numDanger;

    DangerLevel(int numDanger) {
        this.numDanger = numDanger;
    }

    public int getLevel() {
        return numDanger;
    }
}