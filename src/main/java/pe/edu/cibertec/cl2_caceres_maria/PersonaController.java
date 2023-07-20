package pe.edu.cibertec.cl2_caceres_maria;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/* OK */
@Controller
@RequestMapping("/personas")
public class PersonaController {

    private PersonaRepository personaRepository;

    /* OK */
    PersonaController(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    /* OK */
    @GetMapping
    public String list(@RequestParam(required = false) String nombre, Model model) {
        List<Persona> personas;
        if (nombre != null && !nombre.isEmpty()) {
            personas = personaRepository.findByNombre(nombre);
        } else {
            personas = personaRepository.findAll();
        }
        model.addAttribute("superListadoo", personas);
        return "personas/list";
    }

    /* OK */
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("persona", new Persona());
        return "personas/create";
    }


    @PostMapping
    public String store(Persona persona) {
        personaRepository.save(persona);
        return "redirect:/personas";
    }


    @PostMapping("/update")
    public String update(Persona persona) {
        Optional<Persona> personaOptional = personaRepository.findById(persona.getId());
        if (personaOptional.isEmpty()) {
            return "error";
        }
        Persona personaOriginal = personaOptional.get();
        personaOriginal.setNombre(persona.getNombre());
        personaOriginal.setApellido(persona.getApellido());
        personaOriginal.setDni(persona.getDni());
        personaOriginal.setEdad(persona.getEdad());
        personaRepository.save(personaOriginal);
        return "redirect:/personas";
    }

    /* OK */
    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isEmpty()) {
            return "error";
        }
        model.addAttribute("persona", personaOptional.get());
        return "personas/detail";
    }

    /* OK */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isEmpty()) {
            return "error";
        }
        model.addAttribute("persona", personaOptional.get());
        return "personas/edit";
    }

    /* OK */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        personaRepository.deleteById(id);
        return "redirect:/personas";
    }


    @GetMapping("/gabriela")
    public String gabriela(Model model) {
        return "gabriela";
    }

}
