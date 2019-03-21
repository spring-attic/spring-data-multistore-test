/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.example;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.example.jpa.User;
import org.springframework.data.example.jpa.UserRepository;
import org.springframework.data.example.mongodb.Person;
import org.springframework.data.example.mongodb.PersonRepository;
import org.springframework.data.example.neo4j.Movie;
import org.springframework.data.example.neo4j.MovieRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/application-context.xml")
@TransactionConfiguration(transactionManager = "jpaTransactionManager")
public class ApplicationIntegrationTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	MovieRepository movieRepository;

	@Test
	@Transactional
	public void workWithJpaEntity() {

		User user = new User();
		user.firstname = "Dave";
		user.lastname = "Matthews";

		userRepository.save(user);

		List<User> result = userRepository.findByLastname("Matthews");
		assertThat(result.size(), is(1));
		assertThat(result.get(0), is(user));
	}

	@Test
	public void workWithMongoEntity() {

		personRepository.deleteAll();

		Person person = new Person();
		person.firstname = "Dave";
		person.lastname = "Matthews";

		person = personRepository.save(person);

		List<Person> result = personRepository.findByLastname("Matthews");
		assertThat(result.size(), is(1));
		assertThat(result.get(0).id, is(person.id));
	}

	@Test
	public void wirkWithNeo4JEntity() {

		movieRepository.deleteAll();

		Movie movie = new Movie();
		movie.title = "The usual suspects";

		movie = movieRepository.save(movie);

		List<Movie> result = movieRepository.findByTitle("The usual suspects");
		assertThat(result.size(), is(1));
		assertThat(result.get(0).id, is(movie.id));
	}
}
