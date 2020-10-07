package me.lab.in.action.web.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    public String firstQuery(){
        return "First Query";
    }

    public String secondQuery(){
        return "Second Query";
    }
}
