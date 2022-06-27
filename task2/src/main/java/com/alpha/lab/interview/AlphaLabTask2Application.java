package com.alpha.lab.interview;

import com.alpha.lab.interview.named.NamedObject;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlphaLabTask2Application {

    public static void main(String[] args) {
        EasyRandomParameters parameters = new EasyRandomParameters()
                .scanClasspathForConcreteTypes(true);
        EasyRandom generator = new EasyRandom(parameters);
        List<NamedObject> namedObjects = generator
                .objects(NamedObject.class, 25)
                .collect(Collectors.toList());

        Map<String, List<NamedObject>> result = new Grouper().groupByName(namedObjects);
        System.out.println(result);
    }

}
