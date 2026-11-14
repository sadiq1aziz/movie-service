	package com.app.movie;

	import com.app.movie.dto.MovieDto;
	import com.app.movie.domain.Genre;
	import org.junit.jupiter.api.Assertions;
	import org.junit.jupiter.api.Test;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.context.annotation.Import;
	import org.springframework.core.ParameterizedTypeReference;
	import org.springframework.test.web.servlet.client.RestTestClient;

	import java.util.List;


	// avoid port collision using a separate available port from OS
	@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
	@AutoConfigureRestTestClient
	@Import(TestcontainersConfiguration.class)
	class MovieApplicationTests {

		private static final Logger log = LoggerFactory.getLogger(MovieApplicationTests.class);

		// use template for REST calls to invoke this app actual endpoints
		@Autowired
		private RestTestClient template;

		@Test
		void contextLoads() {
		}

		@Test
		public void health(){
			var res = template
					.get()
					.uri("/actuator/health")
					.exchange()
					.expectStatus()
					.is2xxSuccessful();
		}

		@Test
		void getAllMovies(){
			var movies = movieFetch("/api/movies");
			Assertions.assertNotNull(movies);
			Assertions.assertEquals(6, movies.size());
		}

		@Test
		public void getMoviesByGenre(){
			var movies =  movieFetch("/api/movies/ACTION");
			Assertions.assertNotNull(movies);
			Assertions.assertEquals(3, movies.size());
			Assertions.assertTrue(movies
					.stream()
					.map(MovieDto::genre).allMatch(Genre.ACTION::equals));
		}

		private List<MovieDto> movieFetch(String uri){
			// used with template/testresttemplate - comprises full object for http request
			return this.template
					.get()
					.uri(uri)
					.exchange()
					.expectStatus()
					.is2xxSuccessful()
					.expectBody(new ParameterizedTypeReference<List<MovieDto>>(){})
					.value((list) -> log.info("response: {}", list))
					.returnResult()
					.getResponseBody();
		}
	}
