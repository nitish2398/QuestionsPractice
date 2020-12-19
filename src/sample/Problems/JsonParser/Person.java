package sample.Problems.JsonParser;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Person extends MainClass {
    Customer customer;

    public Person() {
        this.customer = new Customer();
        this.updateDataTypes();
    }

    @Override
    public void updateDataTypes() {

        Map<String, String> attributes = new HashMap<>();

        Field[] fields = this.getClass().getFields();
        for(Field field: fields) {
            attributes.put(field.getName(), field.getType().getName());
        }

        ClassInfo.classInfo.put(this.getClass().getName(), attributes);
    }
}
