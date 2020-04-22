package bruh;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class Factory {
    HashMap<String, String> operations;
    public Factory(String config) throws IOException {
        operations = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(config);
        Properties properties = new Properties();
        if (inputStream != null) {
            properties.load(inputStream);
        }
        for (String commandClass : properties.stringPropertyNames()) {
            String ourCommand = properties.getProperty(commandClass);
            operations.put(ourCommand, commandClass);
        }
    }
    Operation create(String ourCommand){
        Operation operation = null;
        try {
            String className = operations.get(ourCommand);
            System.out.println(">>>>" + className);
            Class<?> chooseClass = Class.forName(className);
            Constructor constructor = chooseClass.getDeclaredConstructor();
            operation = (Operation) constructor.newInstance();
        } catch (ClassNotFoundException error) {
            System.out.println("command wasn't created");
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException error){
            System.out.println("no method");
        } catch (NullPointerException error){
            System.out.println("oops");
            System.exit(1);
        }
        return operation;
    }
}
