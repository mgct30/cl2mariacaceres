package pe.edu.cibertec.cl2_caceres_maria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    /*
    @GetMapping("/algo")
    public String algo(Model model) {
        return "algo";
    }*/




}
