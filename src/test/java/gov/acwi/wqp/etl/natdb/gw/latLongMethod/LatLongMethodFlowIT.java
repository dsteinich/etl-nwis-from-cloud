package gov.acwi.wqp.etl.natdb.gw.latLongMethod;

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

public class LatLongMethodFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier("latLongMethodFlow")
	private Flow latLongMethodFlow;

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/latLongMethod/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyLatLongMethod.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void deleteLatLongMethodStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("deleteLatLongMethodStep", testJobParameters);
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
			value="classpath:/testResult/nwis/emptyLatLongMethod.xml"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/latLongMethod/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void transformLatLongMethodStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformLatLongMethodStep", testJobParameters);
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
			value="classpath:/testData/nwis/latLongMethod/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/latLongMethod/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void latLongMethodFlowTest() {
		Job latLongMethodFlowTest = jobBuilderFactory.get("latLongMethodFlowTest")
					.start(latLongMethodFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(latLongMethodFlowTest);
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
