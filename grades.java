package homework;

public class grades {
    public static void main(String[] args) {
        String[][] newArray = {{"          ", "course1 ","course2 ","course3 ","course4 ","course5 "},
                {"Student 1: ", "A    ","A    ","A    ","A    ","A  "},
                {"Student 2: ", "B    ","B    ","B    ","B    ","B "},
                {"Student 3: ","C    ","C    ","C    ","C    ","C  "}};
        System.out.println("After Initialization");
        System.out.println();
        System.out.println("           Grades of three students");
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<6;j++){
                System.out.print(newArray[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("After Changes");
        System.out.println("           Grades of three students");
        newArray[2][5]="C";
        newArray[3][1]="A    ";
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<6;j++){
                System.out.print(newArray[i][j]);
            }
            System.out.println();
        }

    }
}
