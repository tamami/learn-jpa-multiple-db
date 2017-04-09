package lab.aikibo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by tamami on 09/04/17.
 */
@Data
public class RefKecamatanID implements Serializable {

    private String kdPropinsi;
    private String kdDati2;
    private String kdKecamatan;

}
