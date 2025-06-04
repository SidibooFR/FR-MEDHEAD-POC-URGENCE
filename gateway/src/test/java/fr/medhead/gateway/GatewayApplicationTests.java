package fr.medhead.gateway;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import fr.medhead.gateway.security.JwtUtil;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class GatewayApplicationTests {

	@Autowired
	private JwtUtil jwtUtil;

	@Test
	void generateToken_ShouldNotBeNullOrEmpty() {
		String token = jwtUtil.generateToken("testuser");
		assertNotNull(token, "Generated token must not be null or empty");
	}

	@Test
	void extractUsername_ShouldReturnCorrectUsername() {
		String token = jwtUtil.generateToken("testuser");
		Mono<String> usernameMono = jwtUtil.extractUsername(token);

		StepVerifier.create(usernameMono)
				.expectNext("testuser")  // Simplified assertion
				.verifyComplete();       // Correct verification method
	}

	@Test
	void extractUsername_InvalidToken_ShouldThrowException() {
			String invalidToken = "invalid.token";
			Mono<String> usernameMono = jwtUtil.extractUsername(invalidToken);

		StepVerifier.create(usernameMono)
			.expectError()           // Expect any error
			.verify();               // Complete verification
	}
}