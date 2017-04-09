package lab.aikibo;

import lab.aikibo.entity.RefKecamatan;
import lab.aikibo.repo.RefKecamatanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Iterator;
import java.util.List;

/**
 * masih gagal, solusinya harus pake AbstractRoutingDataSource
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class})
@ComponentScan({"lab.aikibo.repo","lab.aikibo.config"})
@EnableTransactionManagement
public class LearnJpaMultipleDbApplication implements CommandLineRunner{

	@Autowired
	DataSource sismiopDS;

	@Autowired
	DataSource dummyDS;

	//@Autowired
    RefKecamatanRepo refKecamatanRepo;

	public static void main(String[] args) {
		SpringApplication.run(LearnJpaMultipleDbApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//System.out.println("sismiop : " + sismiopDS);
		//System.out.println("dummy : " + dummyDS);

        List<RefKecamatan> dataSismiop = getDataSismiop();
        Iterator it = dataSismiop.iterator();

        if(it.hasNext()) {
            System.out.println("kd_kecamatan : " + ((RefKecamatan) it.next()).getKdKecamatan());
        } else System.out.println("sismiop tidak ada data");

        List<RefKecamatan> dataDummy = getDataDummy();
        it = dataDummy.iterator();
        if(it.hasNext()) {
            System.out.println("kd_kecamatan : " + ((RefKecamatan) it.next()).getKdKecamatan());
        } else System.out.println("dummy tidak ada data");

	}

	@Transactional("sismiopTransactionManager")
    public List<RefKecamatan> getDataSismiop() {
	    return refKecamatanRepo.findAll();
    }

    @Transactional("dummyTransactionManager")
    public List<RefKecamatan> getDataDummy() {
	    return refKecamatanRepo.findAll();
    }
}
