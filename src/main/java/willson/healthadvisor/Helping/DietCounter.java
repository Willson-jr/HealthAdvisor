package willson.healthadvisor.Helping;

import lombok.Data;
import lombok.NoArgsConstructor;
import willson.healthadvisor.Model.Diet;
import willson.healthadvisor.Model.User;

@Data
@NoArgsConstructor
public class DietCounter {
        public int caculateNormUsual(User user){
        double norm = 0;
        if (user.getSex()) norm = ((10*user.getWeight())+(6.25*user.getHeight())-(5*user.getAge())+5)*user.getActivity().getStrength();
        else norm = ((10*user.getWeight())+(6.25*user.getHeight())-(5*user.getAge())-161)*user.getActivity().getStrength();
        int need = (int)norm;
        return need;
    }
    public int calclateNormToLose (User user){
        double norm = 0;
        if (user.getSex()) norm = (((10*user.getWeight())+(6.25*user.getHeight())-(5*user.getAge())+5)*user.getActivity().getStrength())*0.75;
        else norm = (((10*user.getWeight())+(6.25*user.getHeight())-(5*user.getAge())-161)*user.getActivity().getStrength())*0.75;
        int need = (int)norm;
        return need;
    }
    public int leftToConsume(User user, Integer num){
            int number = calclateNormToLose(user) - num;
            return number;
        }
    public int calculateWaterToConsume(User user){
            int number  = user.getWeight()*30;
            return number;
    }

}
