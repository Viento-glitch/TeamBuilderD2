package org.example;

import local.exceptions.UnAvailableRoleException;

import java.lang.annotation.Target;
import java.util.*;

public class TeamMaker {
    static List<Player> players;
    static Team radiant;
    static Team dire;

    public static void main(String[] args) {
        List<Player> players = new ArrayList();
        players.add(new Player("Настя_ивлеева", 3030, false, true, false, false, false));
        players.add(new Player("Ден4ик", 4500, false, false, true, true, false));
        players.add(new Player("(Сергей){Sent!nel}", 1670, false, true, false, false, false));
        players.add(new Player("Ксюша.XXL(Андрей)", 1500, false, true, false, true, false));
        players.add(new Player("Медузник", 2150, false, false, true, false, false));
        players.add(new Player("DIABLO", 2240, false, false, true, false, false));
        players.add(new Player("Юрод", 1200, false, false, true, false, true));
        players.add(new Player("Аня.XXL", 990, false, false, true, false, true));
        players.add(new Player("wrotking", 1280, true, false, true, false, false));
        players.add(new Player("DIN-", 3190, true, false, true, true, true));
        try {
            makeTeams(players);
        } catch (UnAvailableRoleException e) {
            throw new RuntimeException(e);
        }
    }


    public static void makeTeams(List<Player> gamers) throws UnAvailableRoleException {
        Collections.sort(gamers);
        players = gamers;
        if (players.size() < 10) {
            System.out.println("Для игры 5 на 5 недостаточно игроков");

        }

        int Carry = 0;
        int Mid = 0;
        int HardLine = 0;
        int Support = 0;
        int FullSupport = 0;


        for (int i = 0; i < 10; i++) {
            Player player = players.get(i);

            if (player.isCarry()) {
                Carry = Carry + 1;
            }
            if (player.isMid()) {
                Mid = Mid + 1;
            }
            if (player.isHardLine()) {
                HardLine = HardLine + 1;
            }
            if (player.isSupport()) {
                Support = Support + 1;
            }
            if (player.isFullSupport()) {
                FullSupport = FullSupport + 1;
            }
        }

        Counter CounterCarry = new Counter("Carry", Carry);
        Counter CounterMid = new Counter("Mid", Mid);
        Counter CounterHardLine = new Counter("HardLine", HardLine);
        Counter CounterSupport = new Counter("Support", Support);
        Counter CounterFullSupport = new Counter("FullSupport", FullSupport);
        List<Counter> counters = new ArrayList<>();
        counters.add(CounterCarry);
        counters.add(CounterMid);
        counters.add(CounterHardLine);
        counters.add(CounterSupport);
        counters.add(CounterFullSupport);
        Collections.sort(counters);

        boolean isAllRolesReady = isAllRolesEnough(counters);
        if (isAllRolesReady) {
            Team radiant = new Team();
            Team dire = new Team();

            Random random = new Random();
            boolean isfirstRadiant = random.nextBoolean();
            for (int j = 0; j < counters.size(); j++) {
                for (int i = players.size() - 1; i >= 0; i--) {
                    Counter counter = counters.get(j);
                    if (radiant.isAlreadyHaveRole(counter.role) && dire.isAlreadyHaveRole(counter.role)) {
                        continue;
                    }

                    Player player = players.get(i);
                    System.out.println(player.getNick());
                    if (player.checkRole(counter.role)) {
                        if (isfirstRadiant) {
                            radiant.setPlayer(player, counter.role);
                        } else {
                            dire.setPlayer(player, counter.role);
                        }
                    }
                    players.remove(i);


//                    if (isfirstRadiant) {
//                        radiant.setPlayer(players.get(i));
//                    } else {
//                        dire.setPlayer(players.get(i));
//                    }

                    if (counter.countOfRole == 0) continue;
                    counter.countOfRole--;
                }

            }
            if (isfirstRadiant) {

            } else {

            }

            //todo проверить рабостоспособность сортировки игроков по ммр


        }


    }

    private static boolean isAllRolesEnough(List<Counter> roles) {
        boolean result = true;
        for (int i = 0; i < roles.size(); i++) {
            int countOfRole = roles.get(i).countOfRole;
            if (countOfRole < 2) {
                if (result) {
                    System.out.print("Недостаточно человек для роли:");
                    result = false;
                } else {
                    System.out.print(",");
                }
            }
            switch (i + 1) {
                case (1): {
                    if (!result) {

                        System.out.print(" \"Carry\"");
                        break;
                    }
                }
                case (2): {
                    if (!result) {
                        System.out.print(" \"Mid\"");
                        break;
                    }
                }
                case (3): {
                    if (!result) {

                        System.out.print(" \"HardLine\"");
                        break;
                    }
                }
                case (4): {
                    if (!result) {
                        System.out.print(" \"Support\"");
                        break;
                    }
                }
                case (5): {
                    if (!result) {
                        System.out.print(" \"FullSupport\"");
                        break;
                    }
                }
            }
        }
        return result;
    }


}

class Counter implements Comparable<Counter> {
    String role;
    int countOfRole;

    public Counter(String role, int countOfRole) {
        this.role = role;
        this.countOfRole = countOfRole;
    }

    @Override
    public int compareTo(Counter o) {
        return countOfRole - o.countOfRole;
    }
}