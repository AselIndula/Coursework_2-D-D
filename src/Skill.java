
/*
*
* @author Asel Siriwardena
*2017454
*/

public class Skill {
    private String name;
    private String optional;
    private String short_desc1;
    private String short_desc2;
    private String short_desc3;
    private int stat_affinity;
    private int ranks;
    private int index; //index is added , because using it easily find a node
    private Skill next_skill;


    public Skill(int index, String name, String optional, String short_desc1, String getShort_desc2,
                 String getShort_desc3, int stat_affinity, int ranks, Skill next_skill) { //Constructor of the Skill Nodes

        this.name = name;
        this.optional = optional;
        this.short_desc1 = short_desc1;
        this.short_desc2 = getShort_desc2;
        this.short_desc3 = getShort_desc3;
        this.stat_affinity = stat_affinity;
        this.ranks = ranks;
        this.index = index;

        this.next_skill = next_skill;
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

    public String getOptional() {
        return optional;
    }

    public void setOptional(String optional) {
        this.optional = optional;
    }

    public String getShort_desc1() {
        return short_desc1;
    }

    public void setShort_desc1(String short_desc1) {
        this.short_desc1 = short_desc1;
    }

    public String getShort_desc2() {
        return short_desc2;
    }

    public void setShort_desc2(String short_desc2) {
        this.short_desc2 = short_desc2;
    }

    public String getShort_desc3() {
        return short_desc3;
    }

    public void setShort_desc3(String short_desc3) {
        this.short_desc3 = short_desc3;
    }

    public int getStat_affinity() {
        return stat_affinity;
    }

    public void setStat_affinity(int stat_affinity) {
        this.stat_affinity = stat_affinity;
    }

    public int getRanks() {
        return ranks;
    }

    public void setRanks(int ranks) {
        this.ranks = ranks;
    }

    public Skill getNext_skill() {
        return next_skill;
    }

    public void setNext_skill(Skill next_skill) {
        this.next_skill = next_skill;
    }
}
