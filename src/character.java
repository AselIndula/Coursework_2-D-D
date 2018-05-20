import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 *
 * @author Asel Siriwardena
 *2017454
 *
 */

public class character {
    static int level;
    static int[] gameValues = new int[6];
    static int[] skillNumberList;
    static int characterNumber;
    static double BAB;
    static Skill head = new Skill(0, "", "", "", //this is the constructor of Skill LinkedList
            "", "", 0, 0, null);
    static Class classHead = new Class("", 0, 0, 0,
            0, 0, 0, 0, 0, 0, null, 0);
    static int spTotal;

    public static void levelSelect() {

        /*
        Getting level and validating it
        * */

        int num = 0;
        String stringInput = "";
        boolean assignment = true;

        Scanner input = new Scanner(System.in);
        System.out.print(Main.BLACK_BOLD + "Enter Level : " + Main.RESET);


        do {                                        //checking that input is valid
            stringInput = input.nextLine();
            try {
                num = Integer.parseInt(stringInput);
                assignment = false;

            } catch (NumberFormatException e) {
                System.out.println(Main.RED + "Invalid input,Try Again \n" + Main.RESET);
            }
        } while (assignment);

        level = num;

    }

    public static void characterSelection() {
        /*
        offer a selection of Classes or Professions to choose from and accept a choice.
        */
        int number;
        String classRead = null;
        Scanner input = new Scanner(System.in);
        File classFile = new File("resources\\class.txt");
        int index = 1;
        Class pointer = classHead;

        try {
            Scanner classInput = new Scanner((classFile));
            while (classInput.hasNext()) {

                classRead = classInput.nextLine();
                Class temp = new Class("", 0, 0, 0, 0,
                        0, 0, 0, 0, 0, null, index);

                pointer.setName(classRead);
                pointer.setHitdice((int) (Math.random() * 1000 % 6 + 1));
                pointer.setNext_Class(temp);
                pointer = temp;
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(Main.RED + "Class File Not Found" + Main.RESET);
            System.exit(0);
        }

        Class classCurrent = classHead.getNext_Class();
        while (classCurrent != null) {
            System.out.println(Main.BLUE + classCurrent.getName() + Main.RESET);
            classCurrent = classCurrent.getNext_Class();
        }
        String charaInput = "";


        do {
            System.out.print(Main.BLACK_BOLD + "Enter Character Number : " + Main.RESET);
            charaInput = input.nextLine();
            try {
                number = Integer.parseInt(charaInput);
                if (number > 12 || number < 1) throw new invalidSelection();
                break;

            } catch (NumberFormatException e) {
                System.out.println(Main.RED + "Invalid input,Try Again " + Main.RESET);
            } catch (invalidSelection e) {
                System.out.println(Main.RED + "Invalid Selection,Try Again \n" + Main.RESET);
            }
        } while (true);
        characterNumber = number;
    }

    public static void statCalculationMethod() {
        System.out.print(Main.BLUE + "\n Select a Method for Generate Attributes \n"
                + "[1] Entering the attributes directly \n"
                + "[2] Roll 4d6 and discard the lowest value \n"
                + "[3] Roll 4d6 and discard the lowest value and if the attribute is 16 or higher, add " +
                "the value of an additional 1d6 \n"
                + "[4] Roll Method IX \n"
                + Main.RESET);

        int num = 0;
        String stringInput = "";
        boolean assignment = true;

        Scanner input = new Scanner(System.in);

        do {                                        //checking that input is valid
            System.out.print(Main.BLACK_BOLD + "\nMethod Number : ");
            stringInput = input.nextLine();
            try {
                num = Integer.parseInt(stringInput);
                if (num > 4 || num < 0) throw new invalidSelection();
                assignment = false;

            } catch (NumberFormatException e) {
                System.out.println(Main.RED + "Invalid input,Try Again \n" + Main.RESET);
            } catch (invalidSelection e) {
                System.out.println(Main.RED + "Invalid Selection,Try Again \n" + Main.RESET);
            }
        } while (assignment);

        if (num == 1) {
            directMethod();
        } else if (num == 2) {
            fourD6Method(0);
        } else if (num == 3) {
            fourD6Method(1);
        } else {
            methodIX();
        }

        Class classCurrent = classHead.getNext_Class();
        while (classCurrent != null) {
            if (classCurrent.getIndex() == characterNumber) {
                classCurrent.setStr_Dice(gameValues[0]);
                classCurrent.setDex_Dice(gameValues[1]);
                classCurrent.setCon_Dice(gameValues[2]);
                classCurrent.setInt_Dice(gameValues[3]);
                classCurrent.setWis_Dice(gameValues[4]);
                classCurrent.setCha_Dice(gameValues[5]);
            }
            classCurrent = classCurrent.getNext_Class();
        }

    }

    public static void directMethod() {

        System.out.print("Enter Str   :");          //prompts user to enter values.
        gameValues[0] = isNumberValid();                //checking if the input is valid.
        System.out.print("Enter Dex   :");          //doing the same for other inputs.
        gameValues[1] = isNumberValid();
        System.out.print("Enter Con   :");
        gameValues[2] = isNumberValid();
        System.out.print("Enter Int   :");
        gameValues[3] = isNumberValid();
        System.out.print("Enter Wis   :");
        gameValues[4] = isNumberValid();
        System.out.print("Enter Cha   :");
        gameValues[5] = isNumberValid();
        System.out.print("Enter Level :");
        gameValues[6] = isNumberValid();


        System.out.println(Main.BLUE + "Level" + "[" + level + "]");
        System.out.println("Str: [" + gameValues[0] + "][" + bonusCal(gameValues[0]) + "]"); //bonus is calculated in the method bonusCal.
        System.out.println("Dex: [" + gameValues[1] + "][" + bonusCal(gameValues[1]) + "]");
        System.out.println("Con: [" + gameValues[2] + "][" + bonusCal(gameValues[2]) + "]");
        System.out.println("Int: [" + gameValues[3] + "][" + bonusCal(gameValues[3]) + "]");
        System.out.println("Wis: [" + gameValues[4] + "][" + bonusCal(gameValues[4]) + "]");
        System.out.println("Cha: [" + gameValues[5] + "][" + bonusCal(gameValues[5]) + "]");
        int hitPoints = (gameValues[2]) * level; //bonus Con value is casted into double value
        System.out.println("HP:" + "[" + hitPoints + "]" + Main.RESET);

    }


    public static Integer isNumberValid() {               //method has a integer return to calculate bonus. no input,Scanner is inside the method

        Scanner input = new Scanner(System.in);
        if (!input.hasNextInt()) {                        // checking that input is a string
            System.out.println("Invalid Input");
            System.exit(1);                       // if its so terminating the programme
        }
        int value = input.nextInt();                     //checking that input is a minus value
        if (value < 0) {
            System.out.println("Invalid Input");
            System.exit(1);                       //if its so terminating the programme
        }
        return value;                                    //returns the value

    }

    public static String bonusCal(int value) {
        int input_bonus;
        String bonusString;
        if (value > 10) {
            input_bonus = (value - 10) / 2;
        } else {
            input_bonus = -((10 - value) + 1) / 2;
        }
        if (input_bonus > 0) {
            bonusString = "+" + input_bonus;
        } else {
            bonusString = " " + input_bonus;
        }
        return bonusString;
    }

    public static void fourD6Method(int method) {

        boolean statement = true;
        while (statement) {


            for (int i = 0; i < 6; i++) {
                gameValues[i] = randomGenerator(method);
            }

            System.out.println(Main.BLUE + "Level" + "[" + level + "]");
            System.out.println("Str: [" + gameValues[0] + "][" + bonusCal(gameValues[0]) + "]"); //bonus is calculated in the method bonusCal.
            System.out.println("Dex: [" + gameValues[1] + "][" + bonusCal(gameValues[1]) + "]");
            System.out.println("Con: [" + gameValues[2] + "][" + bonusCal(gameValues[2]) + "]");
            System.out.println("Int: [" + gameValues[3] + "][" + bonusCal(gameValues[3]) + "]");
            System.out.println("Wis: [" + gameValues[4] + "][" + bonusCal(gameValues[4]) + "]");
            System.out.println("Cha: [" + gameValues[5] + "][" + bonusCal(gameValues[5]) + "]");
            int hitPoints = (gameValues[2]) * level; //bonus Con value is casted into double value
            System.out.println("HP:" + "[" + hitPoints + "]" + Main.RESET);

            System.out.print(Main.BLUE_BOLD + "Accept(1) or Re-roll(2) : " + Main.RESET);

            Scanner input = new Scanner(System.in);
            int accepted = input.nextInt();

            switch (accepted) {
                case 1:
                    System.out.println(Main.BLACK_BOLD + "    *Accepted");
                    statement = false;
                    break;
                case 2:
                    System.out.println("    *Re-roll");
                    break;
                default:
                    System.out.println("*Please enter valid input" + Main.RESET);
            }
        }

        System.out.println(Main.BLUE + "Level" + "[" + level + "]");
        System.out.println("Str: [" + gameValues[0] + "][" + bonusCal(gameValues[0]) + "]"); //bonus is calculated in the method bonusCal.
        System.out.println("Dex: [" + gameValues[1] + "][" + bonusCal(gameValues[1]) + "]");
        System.out.println("Con: [" + gameValues[2] + "][" + bonusCal(gameValues[2]) + "]");
        System.out.println("Int: [" + gameValues[3] + "][" + bonusCal(gameValues[3]) + "]");
        System.out.println("Wis: [" + gameValues[4] + "][" + bonusCal(gameValues[4]) + "]");
        System.out.println("Cha: [" + gameValues[5] + "][" + bonusCal(gameValues[5]) + "]");
        int hitPoints = (gameValues[2]) * level;
        System.out.println("HP:" + "[" + hitPoints + "]" + Main.RESET);


    }

    private static int randomGenerator(int method) {

        int dice[] = new int[4]; //Creating the array with 4 elements.

        for (int i = 0; i < 4; i++) {
            dice[i] = (int) (Math.random() * 1000 % 6 + 1);  //generating random number .
        }

        int diceTotal = 0;
        int minimum = dice[1];
        for (int i = 1; i < dice.length; i++) {
            if (dice[i] < minimum) {
                minimum = dice[i];
            }
        }
        for (int i = 0; i < dice.length; i++) {
            diceTotal += dice[i];
        }
        if (method == 1) {
            diceTotal += (int) (Math.random() * 1000 % 6 + 1);
        }
        return diceTotal;
    }

    public static void methodIX() {

        /*
        Skills are weighted according to the behaviors of the characters
        */

        if (characterNumber == 1) {
            gameValues[0] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[2] = methodIXStatGen(7);
            gameValues[3] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[5] = methodIXStatGen(4);
        } else if (characterNumber == 2) {
            gameValues[0] = methodIXStatGen(9);
            gameValues[3] = methodIXStatGen(8);
            gameValues[2] = methodIXStatGen(7);
            gameValues[4] = methodIXStatGen(6);
            gameValues[1] = methodIXStatGen(5);
            gameValues[5] = methodIXStatGen(4);
        } else if (characterNumber == 3) {
            gameValues[2] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[4] = methodIXStatGen(7);
            gameValues[3] = methodIXStatGen(6);
            gameValues[0] = methodIXStatGen(5);
            gameValues[5] = methodIXStatGen(4);
        } else if (characterNumber == 4) {
            gameValues[4] = methodIXStatGen(9);
            gameValues[2] = methodIXStatGen(8);
            gameValues[3] = methodIXStatGen(7);
            gameValues[1] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[0] = methodIXStatGen(4);
        } else if (characterNumber == 5) {
            gameValues[1] = methodIXStatGen(9);
            gameValues[3] = methodIXStatGen(8);
            gameValues[2] = methodIXStatGen(7);
            gameValues[5] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[0] = methodIXStatGen(4);
        } else if (characterNumber == 6) {
            gameValues[5] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[3] = methodIXStatGen(7);
            gameValues[2] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[0] = methodIXStatGen(4);
        } else if (characterNumber == 7) {
            gameValues[0] = methodIXStatGen(9);
            gameValues[2] = methodIXStatGen(8);
            gameValues[1] = methodIXStatGen(7);
            gameValues[3] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[5] = methodIXStatGen(4);
        } else if (characterNumber == 8) {
            gameValues[4] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[3] = methodIXStatGen(7);
            gameValues[2] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[0] = methodIXStatGen(4);
        } else if (characterNumber == 9) {
            gameValues[0] = methodIXStatGen(9);
            gameValues[3] = methodIXStatGen(8);
            gameValues[2] = methodIXStatGen(7);
            gameValues[5] = methodIXStatGen(6);
            gameValues[4] = methodIXStatGen(5);
            gameValues[1] = methodIXStatGen(4);
        } else if (characterNumber == 10) {
            gameValues[5] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[3] = methodIXStatGen(7);
            gameValues[4] = methodIXStatGen(6);
            gameValues[2] = methodIXStatGen(5);
            gameValues[0] = methodIXStatGen(4);
        } else if (characterNumber == 11) {
            gameValues[0] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[2] = methodIXStatGen(7);
            gameValues[5] = methodIXStatGen(6);
            gameValues[2] = methodIXStatGen(5);
            gameValues[3] = methodIXStatGen(4);
        } else {
            gameValues[0] = methodIXStatGen(9);
            gameValues[1] = methodIXStatGen(8);
            gameValues[2] = methodIXStatGen(7);
            gameValues[4] = methodIXStatGen(6);
            gameValues[3] = methodIXStatGen(5);
            gameValues[5] = methodIXStatGen(4);
        }
    }

    public static int methodIXStatGen(int times) {
        int dice[] = new int[times]; //Creating the array with 4 elements.

        for (int i = 0; i < dice.length; i++) {
            dice[i] = (int) (Math.random() * 1000 % 6 + 1);  //generating random number .
        }

        Arrays.sort(dice);

        return dice[1] + dice[2] + dice[3];
    }


    static void create() {

        System.out.println();
        String skillRead = null;
        File skillFile = new File("resources\\skills.txt"); //Adding the Skill File
        Skill pointer = head;

        try {
            Scanner skillInput = new Scanner(skillFile);

            while (skillInput.hasNext()) {

                skillRead = skillInput.nextLine();
                Skill temp = new Skill(0, skillRead, "", "",
                        "", "", 0, 0, null);

                pointer.setShort_desc1(skillInput.nextLine());
                pointer.setShort_desc2(skillInput.nextLine());
                pointer.setShort_desc3(skillInput.nextLine());

                pointer.setNext_skill(temp);

                pointer = temp;

            }

        } catch (FileNotFoundException e) {
            System.out.println(Main.RED + "No File Found" + Main.RESET);
            System.exit(0);
        }

        System.out.println(Main.BLUE + "-Skill List-" + Main.RESET);

        Skill current = head.getNext_skill();
        int count = 1;

        while (current != null) {
            System.out.println(Main.BLUE + "[" + count + "]" + current.getName() + Main.RESET);
            current.setIndex(count);
            current = current.getNext_skill();
            count++;
        }

        System.out.println(Main.BLACK_BOLD + "\n Please select " + level + " skills from below \n" + Main.RESET);

        String stringInput = "";
        Scanner scannerSkills = new Scanner(System.in);
        skillNumberList = new int[level];

        for (int i = 0; i < level; i++) {

            System.out.print(Main.BLACK_BOLD + "Enter Skill Number : " + Main.RESET);
            stringInput = scannerSkills.nextLine();
            try {
                int enteredSkill = Integer.parseInt(stringInput);
                if (enteredSkill > 18 || enteredSkill < 0) throw new invalidSelection();
                for (int sk : skillNumberList) {
                    if (enteredSkill == sk) throw new repeatedErrException();
                }
                skillNumberList[i] = enteredSkill;

            } catch (NumberFormatException e) {
                i--;
                System.out.println(Main.RED + "Invalid input,Try Again !" + Main.RESET);
            } catch (invalidSelection e) {
                System.out.println(Main.RED + "Invalid Skill Selection!" + Main.RESET);
                i--;
            } catch (repeatedErrException e) {
                System.out.println(Main.RED + "Already Entered!" + Main.RESET);
                i--;
            }

        }


        current = head.getNext_skill();
        while (current != null) {
            int i = 0;
            for (i = 0; i < skillNumberList.length; i++) {
                if (current.getIndex() == skillNumberList[i]) {
                    current.setRanks(rankUpper(current));
                }
            }
            for (int u = 0; u <= level; u++) break;
            current = current.getNext_skill();
        }


        current = head.getNext_skill();
        while (current != null) {
            if (current.getName().equals("Acrobatics")) {
                current.setStat_affinity(gameValues[1]);
            } else if (current.getName().equals("Arcana")) {
                current.setStat_affinity(gameValues[3]);
            } else if (current.getName().equals("Athletics")) {
                current.setStat_affinity(gameValues[0]);
            } else if (current.getName().equals("Bluff")) {
                current.setStat_affinity(gameValues[5]);
            } else if (current.getName().equals("Diplomacy")) {
                current.setStat_affinity(gameValues[5]);
            } else if (current.getName().equals("Dungeoneering")) {
                current.setStat_affinity(gameValues[4]);
            } else if (current.getName().equals("Endurance")) {
                current.setStat_affinity(gameValues[2]);
            } else if (current.getName().equals("Heal")) {
                current.setStat_affinity(gameValues[4]);
            } else if (current.getName().equals("History")) {
                current.setStat_affinity(gameValues[3]);
            } else if (current.getName().equals("Insight")) {
                current.setStat_affinity(gameValues[4]);
            } else if (current.getName().equals("Intimidate")) {
                current.setStat_affinity(gameValues[5]);
            } else if (current.getName().equals("Nature")) {
                current.setStat_affinity(gameValues[4]);
            } else if (current.getName().equals("Perception")) {
                current.setStat_affinity(gameValues[4]);
            } else if (current.getName().equals("Religion")) {
                current.setStat_affinity(gameValues[3]);
            } else if (current.getName().equals("Stealth")) {
                current.setStat_affinity(gameValues[1]);
            } else if (current.getName().equals("Streetwise")) {
                current.setStat_affinity(gameValues[5]);
            } else {
                current.setStat_affinity(gameValues[1]);
            }
            current = current.getNext_skill();
        }

        current = head.getNext_skill();
        while (current != null) {
            int i = 0;
            for (i = 0; i < skillNumberList.length; i++) {
                if (current.getIndex() == skillNumberList[i]) {
                    System.out.println("\n" + Main.BLUE + current.getName());
                    System.out.println(current.getShort_desc1());
                    System.out.println(current.getShort_desc2());
                    System.out.println(current.getShort_desc3());
                    System.out.println("Stat Affinity : " + current.getStat_affinity());
                    System.out.println("Rank : " + current.getRanks() + Main.RESET + "\n");
                }
            }
            current = current.getNext_skill();
        }

    }


    public static int rankUpper(Skill current) {

        /*
        Method is used to rank up skills using skill Points
        */

        int maxSkillPoints = level + 3;
        int num;
        int finalValue = 0;
        String stringInput = "";
        boolean assignment = true;
        Scanner input = new Scanner(System.in);

        do {
            System.out.print(Main.BLACK_BOLD + "Rank Up " + current.getName() + " : " + Main.RESET);
            stringInput = input.nextLine();
            try {
                num = Integer.parseInt(stringInput);
                if (num > (character.level + 3)) throw new invalidSelection();
                if (spTotal + num > maxSkillPoints) throw new invalidSelection();
                spTotal += num;
                finalValue = num;
                assignment = false;

            } catch (NumberFormatException e) {
                System.out.println(Main.RED + "Invalid input,Try Again !" + Main.RESET);
            } catch (invalidSelection e) {
                System.out.println(Main.RED + "Value Could not be Accepted !" + Main.RESET);

            }

        } while (assignment);


        return finalValue;
    }

    /*
     *Base Attack Bonus is generated in here.
     * For combat orientated Classes/Professions: BAB = Level
     *Classes/Professions you think are average at combat: BAB = (Level*3)/4
     *Classes/Professions you think are combat adverse: BAB = Level/2
     *
     * */

    public static void BAB_Generate() {
        if (characterNumber == 1 || characterNumber == 4 || characterNumber == 5 || characterNumber == 7) {
            BAB = level;
        } else if (characterNumber == 2 || characterNumber == 8 || characterNumber == 11 || characterNumber == 12) {
            BAB = (level * 3) / 4;
        } else {
            BAB = level / 2;
        }

        Class classCurrent = classHead.getNext_Class();
        while (classCurrent != null) {
            if (classCurrent.getIndex() == characterNumber) {
                classCurrent.setBAB_Type(BAB);
            }
            classCurrent = classCurrent.getNext_Class();
        }

    }


}






