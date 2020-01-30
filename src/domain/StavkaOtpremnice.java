/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.enumeracije.VrstaNosnje;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class StavkaOtpremnice implements Serializable, IDomainObject{
    private int redniBroj;
    private Long kolicina;
    private Nosnja nosnja;
    private Long sifraOtpremnice;

    public StavkaOtpremnice() {
    }

    public StavkaOtpremnice(int redniBroj, Long kolicina, Nosnja nosnja) {
        this.redniBroj = redniBroj;
        this.kolicina = kolicina;
        this.nosnja = nosnja;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Long getKolicina() {
        return kolicina;
    }

    public void setKolicina(Long kolicina) {
        this.kolicina = kolicina;
    }

    public Nosnja getNosnja() {
        return nosnja;
    }

    public void setNosnja(Nosnja nosnja) {
        this.nosnja = nosnja;
    }

    public Long getSifraOtpremnice() {
        return sifraOtpremnice;
    }

    public void setSifraOtpremnice(Long sifraOtpremnice) {
        this.sifraOtpremnice = sifraOtpremnice;
    }
    
    
    @Override
    public String getTableName() {
        return "stavkaotpremnice";
    }

    @Override
    public String getAllColumnNames() {
        return "rb,sifraOtpremnice,kolicina,sifraNosnje";
    }

    @Override
    public String getValuesForInsert() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(getRedniBroj()).append(",")
                .append(getSifraOtpremnice()).append(",")
                .append(getKolicina());
        if(getNosnja() != null){
            Nosnja n = new Nosnja();
            n.setSifraNosnje(9l);
            setNosnja(nosnja);
            sb.append(",")
                .append(getNosnja().getSifraNosnje());
        }
        
        String values = sb.toString();
        return values;
    }

    @Override
    public void setId(Long id) {
        this.sifraOtpremnice = id;
    }

    @Override
    public String getKeyName() {
        return "sifraOtpremnice";
    }

    @Override
    public String getKeyValue() {
        return getSifraOtpremnice() + "";
    }

    @Override
    public String getColumnNameAndValuesForUpdate() {
        return "";
    }

    @Override
    public List<IDomainObject> napraviListu(ResultSet rs) throws Exception {
        List<IDomainObject> stavkeOtpremnice = new ArrayList<>();
        while (rs.next()) {
                StavkaOtpremnice st = new StavkaOtpremnice();
                st.setRedniBroj(rs.getInt("rb"));
                st.setKolicina(rs.getLong("kolicina"));

                Nosnja nosnja = new Nosnja();
                nosnja.setSifraNosnje(rs.getLong("sifraNosnje"));
                nosnja.setNazivNosnje(rs.getString("nazivNosnje"));
                nosnja.setVrstaNosnje(VrstaNosnje.valueOf(rs.getString("vrstaNosnje")));
                st.setNosnja(nosnja);

                stavkeOtpremnice.add(st);
            }
        return stavkeOtpremnice;
    }

    @Override
    public IDomainObject napraviZaJednog(ResultSet rs) throws Exception {
        return null;
    }

    @Override
    public String vratiKriterijum() {
        return "";
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
        return " st JOIN nosnja1 n ON (st.sifraNosnje = n.sifraNosnje) ";
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
