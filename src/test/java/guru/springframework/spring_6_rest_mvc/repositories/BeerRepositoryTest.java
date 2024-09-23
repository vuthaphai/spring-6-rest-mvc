package guru.springframework.spring_6_rest_mvc.repositories;

import guru.springframework.spring_6_rest_mvc.bootstrap.BootstrapData;
import guru.springframework.spring_6_rest_mvc.entities.Beer;
import guru.springframework.spring_6_rest_mvc.model.BeerStyle;
import guru.springframework.spring_6_rest_mvc.services.BeerCsvServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BootstrapData.class, BeerCsvServiceImpl.class})
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testGetBeerListByName(){
        List<Beer> beerList = beerRepository.findAllByBeerNameIsLikeIgnoreCase("%IPA%");

        assertThat(beerList.size()).isEqualTo(336);
    }

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