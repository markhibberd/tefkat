package tde.popup.actions;

import org.eclipse.ui.IActionFilter;

public class TDEQuestionsActionFilter implements IActionFilter {
    public static final TDEQuestionsActionFilter INSTANCE = new TDEQuestionsActionFilter();

    public boolean testAttribute(Object target, String name, String value) {
        System.out.println("===============");
        System.out.println(name);
        System.out.println(value);
        System.out.println(target);
        System.out.println("===============");
        return true;
    }

}
