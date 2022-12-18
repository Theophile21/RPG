package Game;

import Characters.Character;
import Characters.*;
import item.Armor;
import item.Weapon;

import java.util.ArrayList;
import java.util.List;

import static main.main.readIntInput;

public abstract class RoundEnd {

    //Méthode qui permet au joueur d'améliorer ses personnages
    public static void Upgrades(List<Character> characters) {
        List<Object> tempcharacters = new ArrayList<>(characters);

        for (int i = 0; i < tempcharacters.size(); i++) {
            if (tempcharacters.get(i) instanceof Warrior) {

                System.out.println("\nQue voulez-vous améliorer pour votre " + characters.get(i).getName() + " ?" +
                        "\n 1. Les dégats " +
                        "\n2. L'efficacité de la nourriture " +
                        "\n3. La quantité de nourriture " +
                        "\n4. La défense");

                int upgrade = 0;
                while (upgrade == 0) {
                    upgrade = readIntInput();

                    switch (upgrade) {
                        case 1 -> ((Warrior) characters.get(i)).upDmg();
                        case 2 -> ((Warrior) characters.get(i)).upFood();
                        case 3 -> ((Warrior) characters.get(i)).upFoodNb();
                        case 4 -> ((Warrior) characters.get(i)).upDef();
                        default -> upgrade = 0;
                    }
                }
            } else if (tempcharacters.get(i) instanceof Hunter) {
                System.out.println("\nQue voulez-vous améliorer pour votre " + characters.get(i).getName() + " ?" +
                        "\n 1. Les dégats " +
                        "\n2. L'efficacité de la nourriture " +
                        "\n3. Le nombre de flèches" +
                        "\n4. La défense");

                int upgrade = 0;
                while (upgrade == 0) {
                    upgrade = readIntInput();

                    switch (upgrade) {
                        case 1 -> ((Hunter) characters.get(i)).upDmg();
                        case 2 -> ((Hunter) characters.get(i)).upFood();
                        case 3 -> ((Hunter) characters.get(i)).upArrowNb();
                        case 4 -> ((Hunter) characters.get(i)).upDef();
                        default -> upgrade = 0;
                    }
                }
            } else if (tempcharacters.get(i) instanceof Mage) {
                System.out.println("\nQue voulez-vous améliorer pour votre " + characters.get(i).getName() + " ?" +
                        "\n 1. Les dégats " +
                        "\n2. La quantité de mana" +
                        "\n3. La quantité de potions " +
                        "\n4. La défense");

                int upgrade = 0;
                while (upgrade == 0) {
                    upgrade = readIntInput();

                    switch (upgrade) {
                        case 1 -> ((Mage) characters.get(i)).upDmg();
                        case 2 -> ((Mage) characters.get(i)).upMana();
                        case 3 -> ((Mage) characters.get(i)).upPotNb();
                        case 4 -> ((Mage) characters.get(i)).upDef();
                        default -> upgrade = 0;
                    }
                }

            } else if (tempcharacters.get(i) instanceof Healer) {
                System.out.println("\nQue voulez-vous améliorer pour votre " + characters.get(i).getName() + " ?" +
                        "\n 1. Les soins " +
                        "\n2. La quantité de mana" +
                        "\n3. La quantité de potions " +
                        "\n4. La défense");

                int upgrade = 0;
                while (upgrade == 0) {
                    upgrade = readIntInput();

                    switch (upgrade) {
                        case 1 -> ((Healer) characters.get(i)).upDmg();
                        case 2 -> ((Healer) characters.get(i)).upMana();
                        case 3 -> ((Healer) characters.get(i)).upPotNb();
                        case 4 -> ((Healer) characters.get(i)).upDef();
                        default -> upgrade = 0;
                    }
                }

            }
        }
    }

    //Méthode qui réinitialise les stats des personnages
    public static void resetStats(List<Character> characters) {
        List<Object> tempcharacters = new ArrayList<>(characters);

        for (int i = 0; i < tempcharacters.size(); i++) {
            if (tempcharacters.get(i) instanceof Warrior) {
                ((Warrior) characters.get(i)).reset();
            } else if (tempcharacters.get(i) instanceof Hunter) {
                ((Hunter) characters.get(i)).reset();
            } else if (tempcharacters.get(i) instanceof Mage) {
                ((Mage) characters.get(i)).reset();
            } else if (tempcharacters.get(i) instanceof Healer) {
                ((Healer) characters.get(i)).reset();
            }

        }
    }

    //Méthode qui gère les drop d'armes
    public static List<Weapon> weaponDrop(List<Weapon> weaponList, List<Character> characters) {
        //Liste dans laquelle les armes droppées sont stockée
        List<Weapon> weaponInventory = new ArrayList<>();

        for (int i = 0; i < characters.size(); i++) {
            //RNG qui détermine si le joueur drop une arme
            int ifWeap = (int) (Math.random() * (characters.size()));

            //Si le joueur drop une arme
            if (ifWeap == 1) {
                //Rng qui détermine quelle arme le joueur drop
                int whichWeap = (int) (Math.random() * weaponList.size());
                //Ajoute l'arme droppée à l'inventaire, et la retire du pool d'arme droppable
                weaponInventory.add(weaponList.get(whichWeap));
                System.out.println("Vous droppez " + weaponList.get(whichWeap).getName() + " sur les cadavres des monstres.");
                weaponList.remove(whichWeap);
            }
        }
        return weaponInventory;
    }

    //Méthode identique à celle du drop d'armes, mais pour les armures
    public static List<Armor> armorDrop(List<Armor> armorList, List<Character> characters){
        List<Armor> armorInventory = new ArrayList<>();

        for (int i = 0; i < characters.size(); i++) {


            int ifArm = (int) (Math.random() * (characters.size()));

            if (ifArm == 0) {
                int whichArm = (int) (Math.random() * armorList.size());
                armorInventory.add(armorList.get(whichArm));
                System.out.println("Vous droppez " + armorList.get(whichArm).getName() + " sur les cadavres des monstres.");
                armorList.remove(whichArm);
            }
        }
        return armorInventory;
    }

}