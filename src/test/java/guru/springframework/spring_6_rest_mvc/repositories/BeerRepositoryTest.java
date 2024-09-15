package guru.springframework.spring_6_rest_mvc.repositories;

import guru.springframework.spring_6_rest_mvc.entities.Beer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BeerRepositoryTest {

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testSaveBeer(){
        Beer saveBeer = beerRepository.save(Beer.builder()
                        .beerName("Vp beer")
                .build());
        assertThat(saveBeer).isNotNull();
        assertThat(saveBeer.getId()).isNotNull();
    }

}