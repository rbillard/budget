package fr.rbillard.budget;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.rbillard.budget.conf.BudgetTestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BudgetTestConfiguration.class })
@Transactional
public class PeriodTest {
	
	@Test
	public void a() {
		assertTrue( true );
	}

}
