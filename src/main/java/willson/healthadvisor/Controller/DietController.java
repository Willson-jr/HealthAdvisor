package willson.healthadvisor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import willson.healthadvisor.Helping.DietCounter;
import willson.healthadvisor.Model.Diet;
import willson.healthadvisor.Model.User;
import willson.healthadvisor.Repository.DietRepository;
import willson.healthadvisor.Repository.UserRepository;

@Controller
public class DietController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DietRepository dietRepository;
    /*
    Metoda do pokazywania uzytkownika;
     */
    @GetMapping("/users/{id}")
    public String show(@PathVariable Integer id, ModelMap map) {
        User user = userRepository.findById(id).get();
        Diet diet = dietRepository.findById(id).get();
        diet.setLeftToConsume(diet.getNormToLoseKcal()-diet.getConsumed());
        map.put("user", user);
        map.put("diet", diet);
        return "user";
    }
    /*
    Metoda do "spozywania" jedzenia
     */
    @GetMapping("/users/{id}/consumefood")
    public String consumefood(@PathVariable Integer id, @RequestParam Integer consumed,ModelMap map){
        User user = userRepository.findById(id).get();
        Diet diet = dietRepository.findById(id).get();
        int allConsumed = diet.getConsumed();
        allConsumed += consumed;
        diet.setConsumed(allConsumed);
        diet.setLeftToConsume(diet.getNormToLoseKcal()-diet.getConsumed());
        return saveOnMap(map, user, diet);
    }
    /*
    Metoda do "spozywania" wody
     */
    @GetMapping("/users/{id}/consumewater")
    public String consumewater(@PathVariable Integer id, @RequestParam Integer waterDrunk,ModelMap map){
        User user = userRepository.findById(id).get();
        Diet diet = dietRepository.findById(id).get();
        int allConsumed = diet.getWaterDrunk();
        allConsumed += waterDrunk;
        diet.setWaterDrunk(allConsumed);
        diet.setWaterLeftToDrink(diet.getWaterToDrink()-diet.getWaterDrunk());
        return saveOnMap(map, user, diet);
    }
    /*
    Metoda do resetowania ilosci spozytego jedzenia
     */
    private DietCounter dietCounter = new DietCounter();
    @GetMapping("/users/{id}/resetfood")
    public String resetfood(@PathVariable Integer id,ModelMap map){
        User user = userRepository.findById(id).get();
        Diet diet = dietRepository.findById(id).get();
        diet.setNormToLoseKcal(dietCounter.calclateNormToLose(user));
        diet.setNormUsualKcal(dietCounter.caculateNormUsual(user));
        diet.setConsumed(0);
        diet.setLeftToConsume(diet.getNormToLoseKcal());
        return saveOnMap(map, user, diet);
    }
    /*
    Metoda do resetowania ilosci spozytej wody
     */
    @GetMapping("/users/{id}/resetwater")
    public String resetwater(@PathVariable Integer id,ModelMap map){
        User user = userRepository.findById(id).get();
        Diet diet = dietRepository.findById(id).get();
        diet.setWaterToDrink(dietCounter.calculateWaterToConsume(user));
        diet.setWaterDrunk(0);
        return saveOnMap(map, user, diet);
    }

    private String saveOnMap(ModelMap map, User user, Diet diet) {
        userRepository.save(user);
        dietRepository.save(diet);
        map.put("user", user);
        map.put("diet", diet);
        return "user";
    }

}
