package fr.rbillard.budget;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.rbillard.budget.service.UserServiceTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	UserServiceTest.class
})
public class TestSuite {

}