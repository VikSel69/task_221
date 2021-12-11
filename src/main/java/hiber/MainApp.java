package hiber;

import  hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("car1", 1);
      Car car2 = new Car("car2", 2);
      Car car3 = new Car("car3", 3);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);
      User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
      User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      // возвращаем пользователей с машинами
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      // возвращаем юзера по модели и серии машины
      try {
         System.out.println(userService.getUserByCar("car2", 2));
      } catch (NoResultException e) {
         System.out.println("Пользователя с такой машиной нет");
      }

      context.close();
   }

}
