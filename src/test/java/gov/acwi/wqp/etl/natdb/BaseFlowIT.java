package gov.acwi.wqp.etl.natdb;

import java.time.LocalDate;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.StepScopeTestExecutionListener;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@SpringBatchTest
@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		StepScopeTestExecutionListener.class,
		DbUnitTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class
		})
@AutoConfigureTestDatabase(replace=Replace.NONE)
@Import({DBTestConfig.class})
@DbUnitConfiguration(
		databaseConnection={BaseFlowIT.CONNECTION_NATDB,BaseFlowIT.CONNECTION_NWIS},
		dataSetLoader=FileSensingDataSetLoader.class
		)
@DirtiesContext
public abstract class BaseFlowIT {

	public static final String CONNECTION_NATDB = "natdb";
	public static final String CONNECTION_NWIS = "nwis";

	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected JobBuilderFactory jobBuilderFactory;
	@Autowired
	protected JobLauncherTestUtils jobLauncherTestUtils;
	@Autowired
	protected DataSource dataSource;

	protected JobParameters testJobParameters;

	protected Job testJob;

	@BeforeEach
	public void baseSetup() {
		testJobParameters= new JobParametersBuilder()
				.addJobParameters(jobLauncherTestUtils.getUniqueJobParameters())
				.addString("jobId", LocalDate.now().toString(), true)
				.toJobParameters();
	}

}
