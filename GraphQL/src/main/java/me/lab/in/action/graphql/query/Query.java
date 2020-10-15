package me.lab.in.action.graphql.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Cacheable(value = "apiCache", keyGenerator = "simpleKeyGenerator")
public class Query implements GraphQLQueryResolver {

    public String firstQuery(){
        System.out.println("first");
        return "First Query.";
    }

    public String secondQuery(){
        System.out.println("second");
        return "Second Query";
    }

    public String fullNameQuery(@NotNull SampleRequest sampleRequest){
        return sampleRequest.getFirstName() + " " + sampleRequest.getLastName();
    }
}
