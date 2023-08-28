package com.example.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
	private  MockMvc mockMvc ;

	@Test
    public void TestCase1() throws Exception {
		 
		String st = "{\"babyId\": \"1000\",\"babyFirstName\": \"Demo\", \"babyLastName\": \"demo\", \"fatherName\": \"demo\", \"motherName\": \"demo\", \"address\": \"demo\"}";
	 	mockMvc.perform(MockMvcRequestBuilders.post("/")
	 			.contentType(MediaType.APPLICATION_JSON)
	 			.content(st)
	 			.accept(MediaType.APPLICATION_JSON))
	        	.andExpect(status().isOk())
				.andExpect(jsonPath("$").value(true))
	        	.andReturn();
	    
    }


	@Test
	void test_case2() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.get("/babyFirstName")
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isArray())
						.andReturn();
	}

	@Test
	void test_case3() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.get("/0/2")
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andDo(print())
						.andExpect(jsonPath("$.content").exists())
						.andReturn().getResponse().getContentAsString();
	}

	@Test
	void test_case4() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.get("/1/4/motherName")
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.pageable.sort.sorted").value(true))
						.andReturn();
	}

	@Test
	void test_case5() throws Exception{	

		 mockMvc.perform(MockMvcRequestBuilders.get("/babyId")
		 				.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isArray())
						.andReturn();
	}

}
