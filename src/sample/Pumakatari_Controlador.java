package sample;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class Pumakatari_Controlador {
    private final Pumakatari_Repositorio repository;

    private final Pumakatari_Assemble_Resource assembler;

    Pumakatari_Controlador(Pumakatari_Repositorio repository, Pumakatari_Assemble_Resource assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }
    @PostMapping("/buses")
    ResponseEntity<?> newEmployee(@RequestBody Pumakatari newEmployee) throws URISyntaxException {

        Resource<Pumakatari> resource = assembler.toResource(repository.save(newEmployee));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);
    }

    // Single item

    // se cambio one por ones

    @GetMapping("/buses/{id}")
    Resource<Pumakatari> ones(@PathVariable Long id) {

        Pumakatari employee = repository.findById(id)
                .orElseThrow(() -> new BusNotFoundException(id));

        return assembler.toResource(employee);
    }

    @PutMapping("/buses/{id}")
    Buses replaceEmployee(@RequestBody Pumakatari newBus, @PathVariable Long id) {

        return repository.findById(id)
                .map(bus -> {
                    bus.setName_rute(newBus.getName_rute());
                    return repository.save(bus);
                })
                .orElseGet(() -> {
                    newBus.setId(id);
                    return repository.save(newBus);
                });
    }
}
