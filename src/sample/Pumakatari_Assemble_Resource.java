package sample;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class Pumakatari_Assemble_Resource implements ResourceAssembler<Pumakatari, Resource<Pumakatari>> {
    @Override
    public Resource<Pumakatari> toResource(Pumakatari bus) {

        return new Resource<>(bus,
                linkTo(methodOn(Pumakatari_Controlador.class).one(bus.getId())).withSelfRel(),
                linkTo(methodOn(Pumakatari_Controlador.class).all()).withRel("Pumakatari"));
    }
}
