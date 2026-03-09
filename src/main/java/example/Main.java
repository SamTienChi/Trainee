package example;
import UI.TestUI;
import UI.TestUI_v2;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        TestUI myUi = new TestUI();
        TestUI_v2 myUI2 = new TestUI_v2();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
//        myUi.runUI();
        myUI2.runUI();
    }
}