package xyz.javaman.taco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.data.UserRepository;
import xyz.javaman.taco.entities.Ingredient;
import xyz.javaman.taco.entities.User;

import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaRepositories
public class SpringInActionTacoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringInActionTacoApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

	@Bean
	public CommandLineRunner dataLoader(final IngredientRepository repo, final UserRepository ur, final PasswordEncoder pe) {
		return args -> {
			Stream.of(
					new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
					new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
					new Ingredient("GRBG", "Ground Beef", Ingredient.Type.PROTEIN),
					new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
					new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
					new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
					new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
					new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
					new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
					new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
			).forEach(repo::save);
			User u = new User(null, "admin", pe.encode("admin"), null, null, null, null, null, null);
			ur.save(u);
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsService userDetailsService(UserRepository userRepository) {
		/*
		List<UserDetails> usersList = new ArrayList<>();
		usersList.add(new User("buzz", passwordEncoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		usersList.add(new User("woody", passwordEncoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
		return new InMemoryUserDetailsManager(usersList);
		 */
		return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
	}
}
