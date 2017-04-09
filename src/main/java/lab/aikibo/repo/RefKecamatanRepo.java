package lab.aikibo.repo;

import lab.aikibo.entity.RefKecamatan;
import lab.aikibo.entity.RefKecamatanID;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tamami on 09/04/17.
 */
public interface RefKecamatanRepo extends CrudRepository<RefKecamatan, RefKecamatanID> {

    public List<RefKecamatan> findAll();

}
