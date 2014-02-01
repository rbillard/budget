package fr.rbillard.budget;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import fr.rbillard.budget.entity.PeriodBudgetTest;
import fr.rbillard.budget.entity.PeriodTest;
import fr.rbillard.budget.service.impl.BudgetServiceTest;
import fr.rbillard.budget.service.impl.OperationServiceTest;
import fr.rbillard.budget.service.impl.PeriodBudgetServiceTest;
import fr.rbillard.budget.service.impl.PeriodServiceTest;
import fr.rbillard.budget.service.impl.UserServiceTest;

@RunWith( Suite.class )
@Suite.SuiteClasses({
	UserServiceTest.class,
	PeriodServiceTest.class,
	BudgetServiceTest.class,
	PeriodBudgetServiceTest.class,
	OperationServiceTest.class,
	PeriodTest.class,
	PeriodBudgetTest.class
})
public class TestSuite {

}