package guru.springframework.spring_6_rest_mvc.mappers;

import guru.springframework.spring_6_rest_mvc.entities.Beer;
import guru.springframework.spring_6_rest_mvc.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    Beer beerDtoToBeer(BeerDTO dto);

    BeerDTO beerToBeerDto(Beer beer);

}
