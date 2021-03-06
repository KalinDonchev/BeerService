package com.kalin.beerservice.bootstrap;

import com.kalin.beerservice.domain.Beer;
import com.kalin.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {

            beerRepository.save(Beer.builder()
                    .beerName("Zagorka")
                    .beerStyle("White")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.00"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Ariana")
                    .beerStyle("White")
                    .quantityToBrew(250)
                    .minOnHand(15)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("9.90"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Corona")
                    .beerStyle("White")
                    .quantityToBrew(300)
                    .minOnHand(40)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("9.90"))
                    .build());

        }
    }
}
