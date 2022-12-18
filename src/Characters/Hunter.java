package Characters;

import Ennemies.Ennemy;
import item.Armor;
import item.Weapon;

public class Hunter extends Character{
    //integers to store level of upgrades
    public int foodUp,currentFood, arrowNb, arrowMax;



    //Player specific constructor
    public Hunter(String name, int hp, int dmg, int dmgUp, int defUp, boolean def, Weapon weapon, Armor armor) {
        //Calling constructor of super class
        super(name, hp, dmg, dmgUp, defUp, def, weapon, armor);
        //Setting level of upgrades to 0
        this.defUp = 0;
        this.foodUp = 1;
        this.currentFood = 3;
        this.arrowNb = 5;
        this.arrowMax = 5;
    }





    //Class specific methods
    public void eat(){
        this.hp += 20 * foodUp;
        this.currentFood -= 1;
        System.out.println("Vous mangez et récupérez " + 20 * foodUp + " hp.");
    }

    public int getFood(){
        return this.currentFood;
    }

    public int getArrowNb(){
        return this.arrowNb;
    }

    public void hit(Ennemy target) {
        target.hp -= 15;
        System.out.println(this.name + " inflige " + 15 + " dégats.");
    }

    @Override
    public void attack(Ennemy target){
        target.hp -= this.dmg * this.dmgUp;
        System.out.println(this.name + " inflige " + this.dmg * this.dmgUp + " dégats.");
        this.arrowNb -= 1;
    }


    //Upgrades
    public void upDmg(){
        this.dmgUp += 1;
        System.out.printf("Vous améliorez les dégats.");
    }

    public void upFood(){
        this.foodUp += 1;
        System.out.println("Vous améliorez l'efficacité de la nourriture.");
    }

    public void upArrowNb(){
        this.arrowMax += 3;
        System.out.println("Vous améliorez le nombre de flèche.");
    }

    public void upDef(){
        this.defUp += 10;
        System.out.println("Améliore la défense.");
    }

    //Reset stats after the round
    public void reset(){
        this.hp = 100;
        this.currentFood = 3;
        this.arrowNb = arrowMax;
    }
}
