class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        // implement me
        minutes = (minutes + 1) % 60;
        if (minutes == 0) {
            hours = hours % 12 + 1;
        }
    }
}