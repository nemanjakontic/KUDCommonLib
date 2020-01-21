/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enumeracije.Ansambl;
import domain.enumeracije.Pol;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author Nemanja
 */
public class Clan implements Serializable, IDomainObject{
    private Long brojCK;
    private String ime;
    private String prezime;
    private Pol pol;
    private double visina;
    private java.util.Date datumRodjenja;
    private java.util.Date datumUclanjenja;
    private double brojObuce;
    private Ansambl ansambl;
    private boolean aktivan;

    public Clan() {
    }

    public Clan(Long brojCK, String ime, String prezime, Pol pol, double visina, Date datumRodjenja, Date datumUclanjenja, double brojObuce, Ansambl ansambl, boolean aktivan) {
        this.brojCK = brojCK;
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.visina = visina;
        this.datumRodjenja = datumRodjenja;
        this.brojObuce = brojObuce;
        this.ansambl = ansambl;
        this.datumUclanjenja = datumUclanjenja;
        this.aktivan = aktivan;
    }

    public Long getBrojCK() {
        return brojCK;
    }

    public void setBrojCK(Long brojCK) {
        this.brojCK = brojCK;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public double getVisina() {
        return visina;
    }

    public void setVisina(double visina) {
        this.visina = visina;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public double getBrojObuce() {
        return brojObuce;
    }

    public void setBrojObuce(double brojObuce) {
        this.brojObuce = brojObuce;
    }

    public Ansambl getAnsambl() {
        return ansambl;
    }

    public void setAnsambl(Ansambl ansambl) {
        this.ansambl = ansambl;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Clan other = (Clan) obj;
        if (!Objects.equals(this.brojCK, other.brojCK)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "clan";
    }

    @Override
    public String getAllColumnNames() {
        return "ime, prezime, pol, visina, datumRodjenja, brojObuce, ansambl, datumUclanjenja, aktivan";
    }

    @Override
    public String getValuesForInsert() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("'").append(getIme()).append("'").append(",")
                .append("'").append(getPrezime()).append("'").append(",")
                .append("'").append(getPol()).append("'").append(",")
                .append(getVisina()).append(",")
                .append("'").append(new java.sql.Date(getDatumRodjenja().getTime())).append("'").append(",")
                .append(getBrojObuce()).append(",")
                .append("'").append(getAnsambl()).append("'").append(",")
                .append("'").append(new java.sql.Date(getDatumUclanjenja().getTime())).append("'").append(",")
                .append(isAktivan());
        
        String values = sb.toString();
        return values;
    }

    @Override
    public void setId(Long id) {
        this.brojCK = id;
    }

    @Override
    public String getKeyName() {
        return "brojCK";
    }

    @Override
    public String getKeyValue() {
        return getBrojCK() + "";
    }

    @Override
    public String getColumnNameAndValuesForUpdate() {
        return "ime = '" +getIme()+ "', prezime = '" +getPrezime()+ 
                "', pol = '" +getPol()+ "', visina = "+getVisina()+ 
                ", datumRodjenja = '"+new java.sql.Date(getDatumRodjenja().getTime())+ 
                "', brojObuce = " +getBrojObuce()+ ", ansambl = '" +getAnsambl()+ 
                "', datumUclanjenja = '" +new java.sql.Date(getDatumUclanjenja().getTime())+ "', aktivan = "+isAktivan(); 
                
    }

    @Override
    public List<IDomainObject> napraviListu(ResultSet rs) throws Exception{
        List<IDomainObject> clanovi = new ArrayList<>();
        while(rs.next()){
            Long brojCK = rs.getLong("brojCK");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            boolean aktivan = rs.getBoolean("aktivan");
            
            Clan clan = new Clan();
            clan.setBrojCK(brojCK);
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setAnsambl(ansambl);
            clan.setAktivan(aktivan);
            
            clanovi.add(clan);
        }
        return clanovi;
    }

    @Override
    public IDomainObject napraviZaJednog(ResultSet rs) throws RuntimeException, SQLException{
        Clan clan = null;
        
        if(rs.next()){
            Long brojCKK = rs.getLong("brojCK");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            java.sql.Date datumRodjenja = rs.getDate("datumRodjenja");
            double brojObuce = rs.getDouble("brojObuce");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            java.sql.Date datumUclanjenja = rs.getDate("datumUclanjenja");
            boolean aktivan = rs.getBoolean("aktivan");
            
            clan = new Clan();
            
            clan.setBrojCK(brojCK);
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setDatumRodjenja(datumRodjenja);
            clan.setBrojObuce(brojObuce);
            clan.setAnsambl(ansambl);
            clan.setDatumUclanjenja(datumUclanjenja);
            clan.setAktivan(aktivan);
            
            
        }
        return clan;
    }

    @Override
    public String vratiKriterijum() {
        String upit = "";
        //String brojCK1 = String.valueOf(brojCK);
        if(brojCK != null && !ime.equals("") && !prezime.equals("")){
            upit += " brojCK LIKE '%"+brojCK+"%' AND ime LIKE '%" + ime + "%' AND prezime LIKE '%" +prezime+ "%'";
        }else if(brojCK!= null && !ime.equals("") ){
            upit += " brojCK LIKE '%"+brojCK+"%' AND ime LIKE '%" + ime + "%'";
        }else if(brojCK!= null && !prezime.equals("")){
            upit += " brojCK LIKE '%"+brojCK+"%' AND prezime LIKE '%" +prezime+ "%'";
        }else if(!ime.equals("") && !prezime.equals("")){
            upit += " ime LIKE '%" + ime + "%' AND prezime LIKE '%" +prezime+ "%'";
        }else if(brojCK!= null){
            upit += " brojCK LIKE '%"+brojCK+"%'";
        }else if(!ime.equals("")){
            upit += " ime LIKE '%" + ime + "%'";
        }else if(!prezime.equals("")){
            upit += " prezime LIKE '%" +prezime+ "%'";
        }
        return upit;
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 0;
    }

    @Override
    public int vratiBrojSlogovaVezanogObjekta(int j) {
        return 0;
    }

    @Override
    public IDomainObject vratiSlogVezanogObjekta(int j, int i) {
        return null;
    }

    @Override
    public String vratiJoinUslov() {
        return "";
    }

    @Override
    public String vratiWhereUslov() {
        return "";
    }

    @Override
    public IDomainObject vratiVezaniObjekat(int j) {
        return null;
    }

    @Override
    public void setListuVezanih(List<? extends IDomainObject> listica, int j) {
        
    }

    

   
    
    
    
}
