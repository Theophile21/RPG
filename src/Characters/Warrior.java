package Characters;

import item.Armor;
import item.Weapon;

public class Warrior extends Character {

    //integers to store level of upgrades
    public int foodUp, foodNb, currentFood, foodMax;
    

    //Player specific constructor
    public Warrior(String name, int hp, int dmg, int dmgUp, int defUp, boolean def, Weapon weapon, Armor armor) {
        //Calling constructor of super class
        super(name, hp, dmg, dmgUp, defUp, def, weapon, armor);
        //Setting level of upgrades to 0
        this.foodUp = 1;
        this.foodNb = 3;
        this.currentFood = 3;
        this.foodMax = 3;
    }


    //Player specific methods
    public void eat(){
        this.hp += 25 * foodUp;
        this.currentFood -= 1;
        System.out.println("Vous mangez et récupérez " + 20 * foodUp + " hp.");
    }

    public int getFood(){
        return this.currentFood;
    }

    //Upgrades
    public void upDmg(){
        this.dmgUp += 1;
        System.out.println("Vous améliorez les dégats.");
    }

    public void upFood(){
        this.foodUp += 1;
        System.out.println("Vous améliorez l'efficacité de nourriture.");
    }

    public void upFoodNb(){
        this.foodMax += 2;
        System.out.println("Vous améliorez la quantité de nourriture.");
    }

    public void upDef(){
        this.defUp += 10;
        System.out.println("Vous améliorez la défense");
    }

    //Reset stats after the round
    public void reset(){
        this.hp = 100;
        this.foodNb = this.foodMax;
    }
}
