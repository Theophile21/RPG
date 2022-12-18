package Characters;

import Ennemies.Ennemy;
import item.Armor;
import item.Weapon;

public class Mage extends Character{
    //integers to store level of upgrades
    public int mana, manaMax, potNb, potMax;


    //Player specific constructor
    public Mage(String name, int hp, int dmg, int dmgUp, int defUp, boolean def, Weapon weapon, Armor armor) {
        //Calling constructor of super class
        super(name, hp, dmg, dmgUp, defUp, def, weapon, armor);
        //Setting level of upgrades to 0
        this.defUp = 0;
        this.mana = 35;
        this.manaMax = 35;
        this.potNb = 3;
        this.potMax = 3;
    }

    //Class specific methods
    public void drink() {
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

    @Override
    public void attack(Ennemy target){
        target.hp -= this.dmg * this.dmgUp;
        System.out.println(this.name + " inflige " + this.dmg * this.dmgUp + " dégats.");
        this.mana -= 5;
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
