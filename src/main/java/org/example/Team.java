package org.example;

import local.exceptions.UnAvailableRoleException;

import static org.example.PlayerType.*;

public class Team {
    private Player carry;
    private Player mid;
    private Player hardLine;
    private Player support;
    private Player fullSupport;

    public Player getCarry() {
        return carry;
    }

    public void setCarry(Player carry) {
        this.carry = carry;
    }

    public Player getMid() {
        return mid;
    }

    public void setMid(Player mid) {
        this.mid = mid;
    }

    public Player getHardLine() {
        return hardLine;
    }

    public void setHardLine(Player hardLine) {
        this.hardLine = hardLine;
    }

    public Player getSupport() {
        return support;
    }

    public void setSupport(Player support) {
        this.support = support;
    }

    public Player getFullSupport() {
        return fullSupport;
    }

    public void setFullSupport(Player fullSupport) {
        this.fullSupport = fullSupport;
    }

    public void setPlayer(Player player, PlayerType type) {
//        try {
        if (type == CARRY) {
            if (getCarry() == null) {
                this.carry = player;
            } else {
                assert this.carry != null;
                System.out.println("Роль \"carry\" уже заполнена игроком " + this.carry.getNick());
            }
        }
        if (type == MID) {
            if (getMid() == null) {
                this.mid = player;
            } else {
                assert this.mid != null;
                System.out.println("Роль \"mid\" уже заполнена игроком " + this.mid.getNick());
            }
        }
        if (type == HARDLINE) {
            if (getHardLine() == null) {
                this.hardLine = player;
            } else {
                assert this.hardLine != null;
                System.out.println("Роль \"hardLane\" уже заполнена игроком " + this.hardLine.getNick());
            }
        }
        if (type == SUPPORT) {
            if (getSupport() == null) {
                this.support = player;
            } else {
                assert this.support != null;
                System.out.println("Роль \"support\" уже заполнена игроком " + this.support.getNick());
            }
        }
        if (type == FULLSUPPORT) {
            if (getFullSupport() == null) {
                this.fullSupport = player;
            } else {
                assert this.fullSupport != null;
                System.out.println("Роль \"fullSupport\" уже заполнена игроком " + this.fullSupport.getNick());
            }
        }
    }

    public boolean isFull() {
        if (carry == null) {
            return false;
        }
        if (mid == null) {
            return false;
        }
        if (hardLine == null) {
            return false;
        }
        if (support == null) {
            return false;
        }
        if (fullSupport == null) {
            return false;
        }
        return true;
    }

    public boolean isAlreadyHaveRole(PlayerType type) throws UnAvailableRoleException {
        if (type == CARRY) {
            return (carry != null);
        }
        if (type == MID) {
            return (mid != null);
        }
        if (type == HARDLINE) {
            return (hardLine != null);
        }
        if (type == SUPPORT) {
            return (support != null);
        }
        if (type == FULLSUPPORT) {
            return (fullSupport != null);
        }
        throw new UnAvailableRoleException("В команде не обнаруженно подобного варианта роли.");
    }

    public void printTeam() {
        System.out.println("1. carry: " + carry.getNick() + " " + carry.getMmr());
        System.out.println("2. mid: " + mid.getNick() + " " + mid.getMmr());
        System.out.println("3. hardLine: " + hardLine.getNick() + " " + hardLine.getMmr());
        System.out.println("4. support: " + support.getNick() + " " + support.getMmr());
        System.out.println("5. fullSupport: " + fullSupport.getNick() + " " + fullSupport.getMmr());
    }

    public int getSumOfMmr() {
        return carry.getMmr() + mid.getMmr() + hardLine.getMmr() + support.getMmr() + fullSupport.getMmr();
    }

}