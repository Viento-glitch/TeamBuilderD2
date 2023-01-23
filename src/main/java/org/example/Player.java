package org.example;

import local.exceptions.UnAvailableRoleException;

public class Player implements Comparable<Player> {

    private final String nick;
    private final int mmr;
    private final boolean fullSupport;
    private final boolean support;
    private final boolean mid;
    private final boolean carry;
    private final boolean hardLine;

    public Player(
            String nick,
            int mmr,
            boolean fullSupport,
            boolean support,
            boolean mid,
            boolean carry,
            boolean hardLane) {

        this.nick = nick;
        this.mmr = mmr;
        this.fullSupport = fullSupport;
        this.support = support;
        this.mid = mid;
        this.carry = carry;
        this.hardLine = hardLane;
    }

    public String getNick() {
        return nick;
    }

    public int getMmr() {
        return mmr;
    }

    public boolean isFullSupport() {
        return fullSupport;
    }

    public boolean isSupport() {
        return support;
    }

    public boolean isMid() {
        return mid;
    }

    public boolean isCarry() {
        return carry;
    }

    public boolean isHardLine() {
        return hardLine;
    }

    @Override
    public int compareTo(Player o) {
        return getMmr() - o.getMmr();
    }

    public boolean checkRole(String role) throws UnAvailableRoleException {
        switch (role) {
            case ("Carry"): {
                return carry;
            }
            case ("Mid"): {
                return mid;
            }
            case ("HardLine"): {
                return hardLine;
            }
            case ("Support"): {
                return support;
            }
            case ("FullSupport"): {
                return fullSupport;
            }
        }
        throw new UnAvailableRoleException("Данной роли не обнаружено!");
    }
}