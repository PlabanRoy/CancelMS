package com.cg.cap;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.cap.repository.CancelRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest
public class CancelRepositoryTest{

	
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CancelRepository cancelRepository;
	
	@Test
	public void testCancelOrder() {
		
	}
}
