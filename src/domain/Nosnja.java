/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enumeracije.Pol;
import domain.enumeracije.VrstaNosnje;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nemanja
 */
public class Nosnja implements Serializable, IDomainObject {

    private Long sifraNosnje;
    private VrstaNosnje vrstaNosnje;
    private Pol pol;
    private String nazivNosnje;
    private int kolicina;
    private String opis;
    private String vrstaBeline;
    private String velicina;
    private String vrstaOpanaka;

    public Nosnja() {
    }

    public Nosnja(Long sifraNosnje, VrstaNosnje vrstaNosnje, Pol pol, String nazivNosnje, int kolicina, String opis, String vrstaBeline, String velicina, String vrstaOpanaka) {
        this.sifraNosnje = sifraNosnje;
        this.vrstaNosnje = vrstaNosnje;
        this.pol = pol;
        this.nazivNosnje = nazivNosnje;
        this.kolicina = kolicina;
        this.opis = opis;
        this.vrstaBeline = vrstaBeline;
        this.velicina = velicina;
        this.vrstaOpanaka = vrstaOpanaka;
    }

    public Long getSifraNosnje() {
        return sifraNosnje;
    }

    public void setSifraNosnje(Long sifraNosnje) {
        this.sifraNosnje = sifraNosnje;
    }

    public VrstaNosnje getVrstaNosnje() {
        return vrstaNosnje;
    }

    public void setVrstaNosnje(VrstaNosnje vrstaNosnje) {
        this.vrstaNosnje = vrstaNosnje;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
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
        final Nosnja other = (Nosnja) obj;
        if (!Objects.equals(this.sifraNosnje, other.sifraNosnje)) {
            return false;
        }
        return true;
    }

    public String getNazivNosnje() {
        return nazivNosnje;
    }

    public void setNazivNosnje(String nazivNosnje) {
        this.nazivNosnje = nazivNosnje;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getVrstaBeline() {
        return vrstaBeline;
    }

    public void setVrstaBeline(String vrstaBeline) {
        this.vrstaBeline = vrstaBeline;
    }

    public String getVelicina() {
        return velicina;
    }

    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    public String getVrstaOpanaka() {
        return vrstaOpanaka;
    }

    public void setVrstaOpanaka(String vrstaOpanaka) {
        this.vrstaOpanaka = vrstaOpanaka;
    }

    @Override
    public String getTableName() {
        return "nosnja1";
    }

    @Override
    public String getAllColumnNames() {
        return "vrstaNosnje, pol, nazivNosnje,  kolicina, opisNosnje, vrstaBeline, vrstaOpanaka, velicina";
    }

    @Override
    public String getValuesForInsert() {
        StringBuilder sb = new StringBuilder();

        sb.append("'").append(getVrstaNosnje()).append("'").append(",")
                .append("'").append(getPol()).append("'").append(",")
                .append("'").append(getNazivNosnje()).append("'").append(",")
                .append(getKolicina()).append(",")
                .append("'").append(getOpis()).append("'").append(",")
                .append("'").append(getVrstaBeline()).append("'").append(",")
                .append("'").append(getVrstaOpanaka()).append("'").append(",")
                .append(getVelicina());

        String values = sb.toString();
        return values;
    }

    @Override
    public void setId(Long id) {
        this.sifraNosnje = id;
    }

    @Override
    public String getKeyName() {
        return "sifraNosnje";
    }

    @Override
    public String getKeyValue() {
        return getSifraNosnje() + "";
    }

    @Override
    public String getColumnNameAndValuesForUpdate() {
        return "vrstaNosnje = '" + String.valueOf(getVrstaNosnje())
                + "', pol = '" + String.valueOf(getPol()) + "', nazivNosnje = '"
                + getNazivNosnje() + "', kolicina = " + getKolicina()
                + ", opisNosnje = '" + getOpis() + "', vrstaBeline = '"
                + String.valueOf(getVrstaBeline()) + "', vrstaOpanaka = '"
                + String.valueOf(getVrstaOpanaka()) + "', velicina = " + getVelicina();
    }

    @Override
    public List<IDomainObject> napraviListu(ResultSet rs) throws Exception {
        List<IDomainObject> nosnje = new ArrayList<>();

        while (rs.next()) {
            Nosnja n = new Nosnja();

            Long sifra = rs.getLong("sifraNosnje");
            VrstaNosnje vrstaNosnje = VrstaNosnje.valueOf(rs.getString("vrstaNosnje"));
            Pol pol = Pol.valueOf(rs.getString("pol"));
            String nazivNosnje = rs.getString("nazivNosnje");
            int kolicina = rs.getInt("kolicina");
            String opis = rs.getString("opisNosnje");
            String vrstaBeline1 = rs.getString("vrstaBeline");
            String velicina = rs.getString("velicina");
            String vrstaOpanaka1 = rs.getString("vrstaOpanaka");

            n.setSifraNosnje(sifra);
            n.setVrstaNosnje(vrstaNosnje);
            n.setPol(pol);
            n.setNazivNosnje(nazivNosnje);
            n.setKolicina(kolicina);
            n.setOpis(opis);
            n.setVrstaBeline(vrstaBeline1);
            n.setVelicina(velicina);
            n.setVrstaOpanaka(vrstaOpanaka1);

            nosnje.add(n);

        }
        return nosnje;
    }

    @Override
    public IDomainObject napraviZaJednog(ResultSet rs) throws Exception {
        Nosnja n = null;

        if (rs.next()) {
            Long sifra = rs.getLong("sifraNosnje");
            VrstaNosnje vrstaNosnje = VrstaNosnje.valueOf(rs.getString("vrstaNosnje"));
            Pol pol = Pol.valueOf(rs.getString("pol"));
            String nazivNosnje = rs.getString("nazivNosnje");
            int kolicina = rs.getInt("kolicina");
            String opis = rs.getString("opisNosnje");
            String vrstaBeline1 = rs.getString("vrstaBeline");
            String velicina = rs.getString("velicina");
            String vrstaOpanaka1 = rs.getString("vrstaOpanaka");

            n = new Nosnja();
            n.setSifraNosnje(sifra);
            n.setVrstaNosnje(vrstaNosnje);
            n.setPol(pol);
            n.setNazivNosnje(nazivNosnje);
            n.setKolicina(kolicina);
            n.setOpis(opis);
            n.setVrstaBeline(vrstaBeline1);
            n.setVelicina(velicina);
            n.setVrstaOpanaka(vrstaOpanaka1);

        }
        return n;
    }

    @Override
    public String vratiKriterijum() {
        String upit = "";
        if(sifraNosnje != null && !nazivNosnje.equals("") && !String.valueOf(vrstaNosnje).equals("Izaberite_vrstu_nosnje")){
            upit += " sifraNosnje LIKE '%" + sifraNosnje + "%' AND nazivNosnje LIKE '%" + nazivNosnje + "%' AND vrstaNosnje LIKE '%" + vrstaNosnje + "%'";
        }else if(sifraNosnje != null && !nazivNosnje.equals("")){
            upit += " sifraNosnje LIKE '%" + sifraNosnje + "%' AND nazivNosnje LIKE '%" + nazivNosnje + "%'";
        }else if(sifraNosnje != null && !String.valueOf(vrstaNosnje).equals("Izaberite_vrstu_nosnje") ){
            upit += " sifraNosnje LIKE '%" + sifraNosnje + "%' AND vrstaNosnje LIKE '%" + vrstaNosnje + "%'";
        }else if(!nazivNosnje.equals("") && !String.valueOf(vrstaNosnje).equals("Izaberite_vrstu_nosnje")){
            upit += " nazivNosnje LIKE '%" + nazivNosnje + "%' AND vrstaNosnje LIKE '%" + vrstaNosnje + "%'";
        }else if(sifraNosnje != null){
            upit += " sifraNosnje LIKE '%" + sifraNosnje + "%'";
        }else if(!nazivNosnje.equals("")){
            upit += " nazivNosnje LIKE '%" + nazivNosnje + "%'";
        }else if(!String.valueOf(vrstaNosnje).equals("Izaberite_vrstu_nosnje")){
            upit += " vrstaNosnje LIKE '%" + vrstaNosnje + "%'";
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

    @Override
    public void obrisiTajJedan(int j) {
        
    }

}
