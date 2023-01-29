package org.example;

import local.exceptions.UnAvailableRoleException;

import java.util.*;

import static org.example.PlayerType.*;


public class TeamMaker {
    static List<Player> players;
    static Team alpha;
    static Team beta;

    public static void main(String[] args) throws UnAvailableRoleException {
        List<Player> players = new ArrayList();
//        players.add(new Player("(Денис)Kami", 6500, Arrays.asList(CARRY, MID)));
//        players.add(new Player("(Киприан)Fucking Animals", 2500, Arrays.asList(HARDLINE, SUPPORT, FULLSUPPORT, CARRY)));
//        players.add(new Player("(Максим)黒子 テツヤ", 3200, Arrays.asList(MID, CARRY)));

//        players.add(new Player("(Андрей)Ксюша.XXL", 1350, Arrays.asList(CARRY, MID, HARDLINE, SUPPORT)));
//        players.add(new Player("(Антон)91", 1520, Arrays.asList(MID, HARDLINE)));
//        players.add(new Player("(Амина)Ketsueki", 1600, Arrays.asList(SUPPORT, FULLSUPPORT)));
//        players.add(new Player("(Сергей){Sent!nel}", 1670, Arrays.asList(SUPPORT)));
//        players.add(new Player("(Андрей)DIABLO", 2000, Arrays.asList(CARRY, HARDLINE, SUPPORT, FULLSUPPORT)));
//        players.add(new Player("(Стас)HAPPY", 2200, Arrays.asList(SUPPORT, FULLSUPPORT)));
//        players.add(new Player("(Данила)1mpact", 2500, Arrays.asList(SUPPORT, FULLSUPPORT)));
//        players.add(new Player("(Айдар)   ", 3200, Arrays.asList(SUPPORT)));
//        players.add(new Player("(Саня)нота", 4000, Arrays.asList(SUPPORT, FULLSUPPORT)));
//        players.add(new Player("(Артур)KOT", 4000, Arrays.asList(MID, CARRY, SUPPORT)));

        //todo delete \/  fake
//        players.add(new Player("(Андрей)Ксюша.XXL", 1350, Arrays.asList(FULLSUPPORT)));
        players.add(new Player("(Андрей)Ксюша.XXL", 1350, Arrays.asList(FULLSUPPORT)));
        players.add(new Player("(Антон)91", 1520, Arrays.asList(FULLSUPPORT)));
        players.add(new Player("(Амина)Ketsueki", 1600, Arrays.asList(SUPPORT)));
        players.add(new Player("(Сергей){Sent!nel}", 1670, Arrays.asList(SUPPORT)));
        players.add(new Player("(Андрей)DIABLO", 2000, Arrays.asList(HARDLINE)));
        players.add(new Player("(Стас)HAPPY", 2200, Arrays.asList(HARDLINE)));
        players.add(new Player("(Данила)1mpact", 2500, Arrays.asList(MID)));
        players.add(new Player("(Айдар)   ", 3200, Arrays.asList(MID)));
        players.add(new Player("(Саня)нота", 4000, Arrays.asList(CARRY)));
        players.add(new Player("(Артур)KOT", 4000, Arrays.asList(CARRY)));


        //        System.out.println(players.size());

        int matrix[][] = new int[players.size()][5];
        for (int playersI = 0; playersI < players.size(); playersI++) {
            Player player = players.get(playersI);
            if (player.types.contains(CARRY)) matrix[playersI][0] = player.getMmr();
            if (player.types.contains(MID)) matrix[playersI][1] = player.getMmr();
            if (player.types.contains(HARDLINE)) matrix[playersI][2] = player.getMmr();
            if (player.types.contains(SUPPORT)) matrix[playersI][3] = player.getMmr();
            if (player.types.contains(FULLSUPPORT)) matrix[playersI][4] = player.getMmr();
        }


        List<Player> readyToDistributionListOfPlayers = new ArrayList<>();
        List<Player> listWithApology = new ArrayList<>();

        int roles;
        while (true) {
            for (roles = 0; roles < 5; roles++) {
                int countOfFinedPlayers = 0;
                for (int playersI = players.size() - 1; playersI >= 0; playersI--) {
                    if (matrix[playersI][roles] != 0) {
                        countOfFinedPlayers++;
                        players.get(playersI).setPlayerRole(roles);
                        readyToDistributionListOfPlayers.add(players.get(playersI));
                        players.remove(playersI);
                    }
                    if (countOfFinedPlayers == 2) {
                        break;
                    }
                }
            }
            if (readyToDistributionListOfPlayers.size() == 10) {
                for (int i = 0; i < players.size(); i++) {
                    listWithApology.add(players.get(i));
                }
                break;
            }
        }
        for (int i = 0; i < readyToDistributionListOfPlayers.size(); i++) {
            Collections.sort(readyToDistributionListOfPlayers);
            System.out.println(readyToDistributionListOfPlayers.get(i).getPlayerRole());
        }

        








        apology(listWithApology);


//        Purgatory purgatory = new Purgatory(players);
//        try {
//            purgatory.makeDistributionListForCommands();
//        } catch (UnAvailableRoleException e) {
//            throw new RuntimeException(e);
//        }


        //        players.add(new Player("()MACTEP ШИФУ", 2000, Arrays.asList(SUPPORT)));

        //        players.add(new Player("Solewa9", 3100, Arrays.asList(HARDLINE, SUPPORT, CARRY)));


//        players.add(new Player("Настя_ивлеевdа", 4000, Arrays.asList(HARDLINE, SUPPORT)));
//        players.add(new Player("Юрод", 2000, Arrays.asList(FULLSUPPORT, MID)));
//        players.add(new Player("Аня.XXL", 2000, Arrays.asList(FULLSUPPORT, MID, CARRY, HARDLINE)));
//        players.add(new Player("DIN-", 1000, Arrays.asList(HARDLINE)));
//        try {
//
//
//            if (!checkListOfPlayers(players)) throw new UnAvailableRoleException("");
//            makeTeams(players);
//        } catch (UnAvailableRoleException e) {
////            throw new RuntimeException(e);
//        }
    }

    private static void apology(List<Player> listWithApology) {
        if(!listWithApology.isEmpty()){
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("Приносим свои извинения игрокам");
            for (int i = 0; i < listWithApology.size(); i++) {
                System.out.println(listWithApology.get(i).getNick());
            }
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("              =(               ");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
    }

    private static boolean checkListOfPlayers(List<Player> players) {
        if (players.size() < 10) {
            System.out.println("Для игры 5 на 5 недостаточно игроков");
            return false;
        }

        Collections.sort(players);
        long carry = players.stream().filter(player -> player.types.contains(CARRY)).count();
        long mid = players.stream().filter(player -> player.types.contains(MID)).count();
        long hardLine = players.stream().filter(player -> player.types.contains(HARDLINE)).count();
        long support = players.stream().filter(player -> player.types.contains(SUPPORT)).count();
        long fullSupport = players.stream().filter(player -> player.types.contains(FULLSUPPORT)).count();
        if (carry < 2) {
            System.out.print("Недостаточно человек для роли: " + CARRY);
            return false;
        }
        if (mid < 2) {
            System.out.print("Недостаточно человек для роли: " + MID);
            return false;
        }
        if (hardLine < 2) {
            System.out.print("Недостаточно человек для роли: " + HARDLINE);
            return false;
        }
        if (support < 2) {
            System.out.print("Недостаточно человек для роли: " + SUPPORT);
            return false;
        }
        if (fullSupport < 2) {
            System.out.print("Недостаточно человек для роли: " + FULLSUPPORT);
            return false;
        }
        return true;
    }

    public static void makeTeams(List<Player> players) throws UnAvailableRoleException {
        Collections.sort(players);
        TeamMaker.players = players;


        long carry = players.stream().filter(player -> player.types.contains(CARRY)).count();
        long mid = players.stream().filter(player -> player.types.contains(MID)).count();
        long hardLine = players.stream().filter(player -> player.types.contains(HARDLINE)).count();
        long support = players.stream().filter(player -> player.types.contains(SUPPORT)).count();
        long fullSupport = players.stream().filter(player -> player.types.contains(FULLSUPPORT)).count();
        if (carry < 2) System.out.print("Недостаточно человек для роли: " + CARRY);
        if (mid < 2) System.out.print("Недостаточно человек для роли: " + MID);
        if (hardLine < 2) System.out.print("Недостаточно человек для роли: " + HARDLINE);
        if (support < 2) System.out.print("Недостаточно человек для роли: " + SUPPORT);
        if (fullSupport < 2) System.out.print("Недостаточно человек для роли: " + FULLSUPPORT);

        List<Counter> counters = new ArrayList<>();
        counters.add(new Counter((int) carry, CARRY));
        counters.add(new Counter((int) mid, MID));
        counters.add(new Counter((int) hardLine, HARDLINE));
        counters.add(new Counter((int) support, SUPPORT));
        counters.add(new Counter((int) fullSupport, FULLSUPPORT));

        Team alpha = new Team();
        Team beta = new Team();

        Controller teamController = new Controller(alpha, beta);
        int k = 0;
        while (true) {
            Collections.sort(counters);
            Counter counter = counters.get(k);
            for (int j = 0; j < 2; j++) {
                if (alpha.alreadyHaveRole.contains(counter.type) && beta.alreadyHaveRole.contains(counter.type)) {
                    break;
                }
                for (int i = TeamMaker.players.size() - 1; i >= 0; i--) {
                    Player player = TeamMaker.players.get(i);


                    if (!player.isUsedInTeam() && player.types.contains(counter.type)) {
                        if (teamController.getBeta().calcPotentialIfAddPlayerToTeam(player) - teamController.getAlpha().calcPotentialIfAddPlayerToTeam(player) >= 0) {
                            if (teamController.addPlayerToAlpha(player, counter.type)) {
                                player.setUsedInTeam(true);
                                TeamMaker.players.remove(player);
                            } else if (alpha.alreadyHaveRole.contains(counter.type)) {
                                if (!beta.alreadyHaveRole.contains(counter.type)) {
                                    teamController.addPlayerToBeta(player, counter.type);
                                    TeamMaker.players.remove(player);
                                    player.setUsedInTeam(true);
                                    break;
                                }
                            }
                        } else {
                            if (teamController.addPlayerToBeta(player, counter.type)) {
                                player.setUsedInTeam(true);
                                TeamMaker.players.remove(player);
                            } else if (beta.alreadyHaveRole.contains(counter.type)) {
                                if (!alpha.alreadyHaveRole.contains(counter.type)) {
                                    teamController.addPlayerToAlpha(player, counter.type);
                                    TeamMaker.players.remove(player);
                                    player.setUsedInTeam(true);
                                    break;
                                }
                            }
                        }
//                        System.out.println(player.isUsedInTeam());
                        break;
                    }

                    if (!player.isUsedInTeam() && player.types.contains(counter.type)) {
                        System.out.println("!!!!");
                        System.out.println(player.types);
                        System.out.println(counter.type);

                    }
                    if (player.types.contains(counter.type)) {
                        System.out.println("$$$$$");
                    }

                }
                for (int i = 0; i < TeamMaker.players.size(); i++) {
                    if (TeamMaker.players.get(i).isUsedInTeam()) TeamMaker.players.remove(i);
                }
                if (TeamMaker.players.isEmpty()) {
                    break;
                }
                Collections.sort(TeamMaker.players);
//                System.out.println();
//                System.out.println("Осталось не распределённых игроков : " + (TeamMaker.players.get(0).getNick()));
//                System.out.println();
            }
            if (beta.isFull() && alpha.isFull() || TeamMaker.players.isEmpty()) break;
            if (k == 4) {
                k = 0;
            } else k++;
            counters = getCounters(TeamMaker.players);

//            System.out.println(counter.type);
//            System.out.println(players.get(0).types);
        }
        System.out.println("\n\n\n");
        if (!TeamMaker.players.isEmpty()) {
            System.out.println("Не распределены все игроки");
        }
        if (!TeamMaker.players.isEmpty()) {
            System.out.println(TeamMaker.players.get(0).getNick());
        }
        if (!TeamMaker.players.isEmpty()) {
            System.out.println(TeamMaker.players.get(0).types);
        }
        System.out.println();

        if (alpha.isFull()) {
            System.out.println("Команда ALPHA полностью укомплектованна ");
        } else {
            System.out.println("Команда ALPHA НЕ укомплектованна ");
        }
        if (beta.isFull()) {
            System.out.println("Команда BETA полностью укомплектованна ");
        } else {
            System.out.println("Команда BETA НЕ укомплектованна ");
        }


        {
            alpha.printTeam();
            System.out.println("Суммарный ммр команды ALPHA " + alpha.getSumOfMMR());
            System.out.println();
            beta.printTeam();
            System.out.println("Суммарный ммр команды BETA " + beta.getSumOfMMR());
            System.out.println();
            System.out.println("Разница в ммр между командами " + getModuleOfSum(alpha.getSumOfMMR(), beta.getSumOfMMR()));
            System.out.println();
        }
    }

    private static List<Counter> getCounters(List<Player> gamers) {
        long carry = gamers.stream().filter(gamer -> gamer.types.contains(CARRY)).count();
        long mid = gamers.stream().filter(gamer -> gamer.types.contains(MID)).count();
        long hardLine = gamers.stream().filter(gamer -> gamer.types.contains(HARDLINE)).count();
        long support = gamers.stream().filter(gamer -> gamer.types.contains(SUPPORT)).count();
        long fullSupport = gamers.stream().filter(gamer -> gamer.types.contains(FULLSUPPORT)).count();
//        if (carry < 2) System.out.print("Недостаточно человек для роли: " + CARRY);
//        if (mid < 2) System.out.print("Недостаточно человек для роли: " + MID);
//        if (hardLine < 2) System.out.print("Недостаточно человек для роли: " + HARDLINE);
//        if (support < 2) System.out.print("Недостаточно человек для роли: " + SUPPORT);
//        if (fullSupport < 2) System.out.print("Недостаточно человек для роли: " + FULLSUPPORT);

        List<Counter> counters = new ArrayList<>();
        counters.add(new Counter((int) carry, CARRY));
        counters.add(new Counter((int) mid, MID));
        counters.add(new Counter((int) hardLine, HARDLINE));
        counters.add(new Counter((int) support, SUPPORT));
        counters.add(new Counter((int) fullSupport, FULLSUPPORT));
        Collections.sort(counters);
        return counters;
    }

    public static int getModuleOfSum(int a, int b) {
        if ((a - b) > 0) return a - b;
        else return b - a;
    }

}