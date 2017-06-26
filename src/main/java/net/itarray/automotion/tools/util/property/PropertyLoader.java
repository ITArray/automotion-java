package net.itarray.automotion.tools.util.property;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyLoader {

    private String propertyFileName;

    public PropertyLoader(String propertyPath) {
        this.propertyFileName = propertyPath;
    }

    public String loadProperty(String name) {
        Properties props = new Properties();
        try {

            props.load(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/" + propertyFileName), "utf-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }
}