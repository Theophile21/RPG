package Game;

import Characters.*;
import Characters.Character;
import Ennemies.Ennemy;
import item.Armor;
import item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.main.readIntInput;

public abstract class GameStart {


    //Méthode qui permet au joueur de sélectionner les personnages qu'ils désire jouer
    public static List<Character> charSelect() {

        //Liste qui sera print pour proposer les choix au joueur
        String[] heroes = {" Warrior", " Hunter", " Mage", " Healer", " I'm done"};
        //Liste qui stock les objets correspondant aux personnages choisis
        List<Character> characters = new ArrayList<>();
        //Cette liste sert juste à print la liste des pesonnages choisis (aurait pu etre fait avec getName())
        List<String> team = new ArrayList<>();


        System.out.println("Bienvenure dans ce RPG.\n Choisissez les personnages que vous voulez ajouter à votre équipe. \n");

        int charselect = 0;
        int charNb = 0;

        while (charselect == 0) {
            //Limite la taille de l'équipe à 4 perso
            if (team.size() < 4) {
                for (int i = 0; i < heroes.length; i++) {
                    //Print les choix possibles
                    System.out.println((i + 1) + heroes[i]);
                }
                charselect = readIntInput();


                switch (charselect) {

                    case 1:
                        //Initialise les objets correspondant au perso choisi et son arme et armure de départ
                        Weapon smallSword = new Weapon ("Epée de novice" + characters.size(), 0, true, false, false);
                        Armor smallWarriorArmor = new Armor ("Armure de novice" + characters.size(), 0, true, true, true);
                        characters.add(new Warrior("warrior" + charNb, 100, 20, 1, 0, false, smallSword, smallWarriorArmor));
                        charselect = 0;
                        team.add("Warrior ");
                        System.out.println("You have added a warrior to your team. Your team is now made up of : " + team);
                        charNb ++;
                        break;
                    case 2:
                        Weapon smallBow = new Weapon ("Arc de novice" + characters.size(), 0, false, true, false);
                        Armor smallHunterArmor = new Armor ("Armure de novice" + characters.size(), 0, true, true, true);
                        characters.add(new Hunter("hunter" + charNb, 100, 25, 1, 0, false, smallBow, smallHunterArmor));
                        charselect = 0;
                        team.add("Hunter ");
                        System.out.println("You have added a hunter to your team. Your team is now made up of : " + team);
                        charNb ++;
                        break;
                    case 3:
                        Weapon smallStaff = new Weapon ("Baguette de novice" + characters.size(), 0, false, false, true);
                        Armor smallMageArmor = new Armor ("Armure de novice" + characters.size(), 0, true, true, true);
                        characters.add(new Mage("mage" + charNb, 70, 25, 1, 0,  false, smallStaff, smallMageArmor));
                        charselect = 0;
                        team.add("Mage");
                        System.out.println("You have added a mage to your team. Your team is now made up of : " + team);
                        charNb ++;
                        break;
                    case 4:
                        Weapon smallWand = new Weapon ("Baguette de novice" + characters.size(), 0, false, false, true);
                        Armor smallHealerArmor = new Armor ("Armure de novice" + characters.size(), 0, true, true, true);
                        characters.add(new Healer("healer" + charNb, 70, 35, 1, 0, false, smallWand, smallHealerArmor));
                        charselect = 0;
                        team.add("Healer");
                        System.out.println("You have added a healer to your team. Your team is now made up of : " + team);
                        charNb ++;
                        break;
                    case 5:
                        break;
                    default:
                        charselect = 0;
                        break;
                }
            } else {
                break;
            }
        }
        return characters;
    }


    public static List<Ennemy> foeSelect(List<Character> characters, int foeDmgUp, int foeHpUp) {
        // Crée un pool d'ennemi
        List<Ennemy> ennemies = new ArrayList<>();
        ennemies.add(new Ennemy("Ogre", 100 * foeHpUp, 15 * foeDmgUp));
        ennemies.add(new Ennemy("Zombie", 100 * foeHpUp, 15 * foeDmgUp));
        ennemies.add(new Ennemy("Grunt", 80 * foeHpUp, 10 * foeDmgUp));
        ennemies.add(new Ennemy("Skeleton", 80 * foeHpUp, 10 * foeDmgUp));
        ennemies.add(new Ennemy("Witch", 70 * foeHpUp, 20 * foeDmgUp));
        ennemies.add(new Ennemy("Wizard", 70 * foeHpUp, 20 * foeDmgUp));
        ennemies.add(new Ennemy("Gargoyle", 60 * foeHpUp, 15 * foeDmgUp));
        ennemies.add(new Ennemy("Bat", 60 * foeHpUp, 15 * foeDmgUp));
        ennemies.add(new Ennemy("Dark Lord", 150 * foeHpUp, 25 * foeDmgUp));

        //Sélection aléatoire des ennemis que le joueur va affronter
        Random random = new Random();
        List<Ennemy> selectedfoe = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            int number = random.nextInt(ennemies.size());
            selectedfoe.add(ennemies.get(number));
            ennemies.remove(number);
        }
        return selectedfoe;
    }

    //Méthode qui crée le boss du combat final
    public static List<Ennemy> bossFight(){
        List<Ennemy> boss = new ArrayList<>();
        boss.add(new Ennemy ("Evil Overlord", 1000, 70));
        return boss;
    }

    public static List<Weapon> weaponList(){
        List<Weapon> weapons = new ArrayList<>();
        //Initialisation des items
        weapons.add(new Weapon("Epée courte", 15, true, false, false));
        weapons.add(new Weapon ("Epée longue", 30, true, false, false));
        weapons.add(new Weapon ("Arc léger", 20, false, true, false));
        weapons.add(new Weapon ("Arc puissant", 40, false, true, false));
        weapons.add(new Weapon ("Baguette", 20, false, false, true));
        weapons.add(new Weapon ("Baton magique", 30, false, false, true));
        weapons.add(new Weapon ("Sceptre", 40, false, false, true));
        weapons.add(new Weapon ("Gants magiques", 45, false, false, true));
        return weapons;
    }

    public static List<Armor> armorList(){
        List<Armor> armors = new ArrayList<>();
        //Initialisation des armures
        armors.add(new Armor("Armure légère", 15, true, true, true));
        armors.add(new Armor ("Armure en tissu", 30, true, true, true));
        armors.add(new Armor ("Cotte de maille", 20, true, true, false));
        armors.add(new Armor ("Armure en fer", 40, true, true, false));
        armors.add(new Armor ("Armure lourde", 20, true, false, false));
        armors.add(new Armor ("Tunique magique", 30, false, false, true));
        armors.add(new Armor ("Armure en or", 40, true, false, false));
        armors.add(new Armor ("Armure en diamant", 45, true, false, false));
        return armors;
    }

    //Méthode qui permet de mettre tous les alliés et ennemis dans une liste dans l'ordre dans lequel ils joueront
    public static List<Object> getTurnOrder(List<Character> characters, List<Ennemy> selectedfoe){
        List<Object> turnOrder = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            turnOrder.add(characters.get(i));
            turnOrder.add(selectedfoe.get(i));
        }
        return turnOrder;
    }

    //Méthode qui détermine l'ordre dans lequel les personanges joueront pour le combat de boss
    public static List<Object> getBossTurnOrder(List<Character> characters, List<Ennemy> selectedfoe){
        List<Object> turnOrder = new ArrayList<>(characters);
        turnOrder.add(selectedfoe.get(0));
        return turnOrder;
    }
}
