/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author P
 */
@Entity
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByKorisnikid", query = "SELECT k FROM Korisnik k WHERE k.korisnikid = :korisnikid"),
    @NamedQuery(name = "Korisnik.findByLozinka", query = "SELECT k FROM Korisnik k WHERE k.lozinka = :lozinka"),
    @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime"),
    @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime"),
    @NamedQuery(name = "Korisnik.findByAlt", query = "SELECT k FROM Korisnik k WHERE k.alt = :alt"),
    @NamedQuery(name = "Korisnik.findByTp", query = "SELECT k FROM Korisnik k WHERE k.tp = :tp")})
public class Korisnik implements Serializable,IDomenskiObjekat {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "korisnikid")
    private String korisnikid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "lozinka")
    private String lozinka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "alt")
    private String alt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "tp")
    private String tp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posiljalac")
    private List<Poruka> porukaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "primalac")
    private List<Poruka> porukaList1;

    public Korisnik() {
    }

    public Korisnik(String korisnikid) {
        this.korisnikid = korisnikid;
    }

    public Korisnik(String korisnikid, String lozinka, String ime, String prezime, String alt, String tp) {
        this.korisnikid = korisnikid;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.alt = alt;
        this.tp = tp;
    }

    public String getKorisnikid() {
        return korisnikid;
    }

    public void setKorisnikid(String korisnikid) {
        this.korisnikid = korisnikid;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    @XmlTransient
    public List<Poruka> getPorukaList() {
        return porukaList;
    }

    public void setPorukaList(List<Poruka> porukaList) {
        this.porukaList = porukaList;
    }

    @XmlTransient
    public List<Poruka> getPorukaList1() {
        return porukaList1;
    }

    public void setPorukaList1(List<Poruka> porukaList1) {
        this.porukaList1 = porukaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korisnikid != null ? korisnikid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.korisnikid == null && other.korisnikid != null) || (this.korisnikid != null && !this.korisnikid.equals(other.korisnikid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Korisnik[ korisnikid=" + korisnikid + " ]";
    }

    @Override
    public void toDO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
