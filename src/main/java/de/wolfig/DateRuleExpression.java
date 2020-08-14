package de.wolfig;

public class DateRuleExpression {
    public String[] rules;

    public DateRuleExpression(String string) {
        rules = string.split(";");
        if(rules.length != DateRule.getRuleAmount()) {
            System.out.println("The amount of given parameter did not equal the ruleAmount parameter");
            System.exit(1);
        }
    }
}
