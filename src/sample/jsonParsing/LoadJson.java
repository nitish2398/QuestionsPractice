package sample.jsonParsing;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ObjectFactory;

import java.io.InputStream;

public class LoadJson {
    public static void main(String [] arg) {
        InputStream inputStream = Model.class.getResourceAsStream("input.json");
    }
}
