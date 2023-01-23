package org.example;

public enum Role {

    Carry("Кэрри", 1),
    Mid("Мид", 2),
    HardLine("Хардлейн", 3),
    Support("Саппорт", 4),
    FullSupport("ФуллСапорт", 5);

    private String role;
    private int numberOfRole;

    Role(String role, int numberOfRole) {
        this.role = role;
        this.numberOfRole = numberOfRole;

    }

    public String getRole() {
        return role;
    }

    public int getNumberOfRole() {
        return numberOfRole;
    }
}
