package com.sparktest;

import static com.sparktest.JsonUtil.json;
import static com.sparktest.JsonUtil.toJson;
import static spark.Spark.*;
import static spark.Spark.exception;

/**
 * Created by Balasubkadmin on 2/19/16.
 */
public class CompanyController {

    private static final String API_CONTEXT = "/api/v1";

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
        setupEndpoints();
    }

    private void setupEndpoints() {

        get(API_CONTEXT + "/companies", (req, res) -> companyService.getAllCompanies(), json());

        get(API_CONTEXT + "/companies/:id", (req, res) -> {
            String id = req.params(":id");
            Company company = companyService.getCompany(id);
            if (company != null) {
                return company;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id);
        }, json());

        post(API_CONTEXT + "/companies", (req, res) -> companyService.createCompany(req.body()), json());

        put(API_CONTEXT + "/companies", (req, res) -> companyService.updateCompany(req.body()), json());

        after((req, res) -> res.type("application/json"));

        exception(IllegalArgumentException.class, (e, req, res) -> {
            res.status(400);
            res.body(toJson(new ResponseError(e)));
        });
    }
}
