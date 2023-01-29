package org.example;

class Counter implements Comparable<Counter> {
    private int usedRolesInOneType=0;
    int countOfRole=0;
    PlayerType type;

    public Counter(int countOfRole, PlayerType type) {
        this.countOfRole = countOfRole;
        this.type = type;
    }

    public int getUsedRolesInOneType() {
        return usedRolesInOneType;
    }

    @Override
    public int compareTo(Counter o) {
        return countOfRole - o.countOfRole;
    }
}