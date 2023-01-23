package org.example;

import local.exceptions.UnAvailableRoleException;

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

    public void setPlayer(Player gamer, String role) {
        Player player = new Player(gamer.getNick(),
                gamer.getMmr(),
                gamer.isFullSupport(),
                gamer.isSupport(),
                gamer.isMid(),
                gamer.isCarry(),
                gamer.isHardLine());
        try {

            switch (role) {
                case ("Carry"): {
                    if (this.carry == null) {
                        this.carry = player;
                        break;
                    } else {
                        assert this.carry != null;
                        System.out.println("Роль \"carry\" уже заполнена игроком" + this.carry.getNick());
                    }
                }
                case ("Mid"): {
                    if (this.mid == null) {
                        this.mid = player;
                        break;
                    } else {
                        assert this.mid != null;
                        System.out.println("Роль \"mid\" уже заполнена игроком" + this.mid.getNick());
                    }
                }
                case ("HardLine"): {
                    if (this.hardLine == null) {
                        this.hardLine = player;
                        break;
                    } else {
                        assert this.hardLine != null;
                        System.out.println("Роль \"hardLane\" уже заполнена игроком" + this.hardLine.getNick());
                    }
                }
                case ("Support"): {
                    if (this.support == null) {
                        this.support = player;
                        break;
                    } else {
                        assert this.support != null;
                        System.out.println("Роль \"support\" уже заполнена игроком" + this.support.getNick());
                    }
                }
                case ("FullSupport"): {
                    if (getFullSupport() == null) {
                        this.fullSupport = player;
                        break;
                    } else {
                        assert this.fullSupport != null;
                        System.out.println("Роль \"fullSupport\" уже заполнена игроком" + this.fullSupport.getNick());
                    }
                }
                default: {
                    throw new UnAvailableRoleException("Выбраная роль не существует! "+role );
                }
            }
        } catch (UnAvailableRoleException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAlreadyHaveRole(String role) throws UnAvailableRoleException {
        switch (role) {
            case ("Carry"): {
                return !(carry == null);
            }
            case ("Mid"): {
                return !(mid == null);
            }
            case ("HardLine"): {
                return !(hardLine == null);
            }
            case ("Support"): {
                return !(support == null);
            }
            case ("FullSupport"): {
                return !(fullSupport == null);
            }

        }
        throw new UnAvailableRoleException("В команде не обнаруженно подобного варианта роли.");
    }
}