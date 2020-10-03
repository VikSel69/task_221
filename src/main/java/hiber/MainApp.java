package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car car1 = new Car("УАЗ 469", 109344);
      addData(user1, car1, carService, userService);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car car2 =new Car("ВАЗ 2109", 346277);
      addData(user2, car2, carService, userService);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car car3 =new Car("ГАЗ 3102", 234188);
      addData(user3, car3, carService, userService);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getName() + " " + user.getCar().getSeries());
         System.out.println();
      }

      List <User> userWithCar = userService.getUsersWithCar(car1);
      System.out.println("Достаем юзера, владеющего машиной по ее модели и серии");
      System.out.println("Модель: " + car1.getName() + " Серия: " + car1.getSeries() + " Юзер: " + userWithCar.get(0).getFirstName());

      context.close();
   }

   public static void addData(User user, Car car, CarService carService, UserService userService) {
      car.setUser(user);
      user.setCar(car);
      carService.add(car);
      userService.add(user);
   }
}
