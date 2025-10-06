package springaopproxyannotations;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
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
       
       //CgClass.main(args);

       Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Man.class); // target class

        // Step 2: Set the interceptor (method interceptor)
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("[Before] Calling method: " + method.getName());
                Object result = proxy.invokeSuper(obj, args); // call the original method
                System.out.println("[After] Finished method: " + method.getName());
                return result;
            }

        });

        // Step 3: Create a proxied object (like a subclass instance)
        Man proxiedMan = (Man) enhancer.create(
                new Class[] { String.class, int.class, String.class, String.class },
                new Object[] { "Mohan", 30, "Delhi", "India" }
        );

        // Step 4: Call the method on the proxy
        proxiedMan.introduce(proxiedMan.getName());
   }
}
