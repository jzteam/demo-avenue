package com.example.demoavenue.config;

import java.util.List;

//@ConfigurationProperties(prefix = "spring.datasource.manager")
public class TestProperties {

    private Person master;

    private List<Person> slaves;

    public Person getMaster() {
        return master;
    }

    public void setMaster(Person master) {
        this.master = master;
    }

    public List<Person> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<Person> slaves) {
        this.slaves = slaves;
    }
}
