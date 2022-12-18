package item;

public class Armor {
    private final String name;
    private final int defense;
    boolean warEquipable, hunterEquipable, spellCasterEquipable;

    public Armor(String name, int defense, boolean warEquipable, boolean hunterEquipable, boolean spellCasterEquipable) {
        this.name = name;
        this.defense = defense;
        this.warEquipable = warEquipable;
        this.hunterEquipable = hunterEquipable;
        this.spellCasterEquipable = spellCasterEquipable;
    }

    public String getName() {
        return this.name;
    }

    public int getDefense() {
        return this.defense;
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

