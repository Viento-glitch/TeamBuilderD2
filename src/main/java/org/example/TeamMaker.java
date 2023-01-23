package org.example;

import local.exceptions.UnAvailableRoleException;

import java.lang.annotation.Target;
import java.util.*;

import static org.example.PlayerType.*;

import local.exceptions.UnAvailableRoleException;

import java.lang.annotation.Target;
import java.util.*;


public class TeamMaker {
    static List<Player> players;
    static Team radiant;
    static Team dire;

    public static void main(String[] args) {
        List<Player> players = new ArrayList();
        players.add(new Player("Настя_ивлеева", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE, SUPPORT)));
        players.add(new Player("Ден4ик", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE, SUPPORT)));
        players.add(new Player("(Сергей){Sent!nel}", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("Ксюша.XXL(Андрей)", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("Медузник", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("DIABLO", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("Юрод", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("Аня.XXL", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("wrotking", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
        players.add(new Player("DIN-", 1000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
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

        long carry = gamers.stream().filter(gamer -> gamer.types.contains(CARRY)).count();
        if (carry < 2) System.out.print("Недостаточно человек для роли: " + CARRY);
        long mid = gamers.stream().filter(gamer -> gamer.types.contains(MID)).count();
        if (mid < 2) System.out.print("Недостаточно человек для роли: " + MID);
        long hardLine = gamers.stream().filter(gamer -> gamer.types.contains(HARDLINE)).count();
        if (hardLine < 2) System.out.print("Недостаточно человек для роли: " + HARDLINE);
        long support = gamers.stream().filter(gamer -> gamer.types.contains(SUPPORT)).count();
        if (support < 2) System.out.print("Недостаточно человек для роли: " + SUPPORT);
        long fullSupport = gamers.stream().filter(gamer -> gamer.types.contains(FULLSUPPORT)).count();
        if (fullSupport < 2) System.out.print("Недостаточно человек для роли: " + FULLSUPPORT);

        List<Counter> counters = new ArrayList<>();
        counters.add(new Counter((int) carry, CARRY));
        counters.add(new Counter((int) mid, MID));
        counters.add(new Counter((int) hardLine, HARDLINE));
        counters.add(new Counter((int) support, SUPPORT));
        counters.add(new Counter((int) fullSupport, FULLSUPPORT));
        Collections.sort(counters);

        Team radiant = new Team();
        Team dire = new Team();

        makeTeams(counters, radiant, dire);

//        System.out.println(radiantSum);
//        System.out.println(direSum);

//        radiant.printTeam();
//        dire.printTeam();
    }

    private static void makeTeams(List<Counter> counters, Team radiant, Team dire) throws UnAvailableRoleException {
        /*
         *Находим наименее распространённую роль
         *Создаём две суммы команд из которых будем считать
         */
        Random random = new Random();
        boolean bestPlayerInRadiant = random.nextBoolean();

        int radiantSum = 0;
        int direSum = 0;
        for (int i = 0; i < counters.size(); i++) {
            Counter counter = counters.get(i);
            /*
             * проходимся 2 раза для распределения сразу двух игроков по этой роли в две команды
             */
            for (int j = 0; j < 2; j++) {
                /*
                 *проход по списку игроков дабы найти игрока соответствующего нашим требованиям
                 * Проход в обратном направлении дабы найти наиболее высоко рейтингового игрока с этой ролью
                 */

                for (int k = players.size() - 1; k > 0; k--) {
                    Player playerWithMinimalRoles = players.get(i);
                    //                    Player playerWithMinimalRoles = new Player("", 0, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE, SUPPORT));
//                    for (int l = players.size() - 1; l > 0; l--) {
//                        if (playerWithMinimalRoles.types.size() > players.get(l).types.size()) {
//                            if (!players.get(l).isUsedInTeam()) {
//                                playerWithMinimalRoles = players.get(l);
//                            }
//                        }
//                    }


                    if (!playerWithMinimalRoles.isUsedInTeam()) {
                        if (playerWithMinimalRoles.types.contains(counter.type)) {

                            int radiantPotential = playerWithMinimalRoles.getMmr() + radiantSum;
                            int direPotential = playerWithMinimalRoles.getMmr() + direSum;
                            if (!radiant.isFull() && !dire.isFull()) {
                                //если рэдиэнт и даер не полные
                                System.out.println("Команды не полные");
                                if (radiantPotential <= direPotential) {
                                    //если потенциал добавления в команду radiant выше чем в даер
                                    System.out.println("потенциал добавления в команду radiant выше чем в даер " + radiantPotential + " " + direPotential);
                                    if (!radiant.isAlreadyHaveRole(counter.type)) {
                                        //если у radiant нету человека с ролью
                                        System.out.println("у radiant нету человека с ролью");
                                        radiant.setPlayer(playerWithMinimalRoles, counter.type);
                                        counter.incrementOfCountUsedRoles();
                                        System.out.println(playerWithMinimalRoles.getNick() + " зачислен в команду света под ролью " + counter.type);
                                        System.out.println("Полный список его ролей:" + playerWithMinimalRoles.types.toString());

                                        playerWithMinimalRoles.setUsedInTeam(true);
                                        radiantSum += playerWithMinimalRoles.getMmr();
                                        System.out.println();
                                        continue;
                                    } else {
                                        System.out.println("у radiant есть человек с данной ролью " + counter.type);
                                        if (!dire.isAlreadyHaveRole(counter.type)) {
                                            System.out.println("У команды света уже есть человек этой роли");
                                            dire.setPlayer(playerWithMinimalRoles, counter.type);
                                            counter.incrementOfCountUsedRoles();
                                            System.out.println(playerWithMinimalRoles.getNick() + " зачислен в команду тьмы под ролью " + counter.type);
                                            System.out.println("Полный список его ролей:" + playerWithMinimalRoles.types.toString());

                                            playerWithMinimalRoles.setUsedInTeam(true);
                                            direSum += playerWithMinimalRoles.getMmr();
                                        } else {
                                            System.out.println("у тьмы тоже есть человек с данной ролью " + counter.type);
                                            /*костыльное решение, найти причину и место
                                             *Должно при сохранении двух человек с ролью переходить к следующей роли
                                             */
//                                            if (i != 4) {

//                                                counter = counters.get(i = i + 1);
//                                            System.out.println("Производим скип роли");
//                                            System.out.println();
//                                            break;
//                                            }
                                        }
                                    }
                                } else {
                                    //если потенциал добавления в команду тьмы выше чем у radiant
                                    System.out.println("потенциал добавления в команду тьмы выше чем в radiant " + radiantPotential + " " + direPotential);
                                    if (!dire.isAlreadyHaveRole(counter.type)) {
                                        //если у тьмы нет человека с ролью
                                        System.out.println("у тьмы нету человека с ролью");
                                        dire.setPlayer(playerWithMinimalRoles, counter.type);
                                        counter.incrementOfCountUsedRoles();
                                        System.out.println(playerWithMinimalRoles.getNick() + " зачислен в команду тьмы под ролью " + counter.type);
                                        System.out.println("Полный список его ролей:" + playerWithMinimalRoles.types.toString());

                                        playerWithMinimalRoles.setUsedInTeam(true);
                                        direSum += playerWithMinimalRoles.getMmr();
                                    } else if (!radiant.isAlreadyHaveRole(counter.type)) {
                                        //если у radiant нету человека с ролью
                                        radiant.setPlayer(playerWithMinimalRoles, counter.type);
                                        counter.incrementOfCountUsedRoles();
                                        playerWithMinimalRoles.setUsedInTeam(true);
                                        radiantSum += playerWithMinimalRoles.getMmr();
                                    }

                                }
                            } else {
                                if (!dire.isFull()) {
                                    dire.setPlayer(playerWithMinimalRoles, counter.type);
                                    counter.incrementOfCountUsedRoles();
                                    playerWithMinimalRoles.setUsedInTeam(true);
                                    direSum += playerWithMinimalRoles.getMmr();
                                } else if (!radiant.isFull()) {
                                    radiant.setPlayer(playerWithMinimalRoles, counter.type);
                                    counter.incrementOfCountUsedRoles();
                                    playerWithMinimalRoles.setUsedInTeam(true);
                                    radiantSum += playerWithMinimalRoles.getMmr();
                                }

                            }
                        }
                    }
                }

//                if (counter.countOfRole > 1) {
//                    break;
//                }
            }
        }
    }
}