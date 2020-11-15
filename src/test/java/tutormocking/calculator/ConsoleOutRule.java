package tutormocking.calculator;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ConsoleOutRule implements TestRule {
    private final String note;

    public ConsoleOutRule(final String note) {
        this.note = note;
    }

    public Statement apply(final Statement base, final Description description) {
        System.out.println("rule applied. note: " + note);
        return base;
    }
}
