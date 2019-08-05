package willson.healthadvisor.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import willson.healthadvisor.Data.DataLoader;
import willson.healthadvisor.Helping.Activity;
import willson.healthadvisor.Model.Diet;
import willson.healthadvisor.Helping.DietCounter;
import willson.healthadvisor.Model.User;
import willson.healthadvisor.Repository.DietRepository;
import willson.healthadvisor.Repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DietRepository dietRepository;
    @Autowired
    private DataLoader dataLoader;

    @GetMapping("/")
    public String home(ModelMap map){
        map.put("user", userRepository.findAll());
        map.put("user",new User());
        return "home";
    }
    private DietCounter dietCounter = new DietCounter();

    @PostMapping("/users")
    public String create(@ModelAttribute User user){
        userRepository.save(user);
        dietRepository.save(dataLoader.createDiet(user));
        return "redirect:/all";
    }

//    Diet createDiet(User user){
//        Diet diet = new Diet();
//        diet.setUser(user);
//        diet.setId(user.getId());
//        diet.setNormToLoseKcal(dietCounter.calclateNormToLose(user));
//        diet.setNormUsualKcal(dietCounter.caculateNormUsual(user));
//        diet.setConsumed(0);
//        diet.setLeftToConsume(diet.getNormToLoseKcal());
//        diet.setWaterToDrink(dietCounter.calculateWaterToConsume(user));
//        diet.setWaterDrunk(0);
//        return diet;
//    }

    @GetMapping("/all")
    public String all(ModelMap map){
        map.put("users",userRepository.findAll());
        return "all";
    }

    @GetMapping("/users/{id}/destroy")
    public String destroy(@PathVariable Integer  id, ModelMap map) {
        userRepository.deleteById(id);
        return "redirect:/all";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable Integer id,ModelMap map){
        map.put("user",userRepository.findById(id));

        return "home";
    }



}
