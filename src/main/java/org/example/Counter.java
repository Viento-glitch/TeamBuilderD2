package org.example;

class Counter implements Comparable<Counter> {
    private int usedRolesInOneType=0;
    int countOfRole;
    PlayerType type;

    public Counter(int countOfRole, PlayerType type) {
        this.countOfRole = countOfRole;
        this.type = type;
    }
    public void incrementOfCountUsedRoles(){
        this.countOfRole++;
    }

    public int getUsedRolesInOneType() {
        return usedRolesInOneType;
    }

    @Override
    public int compareTo(Counter o) {
        return countOfRole - o.countOfRole;
    }
}