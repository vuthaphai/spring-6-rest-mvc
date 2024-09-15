package guru.springframework.spring_6_rest_mvc.controller;


import guru.springframework.spring_6_rest_mvc.model.Beer;
import guru.springframework.spring_6_rest_mvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {
    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity patchBeerById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){
        beerService.patchBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId){

        beerService.deleteBeerById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updatebyId(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){
        beerService.updateBeerById(beerId, beer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity handlePost(@RequestBody Beer beer){
        Beer saveBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new  HttpHeaders();
        headers.add("Location",BEER_PATH_ID + saveBeer.getId().toString());
        return new ResponseEntity(headers,HttpStatus.CREATED);
    }

   @GetMapping(value = BEER_PATH)
    public List<Beer> listBeers(){
        return beerService.listBeers();
    }


    @GetMapping(value = BEER_PATH_ID)
    public Beer getBeerById(@PathVariable("beerId") UUID id){
        log.debug("Get Beer by Id - in controller 1234");
        return beerService.getBeerById(id);
    }
}
