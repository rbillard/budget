package fr.rbillard.budget;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.rbillard.budget.dao.OperationDAOTest;
import fr.rbillard.budget.service.BudgetServiceTest;
import fr.rbillard.budget.service.OperationServiceTest;
import fr.rbillard.budget.service.PeriodBudgetServiceTest;
import fr.rbillard.budget.service.PeriodServiceTest;
import fr.rbillard.budget.service.UserServiceTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	UserServiceTest.class,
	PeriodServiceTest.class,
	BudgetServiceTest.class,
	PeriodBudgetServiceTest.class,
	OperationServiceTest.class,
	OperationDAOTest.class,
})
public class TestSuite {

}