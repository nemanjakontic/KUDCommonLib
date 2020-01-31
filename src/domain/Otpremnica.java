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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Nemanja
 */
public class Otpremnica implements Serializable, IDomainObject {

    private Long sifraOtpremnice;
    private Date datumKreiranja;
    private List<StavkaOtpremnice> stavkaOtpremnice;
    private List<IzmeneOtpremnice> izmenaOtpremnice;
    private boolean aktivna;
    private Clan clan;

    public Otpremnica() {
        stavkaOtpremnice = new ArrayList<>();
        izmenaOtpremnice = new ArrayList<>();
    }

    public Otpremnica(Long sifraOtpremnice, Date datumKreiranja, boolean aktivna, Clan clan) {
        this.sifraOtpremnice = sifraOtpremnice;
        this.datumKreiranja = datumKreiranja;
        this.aktivna = aktivna;
        this.clan = clan;
        stavkaOtpremnice = new ArrayList<>();
        izmenaOtpremnice = new ArrayList<>();
    }

    public Long getSifraOtpremnice() {
        return sifraOtpremnice;
    }

    public void setSifraOtpremnice(Long sifraOtpremnice) {
        this.sifraOtpremnice = sifraOtpremnice;
    }

    public Date getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(Date datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public List<StavkaOtpremnice> getStavkaOtpremnice() {
        return stavkaOtpremnice;
    }

    public void setStavkaOtpremnice(List<StavkaOtpremnice> stavkaOtpremnice) {
        this.stavkaOtpremnice = stavkaOtpremnice;
    }

    public List<IzmeneOtpremnice> getIzmenaOtpremnice() {
        return izmenaOtpremnice;
    }

    public void setIzmenaOtpremnice(List<IzmeneOtpremnice> izmenaOtpremnice) {
        this.izmenaOtpremnice = izmenaOtpremnice;
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    public Clan getClan() {
        return clan;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
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
        final Otpremnica other = (Otpremnica) obj;
        if (!Objects.equals(this.sifraOtpremnice, other.sifraOtpremnice)) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return "otpremnica";
    }

    @Override
    public String getAllColumnNames() {
        return "datumKreiranja, aktivna, brojCK";
    }

    @Override
    public String getValuesForInsert() {
        StringBuilder sb = new StringBuilder();

        sb.append("'").append(new java.sql.Date(getDatumKreiranja().getTime())).append("'").append(",")
                .append(isAktivna()).append(",")
                .append(getClan().getBrojCK());

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
        return "datumKreiranja = '" + new java.sql.Date(getDatumKreiranja().getTime()) + 
                "', aktivna = " + isAktivna() + ", brojCK = " + getClan().getBrojCK();
    }

    @Override
    public List<IDomainObject> napraviListu(ResultSet rs) throws Exception {
        List<IDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Otpremnica o = new Otpremnica();
            Long sifra = rs.getLong("sifraOtpremnice");
            java.sql.Date datum = rs.getDate("datumKreiranja");
            boolean aktivna = rs.getBoolean("aktivna");

            Clan clan = new Clan();

            Long brojCK = rs.getLong("brojCK");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            Pol pol = Pol.valueOf(rs.getString("pol"));
            double visina = rs.getDouble("visina");
            Ansambl ansambl = Ansambl.valueOf(rs.getString("ansambl"));
            boolean aktivan = rs.getBoolean("aktivan");

            clan.setBrojCK(brojCK);
            clan.setIme(ime);
            clan.setPrezime(prezime);
            clan.setPol(pol);
            clan.setVisina(visina);
            clan.setAnsambl(ansambl);
            clan.setAktivan(aktivan);

            o.setSifraOtpremnice(sifra);
            o.setDatumKreiranja(datum);
            o.setAktivna(aktivna);
            o.setClan(clan);

            lista.add(o);
        }

        return lista;
    }

    @Override
    public IDomainObject napraviZaJednog(ResultSet rs) throws Exception {
        Otpremnica o = new Otpremnica();

        if (rs.next()) {

            o.setSifraOtpremnice(rs.getLong("sifraOtpremnice"));
            o.setDatumKreiranja(rs.getDate("datumKreiranja"));
            o.setAktivna(rs.getBoolean("aktivna"));

            Clan clan = new Clan();
            clan.setBrojCK(rs.getLong("brojCK"));
            clan.setIme(rs.getString("ime"));
            clan.setPrezime(rs.getString("prezime"));
            o.setClan(clan);
        }
        return o;
    }

    @Override
    public String vratiKriterijum() {
        String upit = "";

        String ime = null;
        String prezime = null;

        if (clan != null) {
            ime = clan.getIme();
            prezime = clan.getPrezime();
        }
        if (sifraOtpremnice != null && clan != null) {
            upit += " WHERE o.sifraOtpremnice LIKE '%" + sifraOtpremnice + "%' AND c.ime LIKE '%" + ime + "%' AND c.prezime LIKE '%" + prezime + "%'";
        } else if (sifraOtpremnice != null) {
            upit += " WHERE o.sifraOtpremnice LIKE '%" + sifraOtpremnice + "%'";
        } else if (clan != null) {
            upit += " WHERE c.ime LIKE '%" + ime + "%' AND c.prezime LIKE '%" + prezime + "%'";
        }
        return upit;
    }

    @Override
    public int vratiBrojVezanihObjekata() {
        return 2;
    }

    @Override
    public int vratiBrojSlogovaVezanogObjekta(int j) {
        if (j == 0) {
            return stavkaOtpremnice.size();
        } else {
            return izmenaOtpremnice.size();
        }
    }

    @Override
    public IDomainObject vratiSlogVezanogObjekta(int j, int i) {
        if (j == 0) {
            return (IDomainObject) stavkaOtpremnice.get(i);
        } else {
            return (IDomainObject) izmenaOtpremnice.get(i);
        }
    }

    @Override
    public String vratiJoinUslov() {
        StringBuilder sb = new StringBuilder();

        sb.append(" o JOIN clan c ON(o.brojCK = c.brojCK) ");

        String values = sb.toString();
        return values;
    }

    @Override
    public String vratiWhereUslov() {
        StringBuilder sb = new StringBuilder();

        sb.append("WHERE o.sifraOtpremnice = ");

        String values = sb.toString();
        return values;
    }

    @Override
    public IDomainObject vratiVezaniObjekat(int j) {
        StavkaOtpremnice st = new StavkaOtpremnice();
        IzmeneOtpremnice izm = new IzmeneOtpremnice();
        /*if (stavkaOtpremnice.isEmpty()) {
            stavkaOtpremnice.add(st);
        }
        if (izmenaOtpremnice.isEmpty()) {
            izmenaOtpremnice.add(izm);
        }*/
        if (j == 0) {
            return st;
        } else if(j == 1){
            return izm;
        }
        return null;
    }

    @Override
    public void setListuVezanih(List<? extends IDomainObject> listica, int j) {
        if (j == 0) {
            this.stavkaOtpremnice = (List<StavkaOtpremnice>) listica;
        }
        if (j == 1) {
            this.izmenaOtpremnice = (List<IzmeneOtpremnice>) listica;
        }
    }

    @Override
    public void obrisiTajJedan(int j) {
        if (j == 0) {
            stavkaOtpremnice.remove(0);
        }
    }

}
