package main;

import Characters.Character;
import Ennemies.Ennemy;
import Game.Battle;
import item.Armor;
import item.Weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Game.Battle.*;
import static Game.GameStart.*;
import static Game.RoundEnd.*;

public class main {

    public static void main(String[] args) {

        start();

    }

    public static void start(){
        List<Character> characters = new ArrayList<>();
        while (characters.size() == 0) {
            characters = charSelect();
        }
        List<Weapon> weaponList = weaponList();
        List<Weapon> weaponInventory = new ArrayList<>();
        List<Armor> armorList = armorList();
        List<Armor> armorInventory = new ArrayList<>();

        int foeDmgUp = 0;
        int foeHpUp = 1;


        //Boucle qui gère les 4 premiers rounds
        for (int turn = 0; turn < 4; turn++) {

            //Variables qui permettent d'incrémenter les stats des ennemis au fil des rounds
            if (turn % 2 == 0 ) {
                foeDmgUp += 1;
            } else {
                foeHpUp += 1;
            }

            //Initialisation de la liste des ennemis et de la liste qui stock l'ordre dans lequel les personnages jouent
            List<Ennemy> selectedfoe = foeSelect(characters, foeDmgUp, foeHpUp);
            List<Object> turnOrder = getTurnOrder(characters, selectedfoe);
            //Crée le combat
            new Battle(characters, selectedfoe, turnOrder, turn);

            printState(characters, selectedfoe);
            boolean roundend = false;

            //Boucle du combat
            while (!roundend) {

                for (int i = 0; i < turnOrder.size(); i++) {
                    fight(i, characters, selectedfoe, turnOrder, weaponInventory, armorInventory);
                    roundend = checkAlive(characters, selectedfoe);

                    //Quand le round est terminé
                    if (roundend) {
                        boolean roundwon = !getFoeAlive(selectedfoe);
                        //En cas de round gagné
                        if (roundwon) {

                            //Drop d'armes et d'armures
                            weaponInventory.addAll(weaponDrop(weaponList, characters));
                            armorInventory.addAll(armorDrop(armorList, characters));
                            //Améliore les personanges et reset leur stats
                            Upgrades(characters);
                            resetStats(characters);

                        //En cas de round perdu
                        } else {
                            int retry = -1;
                            while(retry == -1){
                                retry = readIntInput();

                                switch(retry){
                                    case 1 :
                                        start();
                                        break;
                                    case 2 :
                                        break;
                                }
                            }
                        }
                        break;
                    }
                    printState(characters, selectedfoe);
                }
                //Remet tous les personnages dans l'état non-défendu, ce qui leur permet de prendre des dégats au round suivant
                for (Object o : turnOrder) {
                    if (o instanceof Character) {
                        ((Character) o).undefended();
                    }
                }
            }


        }
        //Boucle qui gère le combat de boss
        int turn = 4 ;
        List<Ennemy> selectedfoe = bossFight();
        List<Object> turnOrder = getBossTurnOrder(characters, selectedfoe);
        new Battle(characters, selectedfoe, turnOrder, turn);

        printState(characters, selectedfoe);
        boolean roundend = false;

        while (!roundend) {

            for (int i = 0; i < turnOrder.size(); i++) {
                fight(i, characters, selectedfoe, turnOrder, weaponInventory, armorInventory);
                roundend = checkAlive(characters, selectedfoe);
                if (roundend) {
                    boolean roundwon = !getFoeAlive(selectedfoe);
                    if (roundwon) {
                        System.out.println("Bravo, vous avez gagné la partie.");

                    } else {
                        System.out.println("Dommage, vous avez perdu. Voulez vous réessayer?");
                        int retry = -1;
                        while(retry == -1){
                            retry = readIntInput();

                            switch(retry){
                                case 1 :
                                    start();
                                    break;
                                case 2 :
                                    break;
                            }
                        }
                    }
                    break;
                }
                printState(characters, selectedfoe);
            }
        }

    }


    //Méthode qui oblige l'utilisateur à entrer un int
    public static int readIntInput() {
        Scanner scanner = new Scanner(System.in);

        // Boucle jusqu'à ce qu'une valeur valide soit lue
        while (true) {
            System.out.println("\nEntrez un nombre acceptable:");

            // Vérifier si l'entrée suivante est un entier
            if (scanner.hasNextInt()) {
                // Lire l'entrée si elle est un entier
                return scanner.nextInt();
            } else {
                // Ignorer l'entrée si elle n'est pas un entier
                scanner.next();

            }
        }
    }
}

