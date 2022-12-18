package Characters;

import Ennemies.Ennemy;
import item.Armor;
import item.Weapon;

import java.util.List;

import static main.main.readIntInput;

public class Character {

    //Variables / Attributes all characters have
    public String name;
    public int hp, dmg, dmgUp, defUp;
    public boolean def;
    public Weapon weapon;
    public Armor armor;


    //Constructor for character
    public Character(String name, int hp, int dmg, int dmgUp, int defUp, boolean def, Weapon weapon, Armor armor) {
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
        this.defUp = 0;
        this.dmgUp = 1;
        this.def = false;
        this.weapon = weapon;
        this.armor = armor;
    }


    //Method every character has

    public String getName() {
        return this.name;
    }

    public void attack(Ennemy target) {
        target.hp -= this.dmg * this.dmgUp;
        System.out.println(this.name + " inflige " + this.dmg * this.dmgUp + " dégats.");
    }

    public int getHealth() {
        return this.hp;
    }

    public int getDamage() {
        return this.dmg * this.dmgUp;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void heal() {
        this.hp += 10;
    }

    public boolean getDef() {
        return this.def;
    }

    public void defend() {
        this.def = true;
    }

    public void undefended() {
        this.def = false;
    }

    public static int getTarget(List<Ennemy> selectedfoe) {
        int target = 5;
        while (target > selectedfoe.size()) {
            System.out.println("\nQui voulez-vous attaquer?");
            for (int i = 0; i < selectedfoe.size(); i++) {
                System.out.println(i + ". " + selectedfoe.get(i).getName() + ": " + selectedfoe.get(i).getHealth());
            }
            target = readIntInput();
            if (target >= 0 && target < selectedfoe.size()) {
                break;
            } else {
                target = 5;
            }
        }
        return target;
    }

    public void equipWeapon(Weapon weapon){
        this.weapon = weapon;
        this.dmg += this.weapon.getDamage();
    }

    public void equipArmor(Armor armor){
        this.armor = armor;
        this.defUp += this.armor.getDefense();
    }

    public void unequipWeapon(){
        this.dmg -= this.weapon.getDamage();
    }

    public void unequipArmor(){
        this.defUp -= this.armor.getDefense();
    }

    public String weaponName(){
        return this.weapon.getName();
    }

    public String armorName(){
        return this.armor.getName();
    }
}



