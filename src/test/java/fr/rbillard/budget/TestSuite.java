package fr.rbillard.budget;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.rbillard.budget.service.PeriodServiceTest;
import fr.rbillard.budget.service.UserServiceTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	UserServiceTest.class,
	PeriodServiceTest.class
})
public class TestSuite {

}