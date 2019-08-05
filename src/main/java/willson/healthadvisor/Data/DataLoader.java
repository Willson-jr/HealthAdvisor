package willson.healthadvisor.Data;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import willson.healthadvisor.Controller.UserController;
import willson.healthadvisor.Helping.DietCounter;
import willson.healthadvisor.Model.Diet;
import willson.healthadvisor.Model.User;
import willson.healthadvisor.Repository.DietRepository;
import willson.healthadvisor.Repository.UserRepository;

import static willson.healthadvisor.Helping.Activity.*;

@Component
public class DataLoader  implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DietRepository dietRepository;

    private DietCounter dietCounter = new DietCounter();

    public Diet createDiet(User user){
        Diet diet = new Diet();
        diet.setUser(user);
        diet.setId(user.getId());
        diet.setNormToLoseKcal(dietCounter.calclateNormToLose(user));
        diet.setNormUsualKcal(dietCounter.caculateNormUsual(user));
        diet.setConsumed(0);
        diet.setLeftToConsume(diet.getNormToLoseKcal());
        diet.setWaterToDrink(dietCounter.calculateWaterToConsume(user));
        diet.setWaterDrunk(0);
        diet.setWaterLeftToDrink(diet.getWaterToDrink());
        return diet;
    }

    @Override
    public void run (String... args) throws Exception{
        if (userRepository.count()==0){
            User user = new User("Pawel",90,177,29,true,MINIMUM);
            userRepository.save(user);
            dietRepository.save(createDiet(user));

            user = new User("Yana",70,175,28,false,INTENSIVEFIFTH);
            userRepository.save(user);
            dietRepository.save(createDiet(user));
            user = new User("Yuri",100,170,31,true, MINIMUM);
            userRepository.save(user);
            dietRepository.save(createDiet(user));
            user = new User("Tatsiana",70,180,36,false,TRIPLE);
            userRepository.save(user);
            dietRepository.save(createDiet(user));


        }

    }
}
