package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TeamBuilder {
    static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args) {
//        addNewPlayer("Danirro", 110);
//        addNewPlayer("Ден4ик", 6500);
//        addNewPlayer("●", 2500);
//        addNewPlayer("Ayew", 4810);
//        addNewPlayer("Cenagfa", 2750);
//        addNewPlayer("SADSADSADSADSADSADSAD", 3520);
//        addNewPlayer("catinho", 2420);
//        addNewPlayer("Laugh_tale", 2000);
        addNewPlayer("Настя_ивлеева", 3030, false, false, false, false, false);
        addNewPlayer("Ден4ик", 4500, false, false, false, false, false);
        addNewPlayer("(Сергей){Sent!nel}", 1670, false, true, false, false, false);
        addNewPlayer("Ксюша.XXL(Андрей)", 1500, false, false, false, false, false);
        addNewPlayer("Медузник", 2150, false, false, false, false, false);
        addNewPlayer("DIABLO", 2240, false, false, false, false, false);
        addNewPlayer("Юрод", 1200, false, false, false, false, false);
        addNewPlayer("Аня.XXL", 990, false, false, false, false, false);
        addNewPlayer("wrotking", 1280, false, false, false, false, false);
        addNewPlayer("DIN-", 3190, false, false, false, false, false);

//        readPlayers(players);

//                for (int i = 0; i < 8; i++) {
//            addNewPlayer("asdasd12122121", 3301);
//        }

        List<Player> sortedCollection = sortPlayersByMmr(players);

        for (int i = 0; i < sortedCollection.size(); i++) {
            String a = "";
            if (i + 1 != 10) {
                a += " ";
            }
            System.out.println(a + (i + 1) + " ммр отсортированных " +
                    sortedCollection.get(i).getNick() +
                    " mmr: " + sortedCollection.get(i).getMmr());
        }
        generateTeamsByMmr(sortedCollection);


    }

    private static void addNewPlayer(String nick,int mmr,boolean roleFullSupport,boolean roleHardLaneSupport,boolean mid,boolean carry,boolean hardLane) {
        players.add(new Player(nick, mmr, roleFullSupport, roleHardLaneSupport, mid, carry, hardLane));
    }


//    private static void readPlayers(ArrayList<Player> players) {
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        int startPlayersSize = 0;
//        startPlayersSize = players.size();
//        for (int i = startPlayersSize; i < 10; i++) {
//            try {
//                System.out.println("Введите ник и через пробел ваш текущий ммр на мейне");
//                System.out.println(i + " Игрок");
//                String massage = bufferedReader.readLine();
//
//                String firstWord = readFirstWord(massage);
//                String secondWord = readSecondWord(massage);
//                String nick;
//                int mmr;
//                if (!isNumeric(firstWord)) {
//                    nick = firstWord;
//                    mmr = Integer.parseInt(secondWord);
//                } else {
//                    nick = secondWord;
//                    mmr = Integer.parseInt(firstWord);
//                }
//                players.add(new Player(nick, mmr, roleFullSupport, roleHardLaneSupport, mid, carry, hardLane));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private static boolean isNumeric(String firstWord) {
        try {
            Integer.parseInt(firstWord);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String readFirstWord(String massage) {
        int i = 0;
        String word = "";
        while (massage.charAt(i) != ' ') {
            word += massage.charAt(i);
            ++i;
        }
        return word;
    }

    private static String readSecondWord(String massage) {
        int i = 0;
        while (massage.charAt(i) != ' ') {
            ++i;
        }
        ++i;
        String word = "";
        for (int j = i; j < massage.length(); j++) {
            word += massage.charAt(j);
        }
        return word;
    }

    public static void generateTeamsByMmr(List<Player> players) {
        ArrayList<Player> radiant = new ArrayList<>();
        ArrayList<Player> dire = new ArrayList<>();


        Random random = new Random();
        boolean bestPlayerInRadiant = random.nextBoolean();

        int radiantScores = 0;
        int direScores = 0;

        if (bestPlayerInRadiant) {
            radiant.add(players.get(0));
            radiantScores = radiant.get(0).getMmr();

        } else {
            dire.add(players.get(0));
            direScores = dire.get(0).getMmr();
        }

        for (int i = 1; i < players.size(); i++) {
            Player testPlayer = players.get(i);

            int radiantPotential = testPlayer.getMmr() + radiantScores;
            int direPotential = testPlayer.getMmr() + direScores;
            if (radiant.size() != 5 && dire.size() != 5) {
                if (radiantPotential >= direPotential) {
                    dire.add(testPlayer);
                    direScores += testPlayer.getMmr();

                } else {
                    radiant.add(testPlayer);
                    radiantScores += testPlayer.getMmr();
                }
            } else {
                if (radiant.size() == 5) {
                    dire.add(testPlayer);
                    direScores += testPlayer.getMmr();
                } else {
                    radiant.add(testPlayer);
                    radiantScores += testPlayer.getMmr();
                }
            }
        }


        int summRad = 0;
        System.out.println("\n" + "За Свет: ");
        for (int i = 0; i < radiant.size(); i++) {
            System.out.println(i + 1 + " Свет " + radiant.get(i).getNick() + " " + radiant.get(i).getMmr());
            summRad += radiant.get(i).getMmr();
        }
        System.out.println("Суммарный ммр Света " + summRad + "\n");

        int summDire = 0;
        for (int i = 0; i < dire.size(); i++) {
            System.out.println(i + 1 + " тьма " + dire.get(i).getNick() + " " + dire.get(i).getMmr());
            summDire += dire.get(i).getMmr();
        }
        System.out.println("Суммарный ммр Тьмы " + summDire + "\n");

        System.out.println("Разница в суммарном ммр составляет :" + calcDifference(summRad, summDire));


    }

    private static int calcDifference(int sumRad, int sumDire) {
        int difference;
        if (sumRad >= sumDire) {
            difference = sumRad - sumDire;
        } else {
            difference = sumDire - sumRad;
        }
        return difference;
    }

    public static List<Player> sortPlayersByMmr(List<Player> players) {
        ArrayList<Player> sortedByMmrCollection = new ArrayList<>();
        final int maxPlayers = 10;

        for (int i = 0; i < maxPlayers; i++) {
            int idMaxMmrPlayer = 0;
            for (int j = 0; j < players.size(); j++) {
                if (players.get(j).getMmr() >= players.get(idMaxMmrPlayer).getMmr()) {
                    idMaxMmrPlayer = j;
                }
            }
            sortedByMmrCollection.add(players.get(idMaxMmrPlayer));
            players.remove(idMaxMmrPlayer);
        }

        return sortedByMmrCollection;
    }
}
