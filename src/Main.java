import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 *
 * @author Asel Siriwardena
 *2017454
 *
 */


/*
some exceptions are created to use in try catch method
 */

class repeatedErrException extends Exception {
}

class invalidSelection extends Exception {
}

public class Main {

    public static final String RESET = "\033[0m";  // Text Reset
    public static final String RED = "\033[0;31m";   // RED
    public static final String BLUE = "\u001B[34m";   //BLUE
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE BOLD
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK BOLD


    public static void main(String[] args) {

        character kar = new character(); //character class object is created.
        kar.levelSelect();
        kar.characterSelection();
        kar.statCalculationMethod();
        kar.create();
        kar.BAB_Generate();
        finalStatPrint();
        fileSave();
    }

    private static void finalStatPrint() {
        Skill skillCurrent = character.head.getNext_skill();
        Class currentClass = character.classHead.getNext_Class();


        System.out.println(Main.BLUE + "Level" + "[" + character.level + "]");
        while (currentClass.next_Class != null) {
            if (currentClass.getIndex() == character.characterNumber) {
                System.out.println("Character : " + currentClass.getName());
                System.out.println("Str: [" + currentClass.getStr_Dice() + "][" + character.bonusCal(currentClass.getStr_Dice()) + "]");
                System.out.println("Dex: [" + currentClass.getDex_Dice() + "][" + character.bonusCal(currentClass.getDex_Dice()) + "]");
                System.out.println("Con: [" + currentClass.getCon_Dice() + "][" + character.bonusCal(currentClass.getCon_Dice()) + "]");
                System.out.println("Int: [" + currentClass.getInt_Dice() + "][" + character.bonusCal(currentClass.getInt_Dice()) + "]");
                System.out.println("Wis: [" + currentClass.getWis_Dice() + "][" + character.bonusCal(currentClass.getWis_Dice()) + "]");
                System.out.println("Cha: [" + currentClass.getCha_Dice() + "][" + character.bonusCal(currentClass.getCha_Dice()) + "]");
                int hitPoints = (currentClass.getCon_Dice() * character.level);
                System.out.println("HP :" + "[" + hitPoints + "]" + Main.RESET);
                
            }
            currentClass = currentClass.getNext_Class(); //calling next node of the linkedList
        }


        for (int i = 0; i < character.skillNumberList.length; i++) {
            if (skillCurrent.getIndex() == character.skillNumberList[i]) {
                System.out.println("\n" + Main.BLUE + skillCurrent.getName());
                System.out.println("Stat Affinity : " + skillCurrent.getStat_affinity());
                System.out.println("Rank : " + skillCurrent.getRanks() + Main.RESET + "\n");
            }
            skillCurrent = skillCurrent.getNext_skill();
        }

        System.out.println(Main.BLUE_BOLD + "Base Attack Bonus :" + character.BAB);
        System.out.println("Combat : " + (character.BAB + character.gameValues[0]));
        System.out.println("Damage :" + character.gameValues[0]);
    }

    private static void fileSave() {

        int number = 0;
        String stringInputY = "";
        boolean assignment = true;

        Scanner input = new Scanner(System.in);
        System.out.print("\nDo You Want To Save In a File ?\n Press \" 1 \" And Enter : "); //asking user if want to save

        do {
            stringInputY = input.nextLine();
            try {
                number = Integer.parseInt(stringInputY);
                assignment = false;
                                         //validating input
            } catch (NumberFormatException e) {
                System.out.println("Invalid input , Try Again \n");
            }
        } while (assignment);

        if (number == 1) {
            Skill skillCurrent = character.head.getNext_skill();
            Class currentClass = character.classHead.getNext_Class();

            File file = new File("Stat_Data.txt"); //creating a text file name Stat_Data
            FileWriter fw = null;

            try {
                fw = new FileWriter(file, true);
            } catch (IOException e) {
                System.out.println("Something Gone Wrongs");
            }
            PrintWriter pw = new PrintWriter(fw, true);

            while (currentClass.next_Class != null) {
                if (currentClass.getIndex() == character.characterNumber) {
                    pw.println("Character : " + currentClass.getName()+"\n");
                    pw.println("Str: [" + currentClass.getStr_Dice() + "][" + character.bonusCal(currentClass.getStr_Dice()) + "]");
                    pw.println("Dex: [" + currentClass.getDex_Dice() + "][" + character.bonusCal(currentClass.getDex_Dice()) + "]");
                    pw.println("Con: [" + currentClass.getCon_Dice() + "][" + character.bonusCal(currentClass.getCon_Dice()) + "]");
                    pw.println("Int: [" + currentClass.getInt_Dice() + "][" + character.bonusCal(currentClass.getInt_Dice()) + "]");
                    pw.println("Wis: [" + currentClass.getWis_Dice() + "][" + character.bonusCal(currentClass.getWis_Dice()) + "]");
                    pw.println("Cha: [" + currentClass.getCha_Dice() + "][" + character.bonusCal(currentClass.getCha_Dice()) + "]");
                    int hitPoints = (currentClass.getCon_Dice() * character.level);
                    pw.println("HP:" + "[" + hitPoints + "]");
                }
                currentClass = currentClass.getNext_Class();
            }


            for (int i = 0; i < character.skillNumberList.length; i++) {
                if (skillCurrent.getIndex() == character.skillNumberList[i]) {
                    pw.println("\n" + skillCurrent.getName());
                    pw.println("Stat Affinity : " + skillCurrent.getStat_affinity());
                    pw.println("Rank : " + skillCurrent.getRanks()+ "\n");
                }
                skillCurrent = skillCurrent.getNext_skill();
            }

            pw.println("Base Attack Bonus : " + character.BAB);
            pw.println("Combat : " + (character.BAB + character.gameValues[0]));
            pw.println("Damage : " + character.gameValues[0]);
        }
    }


}


