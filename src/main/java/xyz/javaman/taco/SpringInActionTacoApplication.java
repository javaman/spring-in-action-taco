package xyz.javaman.taco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.entities.Ingredient;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJdbcRepositories
public class SpringInActionTacoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringInActionTacoApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

	@Bean
	public CommandLineRunner dataLoader(final IngredientRepository repo) {
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
			).peek(new Consumer<Ingredient>() {
				@Override
				public void accept(Ingredient i) {
					i.setNew(true);
				}
			}).forEach(repo::save);
		};
	}
}
