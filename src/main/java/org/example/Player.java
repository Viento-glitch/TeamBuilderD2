package org.example;

import java.util.List;

public class Player implements Comparable<Player> {

    private boolean usedInTeam = false;
    private final String nick;
    private final int mmr;
    List<PlayerType> types;


    public Player(String nick, int mmr, List<PlayerType> types) {
        this.nick = nick;
        this.mmr = mmr;
        this.types = types;
    }

    public boolean isUsedInTeam() {
        return usedInTeam;
    }

    public void setUsedInTeam(boolean usedInTeam) {
        this.usedInTeam = usedInTeam;
    }

    public String getNick() {
        return nick;
    }

    public int getMmr() {
        return mmr;
    }

    @Override
    public int compareTo(Player o) {
        return getMmr() - o.getMmr();
    }
}