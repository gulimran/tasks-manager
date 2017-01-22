package imran.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class Application extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(ModuleFactory.getModules());
    }
}
