package org.example;

import local.exceptions.UnAvailableRoleException;

import java.util.List;

import static org.example.PlayerType.*;

public class Player implements Comparable<Player> {

    private boolean usedInTeam = false;
    private PlayerType playerRole;
    private final String nick;
    private final int mmr;
    List<PlayerType> types;

    public PlayerType getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(PlayerType playerRole) {
        this.playerRole = playerRole;
    }

    public void setPlayerRole(int numOfRole) throws UnAvailableRoleException {
        switch (numOfRole) {
            case (0): {
                this.playerRole = CARRY;
                break;
            }
            case (1): {
                this.playerRole = MID;
                break;
            }
            case (2): {
                this.playerRole = HARDLINE;
                break;
            }
            case (3): {
                this.playerRole = SUPPORT;
                break;
            }
            case (4): {
                this.playerRole = FULLSUPPORT;
                break;
            }
            default:
                throw new UnAvailableRoleException("Не верное значение для переданной роли");
        }


    }

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