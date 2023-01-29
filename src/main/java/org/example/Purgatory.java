package org.example;

import local.exceptions.UnAvailableRoleException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.example.PlayerType.*;

public class Purgatory {

    List<Player> initialListOfPlayers;
    List<Player> distributionListForCommands = new ArrayList<>();
    List<Counter> listOfRoles = new ArrayList<>();
    List<Counter> listOfUniqueRoles = new ArrayList<>();

    /*
    допустим есть 5 игроков
    4 из них имеют 2 роли FullSupport и Support
    1 из них имеет лишь одну роль Support
    и он является наименьшим рейтингом
    значит им можно пожертвовать дабы
    уменьшить количество игроков требующих распределение
    действует при количестве игроков >10




    */








    public Purgatory(List<Player> initialListOfPlayers) {
        this.initialListOfPlayers = initialListOfPlayers;
        long carry = this.initialListOfPlayers.stream().filter(player -> player.types.contains(CARRY)).count();
        long mid = this.initialListOfPlayers.stream().filter(player -> player.types.contains(MID)).count();
        long hardLine = this.initialListOfPlayers.stream().filter(player -> player.types.contains(HARDLINE)).count();
        long support = this.initialListOfPlayers.stream().filter(player -> player.types.contains(SUPPORT)).count();
        long fullSupport = this.initialListOfPlayers.stream().filter(player -> player.types.contains(FULLSUPPORT)).count();

        listOfRoles.add(new Counter((int) carry, CARRY));
        listOfRoles.add(new Counter((int) mid, MID));
        listOfRoles.add(new Counter((int) hardLine, HARDLINE));
        listOfRoles.add(new Counter((int) support, SUPPORT));
        listOfRoles.add(new Counter((int) fullSupport, FULLSUPPORT));
    }

    public void makeDistributionListForCommands() throws UnAvailableRoleException {


        while (!initialListOfPlayers.isEmpty() && distributionListForCommands.size() < 10) {
            //сортировка по ммр
            Collections.sort(initialListOfPlayers);


            //создать список уникальных ролей
            for (int i = 0; i < listOfRoles.size(); i++) {
                Counter role = listOfRoles.get(i);

                if (role.countOfRole == 2) {
                    listOfUniqueRoles.add(role);
                }
            }


            for (int i = 0; i < initialListOfPlayers.size(); i++) {

                //нахождение пользователей обладающих уникальной ролью
                boolean uniqueRoleWasFined = false;

                //Если в списке пользователей имеются уникальные роли
                if (!listOfUniqueRoles.isEmpty()) {
                    for (int j = 0; j < listOfUniqueRoles.size(); j++) {
                        PlayerType uniqueRole = listOfUniqueRoles.get(j).type;

                        //присвоение пользователям роли и занесение их в список распределения по ролям
                        Player player = initialListOfPlayers.get(i);
                        if (player.types.contains(uniqueRole)) {
                            uniqueRoleWasFined = true;

                            //проверить на наличие других уникальных ролей у данного пользователя
                            checkQuantityOfUniqueRoles(j, player);

                            //Присвоить пользователю уникальную роль
                            player.setPlayerRole(uniqueRole);
                            //добавить пользователя в список распределения команд
                            distributionListForCommands.add(player);
                            System.out.println("Найден игрок роли " + uniqueRole);
                            //удалить пользователя из изначального списка
                            initialListOfPlayers.remove(player);

                            int quantityOfFinedRoles = 0;
                            for (int k = 0; k < distributionListForCommands.size(); k++) {
                                if (distributionListForCommands.get(i).getPlayerRole().equals(uniqueRole))
                                    quantityOfFinedRoles++;
                            }
                            if (quantityOfFinedRoles == 2) {
                                break;
                            }

                            //найти пользователя который содержит эту же роль
                            Player anotherPlayerWithThisUniqueRole = null;
                            for (int k = i; k < initialListOfPlayers.size(); k++) {
                                if (k + 1 < initialListOfPlayers.size()) {
                                    if (initialListOfPlayers.get(k + 1).types.contains(uniqueRole))
                                        anotherPlayerWithThisUniqueRole = initialListOfPlayers.get(k + 1);
                                }
                            }

                            //Второй пользователь найден
                            if (anotherPlayerWithThisUniqueRole != null) {
                                System.out.println("выбрано два игрока роли " + uniqueRole);
                                anotherPlayerWithThisUniqueRole.setPlayerRole(uniqueRole);

                                //проверить на наличие других уникальных ролей у данного пользователя
                                checkQuantityOfUniqueRoles(j, anotherPlayerWithThisUniqueRole);

                                //Присвоить пользователю уникальную роль
                                anotherPlayerWithThisUniqueRole.setPlayerRole(uniqueRole);

                                //добавить пользователя в список распределения команд
                                distributionListForCommands.add(anotherPlayerWithThisUniqueRole);

                                //удалить пользователя из изначального списка
                                initialListOfPlayers.remove(anotherPlayerWithThisUniqueRole);

                                //Удалить счётчик с данными ролям ведь больше их нам не нужно
                                listOfRoles.remove(listOfUniqueRoles.get(j));

                                cleanUsersRoles(uniqueRole);
                                cleanCounters(uniqueRole);

                            } else {
                                //второй владелец уникальной роли не найден
                                //!!!! ЭТО КАК ВООБЩЕ?
                                System.err.println("!!!!!ЭТО КАК ВООБЩЕ!!!!!");
                                throw new UnAvailableRoleException("Второй владелец уникальной роли не найден");
                            }
                            Collections.sort(initialListOfPlayers);
                        }
                    }
                } else {

                    //ведём охоту на клонов


                }
                if (uniqueRoleWasFined) {
                    continue;
                }


                System.out.println(distributionListForCommands.size());
            }
        }
    }

    /**
     * Удалить у пользователей данную роль ведь она нам больше не понадобится
     */
    private void cleanUsersRoles(PlayerType uniqueRole) {
        for (int k = 0; k < initialListOfPlayers.size(); k++) {
            Player somePlayer = initialListOfPlayers.get(k);
            somePlayer.types.remove(uniqueRole);
        }
    }

    /**
     * Удалить счётчик распределённой роли
     */
    private void cleanCounters(PlayerType uniqueRole) {
        for (int k = 0; k < listOfRoles.size(); k++) {
            PlayerType role = listOfRoles.get(k).type;
            if (role.equals(uniqueRole)) listOfRoles.remove(role);
        }
    }

    private void checkQuantityOfUniqueRoles(int j, Player player) throws UnAvailableRoleException {
        for (int k = j; k < listOfUniqueRoles.size(); k++) {
            PlayerType anotherUniqueRole = listOfUniqueRoles.get(k).type;
            if (player.types.contains(anotherUniqueRole)) {
                throw new UnAvailableRoleException("Обнаружен недостаток пользователей для закрытия ролей");
            }
        }
    }


//    public List<Player> getListDistributionForCommands(){}

}
