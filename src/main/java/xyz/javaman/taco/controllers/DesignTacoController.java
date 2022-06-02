package xyz.javaman.taco.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import xyz.javaman.taco.data.IngredientRepository;
import xyz.javaman.taco.data.TacoRepository;
import xyz.javaman.taco.entities.Taco;
import xyz.javaman.taco.entities.TacoOrder;
import xyz.javaman.taco.entities.TacoUDT;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        model.addAllAttributes(stream(ingredientRepository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(item -> item.getType().toString().toLowerCase()))
        );
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        if (tacoOrder.getTacos() == null) {
            tacoOrder.setTacos(new ArrayList<>());
        }
        tacoOrder.getTacos().add(new TacoUDT(taco.getName(), taco.getIngredients()));
        tacoRepository.save(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }
}
