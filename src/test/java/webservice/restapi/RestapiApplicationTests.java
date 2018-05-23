package webservice.restapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import webservice.restapi.controller.NoteController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestapiApplicationTests {

	@Autowired
	private NoteController noteController;

	@Test
	public void contextLoads() {
	}
	@Test
	public void get_all_notes(){

	}

}
