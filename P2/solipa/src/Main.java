import java.util.*;

public class Main {

    public static void main (String[] args) {
        KochCurve kochCurve = new KochCurve();
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();

        kochCurve.setStart("F");
        kochCurve.addRule('F', "F+F-F-F+F");
        String word7 = kochCurve.iter(n, "F+F-F-F+F");
        System.out.println(word7);
    }

}
