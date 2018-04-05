package classes;

import daointerfaces.ModuleInterface;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

class Loader {
    ModuleInterface loadLibrary() throws Exception
    {
        File dir = new File(System.getProperty("user.dir") + "\\driver\\");
        if(!dir.isDirectory())
            throw new Exception("Dir not exist");
        dir = dir.listFiles()[0];

        URL loadPath = dir.toURI().toURL();
        URL[] classUrl = new URL[]{loadPath};
        ClassLoader cl = new URLClassLoader(classUrl);
        Class loadedClass = cl.loadClass("classes.Module"); // must be in package.class name format
        return (ModuleInterface)loadedClass.newInstance();
    }
}
