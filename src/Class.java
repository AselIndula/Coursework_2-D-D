
/*
 *
 * @author Asel Siriwardena
 *2017454
 *
 */

public class Class {

    String name;
    int Hitdice, Str_Dice, Dex_Dice, Con_Dice,
            Int_Dice, Wis_Dice, Cha_Dice, Skill_Points;
    double BAB_Type;
    Class next_Class;
    int index; //index is added , because using it easily find a node

    public Class(String name, int hitdice, int str_Dice, int dex_Dice, int con_Dice,
                 int int_Dice, int wis_Dice, int cha_Dice, int skill_Points,
                 double BAB_Type, Class next_Class, int index) { //constructor of the Class Nodes
        this.name = name;
        Hitdice = hitdice;
        Str_Dice = str_Dice;
        Dex_Dice = dex_Dice;
        Con_Dice = con_Dice;
        Int_Dice = int_Dice;
        Wis_Dice = wis_Dice;
        Cha_Dice = cha_Dice;
        Skill_Points = skill_Points;
        this.BAB_Type = BAB_Type;
        this.next_Class = next_Class;
        this.index = index;
    }

    /*
    Getters and Setters
    */

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitdice() {
        return Hitdice;
    }

    public void setHitdice(int hitdice) {
        Hitdice = hitdice;
    }

    public int getStr_Dice() {
        return Str_Dice;
    }

    public void setStr_Dice(int str_Dice) {
        Str_Dice = str_Dice;
    }

    public int getDex_Dice() {
        return Dex_Dice;
    }

    public void setDex_Dice(int dex_Dice) {
        Dex_Dice = dex_Dice;
    }

    public int getCon_Dice() {
        return Con_Dice;
    }

    public void setCon_Dice(int con_Dice) {
        Con_Dice = con_Dice;
    }

    public int getInt_Dice() {
        return Int_Dice;
    }

    public void setInt_Dice(int int_Dice) {
        Int_Dice = int_Dice;
    }

    public int getWis_Dice() {
        return Wis_Dice;
    }

    public void setWis_Dice(int wis_Dice) {
        Wis_Dice = wis_Dice;
    }

    public int getCha_Dice() {
        return Cha_Dice;
    }

    public void setCha_Dice(int cha_Dice) {
        Cha_Dice = cha_Dice;
    }

    public int getSkill_Points() {
        return Skill_Points;
    }

    public void setSkill_Points(int skill_Points) {
        Skill_Points = skill_Points;
    }

    public double getBAB_Type() {
        return BAB_Type;
    }

    public void setBAB_Type(double BAB_Type) {
        this.BAB_Type = BAB_Type;
    }

    public Class getNext_Class() {
        return next_Class;
    }

    public void setNext_Class(Class next_Class) {
        this.next_Class = next_Class;
    }
}
