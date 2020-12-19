package sample.Problems.JsonParser;

import com.sun.deploy.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Customer extends MainClass {
    String name;
    Integer id;
    Integer phone;

    public Customer() {
        this.name = "";
        this.id = 0;
        this.phone = 0;
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
