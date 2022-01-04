package com.hust.tacosapi.api;


import com.hust.tacosapi.data.TacoRepository;
import com.hust.tacosapi.entity.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/design",produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

    private TacoRepository tacoRepo;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

//    @GetMapping("/recent")
//    public Iterable<Taco> recentTacos(){
//        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
//        return tacoRepo.findAll(page).getContent();
//    }
    @GetMapping("/recent")
    public CollectionModel<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        List<Taco> tacos = tacoRepo.findAll(page).getContent();

        CollectionModel<TacoResource> tacoResources = new TacoResourceAssembler().toCollectionModel(tacos);

        tacoResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos())
                .withRel("recents"));

        return tacoResources;
    }

//    @GetMapping("/{id}")
//    public Taco tacoById(@PathVariable Long id){
//        Optional<Taco> optTaco = tacoRepo.findById(id);
//        if(optTaco.isPresent()){
//            return optTaco.get();
//        }
//        return null;
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable Long id){
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if(optTaco.isPresent()){
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
