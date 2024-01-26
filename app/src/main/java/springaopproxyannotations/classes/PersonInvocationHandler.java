package springaopproxyannotations.classes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PersonInvocationHandler implements InvocationHandler{
    
    private final Person person;

    public PersonInvocationHandler(Person person1) {
        this.person = person1;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
       System.out.println("Hi!");
       return null;
    }

}
