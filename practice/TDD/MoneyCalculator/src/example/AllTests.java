package example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCurrencyConverter.class, TestMoneyCalculator.class })
public class AllTests {

}
