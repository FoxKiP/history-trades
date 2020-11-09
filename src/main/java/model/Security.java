package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "securities")
public class Security extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "id_on_exchange", nullable = false)
    private String idOnExchange;

    @Column(name = "reg_number")
    private String regNumber;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "isin")
    private String isin;

    @Column(name = "emitent_title")
    private String emitentTitle;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "security")
    private Set<History> histories;

    public Security() {
    }

    public Security(Security security) {
        this(
                security.getId(),
                security.getIdOnExchange(),
                security.getRegNumber(),
                security.getName(),
                security.getIsin(),
                security.getEmitentTitle()
        );
    }

    public Security(String idOnExchange, String regNumber, String name, String isin, String emitentTitle) {
        this(null, idOnExchange, regNumber, name, isin, emitentTitle);
    }

    public Security(Integer id, String idOnExchange, String regNumber, String name, String isin, String emitentTitle) {
        super(id);
        this.idOnExchange = idOnExchange;
        this.regNumber = regNumber;
        this.name = name;
        this.isin = isin;
        this.emitentTitle = emitentTitle;
    }



    public String getIdOnExchange() {
        return idOnExchange;
    }

    public void setIdOnExchange(String idOnExchange) {
        this.idOnExchange = idOnExchange;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getEmitentTitle() {
        return emitentTitle;
    }

    public void setEmitentTitle(String emitentTitle) {
        this.emitentTitle = emitentTitle;
    }

    @Override
    public String toString() {
        return "Security{" +
                "idOnExchange='" + idOnExchange + '\'' +
                ", regNumber='" + regNumber + '\'' +
                ", name='" + name + '\'' +
                ", isin='" + isin + '\'' +
                ", emitentTitle='" + emitentTitle + '\'' +
                '}';
    }
}
