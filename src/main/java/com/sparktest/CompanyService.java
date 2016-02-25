package com.sparktest;

import com.google.gson.Gson;

import java.util.*;

/**
 * Created by Balasubkadmin on 2/19/16.
 */
public class CompanyService {
    public static final Map<String, Company> companies = new HashMap<>();

    public static List<Company> getAllCompanies() {
        return new ArrayList<>(companies.values());
    }

    public static Company getCompany(String id) {
        return companies.get(id);
    }

    public static Company createCompany(String body) {
        Company company = new Gson().fromJson(body, Company.class);
        failIfInvalid(company);
        company.setId(UUID.randomUUID().toString());
        companies.put(company.getId(), company);
        return company;
    }

    public static Company updateCompany(String body) {
        Company reqCompany = new Gson().fromJson(body, Company.class);
        Company company = companies.get(reqCompany.getId());
        if (company == null) {
            throw new IllegalArgumentException("No user with the given id found");
        }
        failIfInvalid(reqCompany);
        company.setName(reqCompany.getName());
        company.setAddress(reqCompany.getAddress());
        company.setCity(reqCompany.getCity());
        company.setCountry(reqCompany.getCountry());
        company.setEmail(reqCompany.getEmail());
        company.setPhone(reqCompany.getPhone());
        company.setEmployees(reqCompany.getEmployees());
        companies.put(company.getId(), company);

        return company;
    }

    private static void failIfInvalid(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Parameters cannot be empty");
        } else {
            if(company.getName() == null) {
                throw new IllegalArgumentException("Parameter 'name' cannot be empty");
            } else if(company.getAddress() == null) {
                throw new IllegalArgumentException("Parameter 'address' cannot be empty");
            } else if(company.getCity() == null) {
                throw new IllegalArgumentException("Parameter 'city' cannot be empty");
            } else if(company.getCountry() == null) {
                throw new IllegalArgumentException("Parameter 'country' cannot be empty");
            } else if(company.getEmployees() == null) {
                throw new IllegalArgumentException("Parameter 'employees' cannot be empty");
            }
        }

    }
}
