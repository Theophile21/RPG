package Characters;

import Ennemies.Ennemy;
import item.Armor;
import item.Weapon;

import java.util.List;

import static main.main.readIntInput;

public class Healer extends Character {

    //integers to store level of upgrades
    public int manaMax, potNb, mana, potMax;


    //Player specific constructor
    public Healer(String name, int hp, int dmg, int dmgUp, int defUp, boolean def, Weapon weapon, Armor armor) {
        //Calling constructor of super class
        super(name, hp, dmg, dmgUp, defUp, def, weapon, armor);
        //Setting level of upgrades to 0
        this.defUp = 0;
        this.manaMax = 30;
        this.potNb = 3;
        this.mana = 30;
        this.potMax = 3;
    }


    //Class specific methods
    public void drink(){
        this.mana += 20;
        this.potNb -= 1;
        System.out.println("Vous mangez et récupérez " + 20 + " mana.");
    }

    public int getPotNb(){
        return this.potNb;
    }

    public int getMana(){
        return this.mana;
    }

    public void hit(Ennemy target) {
        target.hp -= 10;
        System.out.println(this.name + " inflige " + 10 + " dégats.");
    }

    public void heal(Character target){
        target.hp += this.dmg * this.dmgUp;
        System.out.println(this.name + " soigne " + this.dmg + " hp.");
        this.mana -= 10;
    }

    public static int getAlly(List<Character> characters){
        int target = 5;
        while(target > characters.size()){
            System.out.println("\nQui voulez-vous soigner?");
            for (int i = 0; i < characters.size(); i++){
                System.out.println(i + ". " + characters.get(i).getName() + ": " + characters.get(i).getHealth());
            }
            target = readIntInput();
            System.out.println(target);
            if (target >= 0 && target < characters.size()){
                break;
            } else {
                target = 5;
            }
        }
        return target;
    }


    //Upgrades
    public void upDmg(){
        this.dmgUp += 1;
        System.out.println("Vous améliorez les dégats.");
    }

    public void upMana(){
        this.manaMax += 15;
        System.out.println("Vous améliorez la quantité de mana.");
    }

    public void upPotNb(){
        this.potMax += 1;
        System.out.println("Vous améliorez le nombre de potions.");
    }

    public void upDef(){
        this.defUp += 10;
        System.out.println("Vous améliorez la défense.");
    }

    //Reset stats after round
    public void reset(){
        this.hp = 70;
        this.mana = manaMax;
        this.potNb = potMax ;
    }
}
