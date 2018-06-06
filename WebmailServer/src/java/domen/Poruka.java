/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author P
 */
@Entity
@Table(name = "poruka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poruka.findAll", query = "SELECT p FROM Poruka p"),
    @NamedQuery(name = "Poruka.findByPorukaid", query = "SELECT p FROM Poruka p WHERE p.porukaid = :porukaid"),
    @NamedQuery(name = "Poruka.findByTema", query = "SELECT p FROM Poruka p WHERE p.tema = :tema"),
    @NamedQuery(name = "Poruka.findByKategorija", query = "SELECT p FROM Poruka p WHERE p.kategorija = :kategorija"),
    @NamedQuery(name = "Poruka.findByPregledana", query = "SELECT p FROM Poruka p WHERE p.pregledana = :pregledana"),
    @NamedQuery(name = "Poruka.findByDatum", query = "SELECT p FROM Poruka p WHERE p.datum = :datum"),
    @NamedQuery(name = "Poruka.findByPrilog", query = "SELECT p FROM Poruka p WHERE p.prilog = :prilog")})
public class Poruka implements Serializable,IDomenskiObjekat {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "porukaid")
    private Integer porukaid;
    @Size(max = 50)
    @Column(name = "tema")
    private String tema;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "sadrzaj")
    private String sadrzaj;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "kategorija")
    private String kategorija;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pregledana")
    private boolean pregledana;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datum")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Size(max = 70)
    @Column(name = "prilog")
    private String prilog;
    @JoinColumn(name = "posiljalac", referencedColumnName = "korisnikid")
    @ManyToOne(optional = false)
    private Korisnik posiljalac;
    @JoinColumn(name = "primalac", referencedColumnName = "korisnikid")
    @ManyToOne(optional = false)
    private Korisnik primalac;

    public Poruka() {
    }

    public Poruka(Integer porukaid) {
        this.porukaid = porukaid;
    }

    public Poruka(Integer porukaid, String kategorija, boolean pregledana, Date datum) {
        this.porukaid = porukaid;
        this.kategorija = kategorija;
        this.pregledana = pregledana;
        this.datum = datum;
    }

    public Integer getPorukaid() {
        return porukaid;
    }

    public void setPorukaid(Integer porukaid) {
        this.porukaid = porukaid;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getSadrzaj() {
        return sadrzaj;
    }

    public void setSadrzaj(String sadrzaj) {
        this.sadrzaj = sadrzaj;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public boolean getPregledana() {
        return pregledana;
    }

    public void setPregledana(boolean pregledana) {
        this.pregledana = pregledana;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getPrilog() {
        return prilog;
    }

    public void setPrilog(String prilog) {
        this.prilog = prilog;
    }

    public Korisnik getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(Korisnik posiljalac) {
        this.posiljalac = posiljalac;
    }

    public Korisnik getPrimalac() {
        return primalac;
    }

    public void setPrimalac(Korisnik primalac) {
        this.primalac = primalac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (porukaid != null ? porukaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poruka)) {
            return false;
        }
        Poruka other = (Poruka) object;
        if ((this.porukaid == null && other.porukaid != null) || (this.porukaid != null && !this.porukaid.equals(other.porukaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domen.Poruka[ porukaid=" + porukaid + " ]";
    }

    @Override
    public void toDO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
