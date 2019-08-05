package willson.healthadvisor.Repository;

import org.springframework.data.repository.CrudRepository;
import willson.healthadvisor.Model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}
