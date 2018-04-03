package classes;

import org.xeustechnologies.jcl.JarClassLoader;
import org.xeustechnologies.jcl.JclObjectFactory;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class Loader {
    public void loadLibrary(String filename) throws Exception
    {
        JarClassLoader jcl = new JarClassLoader();
        jcl.add("myjar.jar"); // Load jar file
        jcl.add(new URL("http://myserver.com/myjar.jar")); // Load jar from a URL
        jcl.add(new FileInputStream("myotherjar.jar")); // Load jar file from stream
        jcl.add("myclassfolder/"); // Load class folder
        jcl.add("myjarlib/"); // Recursively load all jar files in the folder/sub-folder(s)

        JclObjectFactory factory = JclObjectFactory.getInstance();
// Create object of loaded class
        Object obj = factory.create(jcl, "mypackage.MyClass");
    }
}
