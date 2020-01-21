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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nemanja
 */
public class IzmeneOtpremnice implements Serializable, IDomainObject {

    private int rb;
    private Date datumIzmene;
    private Nosnja nosnja;
    private Long sifraOtpremnice;

    public IzmeneOtpremnice() {
    }

    public IzmeneOtpremnice(int rb, Date datumIzmene, Nosnja nosnja) {
        this.rb = rb;
        this.datumIzmene = datumIzmene;
        this.nosnja = nosnja;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public Date getDatumIzmene() {
        return datumIzmene;
    }

    public void setDatumIzmene(Date datumIzmene) {
        this.datumIzmene = datumIzmene;
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
        return "izmeneotpremnice";
    }

    @Override
    public String getAllColumnNames() {
        return "rb,sifraOtpremnice,datumIzmene,sifraNosnje";
    }

    @Override
    public String getValuesForInsert() {
        StringBuilder sb = new StringBuilder();

        sb.append(getRb()).append(",")
                .append(getSifraOtpremnice()).append(",")
                .append(getDatumIzmene()).append(",")
                .append(getNosnja().getSifraNosnje());

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
        List<IDomainObject> izmeneOtpremnice = new ArrayList<>();
        while (rs.next()) {
            IzmeneOtpremnice iz = new IzmeneOtpremnice();
            iz.setRb(rs.getInt("rb"));
            iz.setDatumIzmene(rs.getDate("datumIzmene"));

            Nosnja nosnja = new Nosnja();
            nosnja.setSifraNosnje(rs.getLong("sifraNosnje"));
            nosnja.setNazivNosnje(rs.getString("nazivNosnje"));
            nosnja.setVrstaNosnje(VrstaNosnje.valueOf(rs.getString("vrstaNosnje")));
            iz.setNosnja(nosnja);

            izmeneOtpremnice.add(iz);
        }
        return izmeneOtpremnice;
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
        return " iz JOIN nosnja1 n ON (iz.sifraNosnje = n.sifraNosnje) ";
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
