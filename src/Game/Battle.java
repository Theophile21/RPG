package Game;

import Characters.Character;
import Characters.*;
import Ennemies.Ennemy;
import item.Armor;
import item.Weapon;

import java.util.List;
import java.util.Random;

import static Characters.Character.getTarget;
import static main.main.readIntInput;


public class Battle{

    public List<Character> characters;
    public List<Ennemy> selectedfoe;
    public List<Object> turnOrder;
    public int currentTurn;

    public Battle (List<Character> characters, List<Ennemy> selectedfoe, List<Object> turnOrder, int currentTurn){
        this.characters =  characters;
        this.selectedfoe = selectedfoe;
        this.turnOrder = turnOrder;
        this.currentTurn = 1;
    }

    //Méthode qui vérifie si tous les alliés sont morts
    public static boolean getTeamAlive(List<Character> characters) {
        return characters.size() != 0;
    }

    //Méthode qui vérifie si tous les ennemis sont morts
    public static boolean getFoeAlive(List<Ennemy> selectedfoe){
        return selectedfoe.size() != 0;
        }


    //Méthode qui vérifie si le round est terminé
    public static boolean checkAlive(List<Character> characters, List<Ennemy> selectedfoe) {
        boolean teamAlive = getTeamAlive(characters);
        boolean foeAlive = getFoeAlive(selectedfoe);
        boolean roundend = false;
        if (!teamAlive) {
            System.out.println("\n\n\nVous avez perdu la partie.");
            roundend = true;
            return roundend;
        } else if (!foeAlive) {
            System.out.println("\n\n\nVous avez gagné le round.");
            roundend = true;
            return roundend;
        } else {
            return roundend;
        }
    }

    //Méthode principale de combat
    public static void fight(int i, List<Character> characters, List<Ennemy> selectedfoe, List<Object> turnOrder, List<Weapon> weaponInventory, List<Armor> armorInventory) {

            if (turnOrder.get(i) instanceof Warrior) {


                int action = 0;
                while(action == 0) {

                    System.out.println("\nPersonnage joueur : " + ((Character) turnOrder.get(i)).getName() +
                            "\nHp : " + ((Character) turnOrder.get(i)).getHealth() +
                            "\nDmg : " + ((Character) turnOrder.get(i)).getDamage() +
                            "\nNourriture : " + ((Warrior) turnOrder.get(i)).getFood() +
                            "\nChoisissez l'action que vous voulez faire. " +
                            "\n 1. Attaquer             4. Equiper une arme" +
                            "\n 2. Se protéger         5. Equiper une armure" +
                            "\n 3. Manger");

                    action = readIntInput();

                    switch(action){
                        case 1 :
                            //Choix de la cible de l'attaque
                            int target = getTarget(selectedfoe);

                            //Attaque
                            ((Character) turnOrder.get(i)).attack(selectedfoe.get(target));
                            deadFoe(target, selectedfoe, turnOrder);
                            break;

                        case 2 :
                            ((Character) turnOrder.get(i)).defend();
                            break;

                        case 3 :
                            //Vérifie s'il reste suffisament de nourriture
                            if (((Warrior) turnOrder.get(i)).getFood() > 0) {
                                ((Warrior) turnOrder.get(i)).eat();
                            } else {
                                System.out.println("You have no more food.");
                                action = 0;
                            }
                            break;
                        case 4 :
                            toEquipWeapon(i, turnOrder, weaponInventory);
                            action = 0;
                            break;
                        case 5 :
                            toEquipArmor(i, turnOrder, armorInventory);
                            action = 0;
                            break;
                        default :
                            action = 0;
                            break;
                    }
                }
            }

            else if (turnOrder.get(i) instanceof Hunter) {
                int action = 0;
                while(action == 0) {

                    System.out.println("\nPersonnage joueur : " + ((Character) turnOrder.get(i)).getName() +
                            "\nHp : " + ((Character) turnOrder.get(i)).getHealth() +
                            "\nDmg : " + ((Character) turnOrder.get(i)).getDamage() +
                            "\nNourriture : " + ((Hunter) turnOrder.get(i)).getFood() +
                            "\n Flèches : " + ((Hunter) turnOrder.get(i)).getArrowNb() +

                            "\nChoisissez l'action que vous voulez faire. " +
                            "\n 1. Tirer                                4. Manger" +
                            "\n 2. Frapper                              5. Equiper une arme" +
                            "\n 3. Se protéger                          6. Equiper une armure");

                    action = readIntInput();

                    switch(action){
                        case 1 :
                            if (((Hunter) turnOrder.get(i)).getArrowNb() > 0) {
                                //Choix de la cible de l'attaque
                                int target = getTarget(selectedfoe);

                                //Attaque
                                ((Character) turnOrder.get(i)).attack(selectedfoe.get(target));
                                deadFoe(target, selectedfoe, turnOrder);
                            } else {
                                System.out.println("Vous n'avez plus de flèches.");
                                action = 0;
                            }
                            break;

                        case 2 :
                            //Choix de la cible de l'attaque
                            int target = getTarget(selectedfoe);

                            //Attaque
                            ((Hunter) turnOrder.get(i)).hit(selectedfoe.get(target));
                            deadFoe(target, selectedfoe, turnOrder);

                            break;

                        case 3 :
                            ((Character) turnOrder.get(i)).defend();
                            break;

                        case 4 :
                            if (((Hunter) turnOrder.get(i)).getFood() > 0) {
                                ((Hunter) turnOrder.get(i)).eat();
                            } else {
                                System.out.println("You have no more food.");
                                action = 0;
                            }
                        case 5 :
                            toEquipWeapon(i, turnOrder, weaponInventory);
                            action = 0;
                            break;

                        case 6 :
                            toEquipArmor(i, turnOrder, armorInventory);
                            action = 0;
                            break;

                        default :
                            action = 0;
                            break;

                    }
                }

            }

            else if (turnOrder.get(i) instanceof Mage) {

                int action = 0;
                while(action == 0) {

                    System.out.println("\nPersonnage joueur : " + ((Character) turnOrder.get(i)).getName() +
                            "\nHp : " + ((Character) turnOrder.get(i)).getHealth() +
                            "\nDmg : " + ((Character) turnOrder.get(i)).getDamage() +
                            "\nPotions : " + ((Mage) turnOrder.get(i)).getPotNb() +
                            "\n Mana : " + ((Mage) turnOrder.get(i)).getMana() +

                            "\nChoisissez l'action que vous voulez faire. " +
                            "\n 1. Envoyer un sort (5 mana)             4. Boire une potion de mana" +
                            "\n 2. Frapper                              5. Equiper une arme" +
                            "\n 3. Se protéger                          6. Equiper une armure");

                    action = readIntInput();

                    switch(action){
                        case 1 :
                            if (((Mage) turnOrder.get(i)).getMana() > 0) {
                                //Choix de la cible de l'attaque
                                int target = getTarget(selectedfoe);

                                //Attaque
                                ((Character) turnOrder.get(i)).attack(selectedfoe.get(target));
                                deadFoe(target, selectedfoe, turnOrder);
                            } else {
                                System.out.println("Vous n'avez plus de mana.");
                                action = 0;
                            }
                            break;

                        case 2 :
                            //Choix de la cible de l'attaque
                            int target = getTarget(selectedfoe);

                            //Attaque
                            ((Mage) turnOrder.get(i)).hit(selectedfoe.get(target));
                            deadFoe(target, selectedfoe, turnOrder);

                            break;

                        case 3 :
                            ((Character) turnOrder.get(i)).defend();
                            break;

                        case 4 :
                            if (((Mage) turnOrder.get(i)).getPotNb() > 0) {
                                ((Mage) turnOrder.get(i)).drink();
                            } else {
                                System.out.println("Vous n'avez plus de potion");
                                action = 0;
                            }
                            break;
                        case 5 :
                            toEquipWeapon(i, turnOrder, weaponInventory);
                            action = 0;
                            break;

                        case 6 :
                            toEquipArmor(i, turnOrder, armorInventory);
                            action = 0;
                            break;

                        default :
                            action = 0;
                            break;
                    }
                }
            }

            else if (turnOrder.get(i) instanceof Healer) {

                int action = 0;
                while(action == 0) {

                    System.out.println("\nPersonnage joueur : " + ((Character) turnOrder.get(i)).getName() +
                            "\nHp : " + ((Character) turnOrder.get(i)).getHealth() +
                            "\nDmg : " + ((Character) turnOrder.get(i)).getDamage() +
                            "\nPotions : " + ((Healer) turnOrder.get(i)).getPotNb() +
                            "\n Mana : " + ((Healer) turnOrder.get(i)).getMana() +
                            "\nChoisissez l'action que vous voulez faire. " +
                            "\n 1. Soigner un allié (10 mana)           4. Boire une potion de mana" +
                            "\n 2. Frapper                              5. Equiper une arme" +
                            "\n 3. Se protéger                          6. Equiper une armure");

                    action = readIntInput();

                    switch(action){
                        case 1 :
                            if (((Healer) turnOrder.get(i)).getMana() > 0) {
                                //Choix de la cible du soin
                                int target = Healer.getAlly(characters);

                                //Soin
                                ((Healer) turnOrder.get(i)).heal(characters.get(target));

                            } else {
                                System.out.println("Vous n'avez plus de mana.");
                                action = 0;
                            }
                            break;

                        case 2 :
                            //Choix de la cible de l'attaque
                            int target = getTarget(selectedfoe);

                            //Attaque
                            ((Healer) turnOrder.get(i)).hit(selectedfoe.get(target));
                            deadFoe(target, selectedfoe, turnOrder);

                            break;

                        case 3 :
                            ((Character) turnOrder.get(i)).defend();
                            break;

                        case 4 :
                            if (((Healer) turnOrder.get(i)).getPotNb() > 0) {
                                ((Healer) turnOrder.get(i)).drink();
                            } else {
                                System.out.println("Vous n'avez plus de potion");
                                action = 0;
                            }
                            break;

                        case 5 :
                            toEquipWeapon(i, turnOrder, weaponInventory);
                            action = 0;
                            break;
                        case 6 :
                            toEquipArmor(i, turnOrder, armorInventory);
                            action = 0;
                            break;

                        default :
                            action = 0;
                            break;
                    }
                }
            }

            else if (turnOrder.get(i) instanceof Ennemy) {
                Random random = new Random();
                int target = random.nextInt(characters.size());

                if (characters.get(target).getDef()){
                    System.out.println("L'ennemi essaye d'attaqer " + characters.get(target).getName() + ", mais ce dernier se défend.");
                } else {
                    System.out.println("\nL'ennemi attaque " + characters.get(target).getName());
                    ((Ennemy) turnOrder.get(i)).attack(characters.get(target));
                    deadAlly(target, characters, turnOrder);
                }
            }
    }


    //Méthode qui permet d'équiper les armes sur les alliés
    public static void toEquipWeapon(int i, List<Object> turnOrder, List<Weapon> weaponInventory){
        System.out.println("Vous avez " + ((Character) turnOrder.get(i)).weaponName() + " (" + ((Character) turnOrder.get(i)).weapon.getDamage() + ") d'équipé. Quelle arme voulez-vous équiper à la place?");
        for (int k = 0; k < weaponInventory.size(); k++){
            System.out.println(k + ". " + weaponInventory.get(k).getName() + weaponInventory.get(k).getDamage());
        }
        System.out.println(9 + ". Annuler");
        int choice = -1;
        while (choice == -1){
            choice = readIntInput();

            if (choice >= 0 && choice < weaponInventory.size()){

                //Vérifie si l'arme est équipable par la classe du perso
                if ((weaponInventory.get(choice).getWarEquipable() && turnOrder.get(i) instanceof Warrior) ||
                        (weaponInventory.get(choice).getHunterEquipable() && turnOrder.get(i) instanceof Hunter) ||
                        (weaponInventory.get(choice).getSpellCasterEquipable() && turnOrder.get(i) instanceof Mage) ||
                        (weaponInventory.get(choice).getSpellCasterEquipable() && turnOrder.get(i) instanceof Healer)) {

                    //Retire les stats de l'arme précédent, ajoute celles de la nouvelle, et retire l'arme de l'inventaire.
                    ((Character) turnOrder.get(i)).unequipWeapon();
                    ((Character) turnOrder.get(i)).equipWeapon(weaponInventory.get(choice));
                    System.out.println("Vous équipez " + weaponInventory.get(choice).getName());
                    weaponInventory.remove(choice);
                } else {
                    System.out.println("Cette arme n'est pas équipable par cette classe.");
                    choice = -1;
                }

            } else if (choice == 9){
                break;
            } else {
                choice = -1;
            }
        }
    }


    //Idem, pour les armures
    public static void toEquipArmor(int i, List<Object> turnOrder, List<Armor> armorInventory){
        System.out.println("Vous avez " + ((Character) turnOrder.get(i)).armorName() + " (" + ((Character) turnOrder.get(i)).armor.getDefense() + ") d'équipée. Quelle arme voulez-vous équiper à la place?");
        for (int k = 0; k < armorInventory.size(); k++){
            System.out.println(k + ". " + armorInventory.get(k).getName() + armorInventory.get(k).getDefense());
        }
        System.out.println( 9 + ". Annuler");
        int choice = -1;
        while (choice == -1){
            choice = readIntInput();

            if (choice >= 0 && choice < armorInventory.size()){
                if ((armorInventory.get(choice).getWarEquipable() && turnOrder.get(i) instanceof Warrior) ||
                        (armorInventory.get(choice).getHunterEquipable() && turnOrder.get(i) instanceof Hunter) ||
                        (armorInventory.get(choice).getSpellCasterEquipable() && turnOrder.get(i) instanceof Mage) ||
                        (armorInventory.get(choice).getSpellCasterEquipable() && turnOrder.get(i) instanceof Healer)){

                    ((Character) turnOrder.get(i)).unequipArmor();
                    ((Character) turnOrder.get(i)).equipArmor(armorInventory.get(choice));
                    System.out.println("Vous équipez " + armorInventory.get(choice).getName());
                    armorInventory.remove(choice);
                } else {
                    System.out.println("Cette armure n'est pas équipable par cette classe.");
                    choice = -1;
                }


            } else if (choice == 9){
                break;
            } else {
                choice = -1;
            }
        }
    }


    //Méthode qui vérifie et gère les ennemis mort
    public static void deadFoe(int target, List<Ennemy> selectedfoe, List<Object> turnOrder){
        //On retire les ennemis morts de la liste selectedfoe
        if (!selectedfoe.get(target).isAlive()){
            System.out.println(selectedfoe.get(target).getName() + " est mort.");
            selectedfoe.remove(target);

            //On retire les ennemis morts de la list turnOrder
            for (int k = 0; k < turnOrder.size(); k++){
                if (turnOrder.get(k) instanceof Ennemy){
                    if (!((Ennemy) turnOrder.get(k)).isAlive()){
                        turnOrder.remove(k);
                    }
                }
            }
        }
    }

    //Méthode qui vérifie et gère les ennemis morts
    public static void deadAlly(int target, List<Character> characters, List<Object> turnOrder){
        //On retire les ennemis morts de la liste selectedfoe
        if (!characters.get(target).isAlive()){
            System.out.println(characters.get(target).getName() + " est mort.");
            characters.remove(target);

            //On retire les ennemis morts de la list turnOrder
            for (int k = 0; k < turnOrder.size(); k++){
                if (turnOrder.get(k) instanceof Character){
                    if (!((Character) turnOrder.get(k)).isAlive()){
                        turnOrder.remove(k);
                    }
                }
            }
        }
    }


    //Méthode qui permet de print les pv et dégats de tous les alliés vivants
    public static void printState(List<Character> characters, List<Ennemy> selectedfoe){
        System.out.println("\n\nAlliés : ");
        for (Character character : characters) {
            System.out.println(character.getName() + "    Hp : " + character.getHealth() + "    Dmg : " + character.getDamage());
        }
        System.out.println("\nEnnemis : ");
        for (Ennemy ennemy : selectedfoe) {
            System.out.println(ennemy.getName() + "    Hp : " + ennemy.getHealth() + "    Dmg : " + ennemy.getDamage());
        }
    }

}
