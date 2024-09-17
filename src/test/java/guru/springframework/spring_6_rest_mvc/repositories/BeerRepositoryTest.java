package guru.springframework.spring_6_rest_mvc.repositories;

import guru.springframework.spring_6_rest_mvc.entities.Beer;
import guru.springframework.spring_6_rest_mvc.model.BeerStyle;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeerNameTooLong(){

        assertThrows(ConstraintViolationException.class, () ->{
            Beer saveBeer = beerRepository.save(Beer.builder()
                    .beerName("Vp beer3234234234234234234444444444444444444444444444444444444444444444444444444444444444444442344444444444444444444444444444444444444444444444444444444444444444444444444")
                    .beerStyle(BeerStyle.PALE_ALE)
                    .upc("1312312312312")
                    .price(new BigDecimal("11.99"))
                    .build());
            beerRepository.flush();
        });


    }

    @Test
    void testSaveBeer(){
        Beer saveBeer = beerRepository.save(Beer.builder()
                        .beerName("Vp beer")
                        .beerStyle(BeerStyle.PALE_ALE)
                        .upc("1312312312312")
                        .price(new BigDecimal("11.99"))
                .build());
        beerRepository.flush();
        assertThat(saveBeer).isNotNull();
        assertThat(saveBeer.getId()).isNotNull();
    }

}