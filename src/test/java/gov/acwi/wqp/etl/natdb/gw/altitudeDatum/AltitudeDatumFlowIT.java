package gov.acwi.wqp.etl.natdb.gw.altitudeDatum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import gov.acwi.wqp.etl.natdb.BaseFlowIT;

public class AltitudeDatumFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier("altitudeDatumFlow")
	private Flow altitudeDatumFlow;

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/altitudeDatum/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyAltitudeDatum.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void deleteAltitudeDatumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("deleteAltitudeDatumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/gwReflist/"
			)
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyAltitudeDatum.xml"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/altitudeDatum/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void transformAltitudeDatumStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformAltitudeDatumStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/gwReflist/"
			)
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/altitudeDatum/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/altitudeDatum/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void altitudeDatumFlowTest() {
		Job altitudeDatumFlowTest = jobBuilderFactory.get("altitudeDatumFlowTest")
					.start(altitudeDatumFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(altitudeDatumFlowTest);
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchJob(testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
