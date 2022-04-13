package terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Assembly {
    public static void ShowAssemblyClass() throws IOException {
        System.out.println("Enter Your name: ");
        BufferedReader inputname = new BufferedReader(new InputStreamReader(System.in));
        String inname = inputname.readLine();
        System.out.println("Your name is " + inname);
        for (; ; ) {
            BufferedReader enterSelectPosition = new BufferedReader(new InputStreamReader(System.in));
            String selectPosition;

            System.out.println("Enter password Your card:");
            Scanner inputpassword = new Scanner(System.in);
            String enter_password_card = inputpassword.next();
            if (inname.equals("Vitali") & enter_password_card.equals("8888")) {
                System.out.println("This card belong " + inname);
                Activity activity = new Activity(enter_password_card);
                do {
                    activity.showMenu();
                    selectPosition = enterSelectPosition.readLine();
                    activity.caseMenu(selectPosition);
                    if (selectPosition == "q") break;
                } while (activity.Valid(selectPosition));
                if (selectPosition == "q") break;
            }
            else {
                System.out.println("Error, is not your card. Run application again");
            }
        }
    }
}
