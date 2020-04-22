package bruh;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;

public class Factory {
    HashMap<String, String> operations;

    public Factory(String config) {
        operations = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream(config);
            Properties properties = new Properties();
            if (inputStream != null) {
                properties.load(inputStream);
            }
            for (String commandClass : properties.stringPropertyNames()) {
                String ourCommand = properties.getProperty(commandClass);
                operations.put(ourCommand, commandClass);
            }
        } catch (IOException error) {
            Calculator.logger.log(Level.SEVERE, "Config file wasn't found");
        }
        Calculator.logger.log(Level.INFO, "Factory was created!");
    }

    Operation load(String ourCommand) {
        Operation operation = null;
        try {
            String className = operations.get(ourCommand);
            Class<?> chooseClass = Class.forName(className);
            Constructor<?> constructor = chooseClass.getDeclaredConstructor();
            operation = (Operation) constructor.newInstance();
            Calculator.logger.log(Level.INFO, "Class " + className + " was loaded!");
        } catch (ClassNotFoundException error) {
            Calculator.logger.log(Level.SEVERE, "ClassNotFoundException" + error.getMessage());
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException error) {
            Calculator.logger.log(Level.SEVERE, error.getClass() + " " + error.getMessage());
        } catch (NoSuchMethodException error) {
            Calculator.logger.log(Level.SEVERE, "NoSuchMethodException: " + error.getMessage());
        } catch (NullPointerException error) {
            Calculator.logger.log(Level.INFO, "Turn off the application...");
            System.exit(1);
        }
        return operation;
    }
}
