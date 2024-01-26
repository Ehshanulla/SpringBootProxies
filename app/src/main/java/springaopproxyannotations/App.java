package springaopproxyannotations;

import java.lang.reflect.Proxy;

import springaopproxyannotations.cglib.CgClass;
import springaopproxyannotations.classes.Man;
import springaopproxyannotations.classes.Person;
import springaopproxyannotations.classes.PersonInvocationHandler;


public class App {

      public static void main(String[] args) {
       Man mohan = new Man("Mohan", 30, "Delhi", "India");
       ClassLoader mohanClassLoader = mohan.getClass().getClassLoader();
       Class[] interfaces = mohan.getClass().getInterfaces();
       Person proxymohan = (Person) Proxy.newProxyInstance(mohanClassLoader, interfaces, new PersonInvocationHandler(mohan));
       proxymohan.introduce(mohan.getName());
//       CgClass.main(args);
   }
}
