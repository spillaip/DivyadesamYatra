package com.eclass.vaishnavism.divyadesamyatra;

import java.util.Date;

public class Divyadesam {
    String divyadesam;
    String dateVisited;

    public Divyadesam(){};

    public Divyadesam(String divyadesam, String dateVisited) {
        this.divyadesam = divyadesam;
        this.dateVisited = dateVisited;
    }

    public String getDivyadesam() {
        return divyadesam;
    }

    public void setDivyadesam(String divyadesam) {
        this.divyadesam = divyadesam;
    }

    public String getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(String dateVisited) {
        this.dateVisited = dateVisited;
    }
}
