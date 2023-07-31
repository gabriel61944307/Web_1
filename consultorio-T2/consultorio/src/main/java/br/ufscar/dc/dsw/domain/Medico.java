package br.ufscar.dc.dsw.domain;

// import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;
// import org.springframework.format.annotation.NumberFormat;
// import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "Medico")
public class Medico extends Usuario {
    
    // formato: 12.345/SP
    @NotBlank
    @Column(nullable = false, length = 9)
    private String CRM;

    @NotBlank
    @Column(nullable = false, length = 16)
    private String Especialidade;

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String cRM) {
        CRM = cRM;
    }

    public String getEspecialidade() {
        return Especialidade;
    }

    public void setEspecialidade(String especialidade) {
        Especialidade = especialidade;
    }

    
}
