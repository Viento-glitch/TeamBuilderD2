package org.example;

import local.exceptions.UnAvailableRoleException;

public class Controller {
    private Team alpha;
    private Team beta;

    public Controller(Team alpha, Team beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    public boolean addPlayerToAlpha(Player player, PlayerType role) throws UnAvailableRoleException {
        if (!this.alpha.isFull()) {
            if (!this.alpha.isAlreadyHaveRole(role)) {
                this.alpha.setPlayer(player, role);
                this.alpha.alreadyHaveRole.add(role);
                System.out.println(player.getNick() + " зачислен в команду ALPHA под ролью \n" + role);
                System.out.println("Полный список его ролей:" + player.types.toString());
                return true;

            } else {
                System.out.println("Не удалось добавить в команду АЛЬФЫ потому что команда уже имеет данную роль");
            }
        } else {
            System.out.println("Не удалось добавить в команду АЛЬФЫ потому что команда полная");
        }
        return false;
    }

    public boolean addPlayerToBeta(Player player, PlayerType role) throws UnAvailableRoleException {
        if (!this.beta.isFull()) {
            if (!this.beta.isAlreadyHaveRole(role)) {
                this.beta.setPlayer(player, role);
                this.beta.alreadyHaveRole.add(role);

                System.out.println(player.getNick() + " зачислен в команду BETA под ролью \n" + role);
                System.out.println("Полный список его ролей:" + player.types.toString());
                return true;
            } else {
                System.out.println("Не удалось добавить в команду БЭТЫ потому что команда уже имеет данную роль");
            }
        } else {
            System.out.println("Не удалось добавить в команду БЭТЫ потому что команда полная");
        }
        return false;
    }

    public Team getAlpha() {
        return alpha;
    }

    public Team getBeta() {
        return beta;
    }
}
