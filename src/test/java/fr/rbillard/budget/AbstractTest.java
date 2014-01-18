package fr.rbillard.budget;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.rbillard.budget.conf.BudgetTestConfiguration;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { BudgetTestConfiguration.class } )
public abstract class AbstractTest {

}
