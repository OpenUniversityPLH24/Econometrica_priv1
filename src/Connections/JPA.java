/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connections;

import Database.Country;
import Database.CountryData;
import Database.CountryDataset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Μπορότης Βασίλειος
 * @author Ντουλάκης Ευστράτιος
 * @author Ντάφος Χρήστος
 */

public class JPA {


  public static EntityManager em ;
  public static EntityManagerFactory emf;
    
//    public static void main(String[] args) {
    public static void JPA() {
//        DBManager dbm = new DBManager();
//        dbm.DBManager();
        emf = DBManager.getEmf();
        em = DBManager.getEm();
//        em.getTransaction().begin();
        
//        EntityManager em = new EntityManager();
//       
//         
//        EntityManagerFactory emf = 
//            Persistence.createEntityManagerFactory("JPATestPU");
//        em = emf.createEntityManager();
//        // από εδώ αρχίζουν τα transactions
//        
//        removeCustomer(10);
//       
//        em.close();
//        emf.close();
    }
    
    public static Country retreiveCountry(String name){
    
    em = DBManager.getEm();
    Country c = new Country();
        try {
        c = em.createNamedQuery("Country.findByName",Country.class).setParameter("name", name).getSingleResult();
        } catch (Exception ex){
             System.out.println("Exception occurred " + ex.getMessage());
              c  = null;
        }
        return c;
    }

    public static Country retreiveCountryByISO(String ISO){
    
    em = DBManager.getEm();
    Country country = new Country();
        try {
//        System.out.println("name = " + name);
        country = em.createNamedQuery("Country.findByIsoCode",Country.class).setParameter("isoCode", ISO).getSingleResult();
//        System.out.println("Country ISO code = " + country.getIsoCode());
        return country;
        } catch (Exception ex){
             System.out.println("Exception occurred " + ex.getMessage() + "Lalakis1");
            return country;
        }
    }
    
    public static CountryDataset retreiveDataset(Country country, String type) {
    
//    em = DBManager.getEm();
    List<CountryDataset> lst = new ArrayList();
    CountryDataset dataset = new CountryDataset();
            try {
//        System.out.println("4th Point passed");
        lst = em.createNamedQuery("CountryDataset.findBycountryCode",CountryDataset.class).setParameter("countryCode", country).getResultList();
//        System.out.println("CountryDataset counts = " + lst.size());
        for (int i=0; i< lst.size(); i++) {
             if (lst.get(i).getName().contains(type)) {
                 dataset = lst.get(i);
                }
            }
        } catch (Exception ex){
             System.out.println("Exception occurred " + ex.getMessage() + "Lalakis2");
        }
//         System.out.println("CountryDataset Description = " + dataset.getDescription());   
        return dataset;
    }

    public static CountryDataLst retreiveDataLst(CountryDataset dataset) {
    
//    em = DBManager.getEm();
    CountryDataLst dataLst = new CountryDataLst();
    List lst = new ArrayList();
            try {
//        System.out.println("4th Point passed");
        lst = em.createNamedQuery("CountryData.findByDataset",CountryData.class).setParameter("dataset", dataset).getResultList();
//        System.out.println("CountryDataset counts = " + lst.size());
        for (int i=0; i < lst.size(); i++) {
             dataLst.CountryDataLstAdd((CountryData) lst.get(i));
        }
        } catch (Exception ex){
             System.out.println("Exception occurred " + ex.getMessage() + "Lalakis3");
        }
//         System.out.println("CountryDataset Description = " + dataset.getDescription());   
        return dataLst;
    }
        
    public static void createCountry( Country country ) {
        em = DBManager.getEm();
        em.getTransaction().begin();
        em.persist(country);
        em.flush();
        em.getTransaction().commit(); 
 //       em.close();
    }
    
    public static void createCountryDataset( CountryDataset countryDataset ) {
//        emf = DBManager.getEmf();
        em = DBManager.getEm();
        em.getTransaction().begin();
        
//        CountryDataset cd = new CountryDataset();
//        cd.setDescription(countryDataset.getDescription());
//        cd.setName(countryDataset.getName());
//        cd.setStartYear(countryDataset.getStartYear());
//        cd.setEndYear(countryDataset.getEndYear());
//        cd.setCountryCode(countryDataset.getCountryCode());

//        em.persist(cd);
        em.persist(countryDataset);
        em.flush();
        em.getTransaction().commit();
//        em.close();
    }
    
    public static void createCountryData( ArrayList<CountryData> countryDataLst ) {
//        em = DBManager.getEm();
        em.getTransaction().begin();
        for (int i=0; i< countryDataLst.size(); i++) {
        CountryData cd = new CountryData(countryDataLst.get(i).getDataYear(),
                                         countryDataLst.get(i).getValue(),
                                         countryDataLst.get(i).getDataset()
           );
        
        em.persist(cd); 
        em.flush();
        }
        em.getTransaction().commit();
//        em.close();
    }
    
    public static void createCountryDataInd( CountryData countryData ) {
        em = DBManager.getEm();
        em.getTransaction().begin();
        CountryData cd = new CountryData(countryData.getDataYear(),
                                         countryData.getValue(),
                                         countryData.getDataset()
        );
        em.persist(cd);
        em.flush();
        em.getTransaction().commit(); 
//        em.close();
    }
    
    public static void removeAllCountries() {
        em = DBManager.getEm();
        em.getTransaction().begin();
        em.createNamedQuery("CountryData.deleteAll").executeUpdate();
        em.createNamedQuery("CountryDataset.deleteAll").executeUpdate();
        em.createNamedQuery("Country.deleteAll").executeUpdate();
        em.createNativeQuery("ALTER TABLE Country_Data ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

//    private static Object setParameter(String countryCode, String code) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
