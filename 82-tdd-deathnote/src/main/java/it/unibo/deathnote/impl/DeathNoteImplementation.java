package it.unibo.deathnote.impl;

import java.util.LinkedList;
import java.util.List;
import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImplementation implements DeathNote {

    public class name {
        private final String personName;
        private String deathCause = "heart attack";
        private String details = null;

        public name(String personName) {
            this.personName = personName;
        }

        public String getPersonName() {
            return personName;
        }
        public String getDeathCause() {
            return deathCause;
        }
        public String getDetails() {
            return details;
        }
    }

    public List<name> names = new LinkedList<>();
    private long time = System.currentTimeMillis();
    private int timeLimit2 = 6040;
    private int timeLimit1 = 40;

    public String getRule(int ruleNumber) {
        if (ruleNumber >= 1 && ruleNumber < 13) {
        return RULES.get(ruleNumber);
        }
        throw new IllegalArgumentException("Invalid rule number!");
    }

    public void writeName(String name){
        if (name != null) {
            names.add(new name(name));
            time = System.currentTimeMillis();
        } else {
            throw new NullPointerException("Error: blank name!");
        }
    }

    public boolean writeDeathCause(String cause) {
        if (cause != null && !names.isEmpty()) {
            if ((System.currentTimeMillis() - time) < timeLimit1) {
                names.getLast().deathCause = cause;
                return true;
            } else {
                return false;
        }
        }
        throw new IllegalStateException("Death Note or death cause is empty");
    }

    public boolean writeDetails(String details) {
        if (details != null && !names.isEmpty()) {
            if ((System.currentTimeMillis() - time) < timeLimit2) {
                names.getLast().details = details;
                return true;
            } else {
                return false;
        }
        }
        throw new IllegalStateException("Death Note or death details are empty");
    }

    public String getDeathCause(String name) {
        for (name n : names) {
            if (n.personName == name) {
                if (n.deathCause != null) {
                    return n.deathCause;
                } else {
                    return "heart attack";
                }
            }
        }
        throw new IllegalArgumentException(name + " is not present in the death note!");
    }

    public String getDeathDetails(String name) {
        for (name n : names) {
            if (n.personName == name) {
                if (n.details != null) {
                    return n.details;
                } else {
                    return "";
                }
            }
        }
        throw new IllegalArgumentException(name + " is not present in the death note!");
    }

    public boolean isNameWritten(String name) {
        if (name != null) {
        for(name n : names) {
            if (n.personName == name) {
                return true;
            }  
        }
        return false;
        }
        throw new IllegalArgumentException("Error: blank name!");
    }
}