package item;

public class Weapon {
    private final String name;
    private final int damage;
    boolean warEquipable, hunterEquipable, spellCasterEquipable;

    public Weapon(String name, int damage, boolean warEquipable, boolean hunterEquipable, boolean spellCasterEquipable) {
        this.name = name;
        this.damage = damage;
        this.warEquipable = warEquipable;
        this.hunterEquipable = hunterEquipable;
        this.spellCasterEquipable = spellCasterEquipable;
    }

    public String getName() {
        return this.name;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean getWarEquipable(){
        return this.warEquipable;
    }

    public boolean getHunterEquipable(){
        return this.hunterEquipable;
    }

    public boolean getSpellCasterEquipable(){
        return this.spellCasterEquipable;
    }
}