package lab.aikibo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by tamami on 09/04/17.
 */
@Data
@Entity
@Table(name = "ref_kecamatan")
@IdClass(RefKecamatanID.class)
public class RefKecamatan {

    @Id
    private String kdPropinsi;
    @Id
    private String kdDati2;
    @Id
    private String kdKecamatan;
    @Column
    private String nmKecamatan;

}
