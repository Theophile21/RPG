package Ennemies;

import Characters.Character;

public class Ennemy {
    public String name;
    public int hp, dmg;

    //Constructor
    public Ennemy(String name, int hp, int dmg){
        this.name = name;
        this.hp = hp;
        this.dmg = dmg;
    }


    // Methods
    public String getName() {
        return this.name;
    }

    public void attack(Character target) {
        if(this.dmg - target.defUp < 0){
            System.out.println(this.name + " inflige 0 dégats.");
        } else {
            target.hp -= this.dmg - target.defUp;
            System.out.println(this.name + " inflige " + (this.dmg - target.defUp) + " dégats.");
        }
    }

    public int getHealth() {
        return this.hp;
    }

    public int getDamage() {
        return this.dmg;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }
}
