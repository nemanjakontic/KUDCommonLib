/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public interface IDomainObject extends Serializable{
    public String getTableName();
    
    public String getAllColumnNames();
    
    public String getValuesForInsert();
    
    public void setId(Long id);
    
    public String getKeyName();
    
    public String getKeyValue();
    
    public String getColumnNameAndValuesForUpdate();
    
    public List<IDomainObject> napraviListu(ResultSet rs) throws Exception;
    
    public IDomainObject napraviZaJednog(ResultSet rs) throws Exception;
    
    public String vratiKriterijum();

    public int vratiBrojVezanihObjekata();

    public int vratiBrojSlogovaVezanogObjekta(int j);

    public IDomainObject vratiSlogVezanogObjekta(int j, int i);

    public String vratiJoinUslov();

    public String vratiWhereUslov();

    public IDomainObject vratiVezaniObjekat(int j);

    public void setListuVezanih(List<? extends IDomainObject> listica, int j);

    public void obrisiTajJedan(int j);
}
