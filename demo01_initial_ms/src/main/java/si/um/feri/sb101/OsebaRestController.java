package si.um.feri.sb101;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("osebe")
public class OsebaRestController {

    private static final Logger log = Logger.getLogger(OsebaRestController.class.toString());

    private static Map<String,Oseba> osebe= Collections.synchronizedMap(new HashMap<String, Oseba>());

    @GetMapping()
    public Collection<Oseba> getOseba() {
        log.info("Get osebe");
        return osebe.values();
    }

    @GetMapping("/{email}")
    public Oseba getOseba(@PathVariable String email) {
        log.info("Get osebe z email "+email);
        return osebe.get(email);
    }

    @PostMapping
    public Oseba postOseba(@RequestBody Oseba oseba) {
        log.info("Dodajanje nove osebe "+oseba);
        return osebe.put(oseba.getEmail(),oseba);
    }

}
