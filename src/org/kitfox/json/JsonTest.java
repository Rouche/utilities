/**
 *
 */
package org.kitfox.json;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author larj15
 */
public class JsonTest {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setBirth(Date.from(LocalDate.of(1960, 01, 01).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        person.setDumb(false);
        person.setMoney(new BigDecimal(100_000));
        person.setName("\" \"test\" : John Snow { aaa : bbb }");

        Adress adress = new Adress();
        adress.setNumber(10);

        person.addAdress(adress);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        String jSon = mapper.writeValueAsString(person);

        System.out.println(jSon);

        Person2 person2 = mapper.readValue(jSon, Person2.class);

        System.out.println(new ReflectionToStringBuilder(person2).toString());

        jSon = mapper.writeValueAsString(person2);

        person = mapper.readValue(jSon, Person.class);

        System.out.println(new ReflectionToStringBuilder(person).toString());
    }
}
