/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import Database.CountryData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Μπορότης Βασίλειος
 * @author Ντουλάκης Ευστράτιος
 * @author Ντάφος Χρήστος
 */

public class CountryDataLst {
    
private List<CountryData> lst = new ArrayList();

public CountryDataLst () {     
}

public CountryDataLst(CountryData cData) {
    this.lst.add(cData);
 }

public void CountryDataLstAdd(CountryData cData) {
    this.lst.add(cData);
}

public void CountryDataLstRem(CountryData cData) {
    this.getLst().remove(getLst());
}

    /**
     * @return the lst
     */
    public List<CountryData> getLst() {
        return lst;
    }

    /**
     * @param lst the lst to set
     */
    public void setLst(List<CountryData> lst) {
        this.lst = lst;
    }

}
