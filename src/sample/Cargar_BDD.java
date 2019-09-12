package sample;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration


@Slf4j
class Cargar_BDD {

    Logger log = LoggerFactory.getLogger (Nominas.class);
    @Bean
    CommandLineRunner initDatabase(Pumakatari_Repositorio repositoryBus, Orden_Repositorio repositoryOrden) {
        return args -> {

            log.info("PPPPPPPPPPPP " + repositoryBus.save(new Buses( "IncaLlojeta")));

            repositoryOrden.save(new Orden("6 de Agosto", Orden_Status.COMPLETED));
            repositoryOrden.save(new Orden("Achumani", Orden_Status.IN_PROGRESS));

            repositoryOrden.findAll().forEach(order -> {
                log.info("PPPPPPPPPP " + order);
            });

        };

    }
}