package com.sparktest;

import static spark.SparkBase.staticFileLocation;
import static spark.Spark.*;

public class Bootstrap {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        new CompanyController(new CompanyService());
    }
    
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}