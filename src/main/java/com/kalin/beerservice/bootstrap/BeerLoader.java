package com.kalin.beerservice.bootstrap;

import com.kalin.beerservice.domain.Beer;
import com.kalin.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

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
                    .upc(337010000001L)
                    .price(new BigDecimal("12.00"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Ariana")
                    .beerStyle("White")
                    .quantityToBrew(250)
                    .minOnHand(15)
                    .upc(337010000002L)
                    .price(new BigDecimal("9.90"))
                    .build());
        }
    }
}
