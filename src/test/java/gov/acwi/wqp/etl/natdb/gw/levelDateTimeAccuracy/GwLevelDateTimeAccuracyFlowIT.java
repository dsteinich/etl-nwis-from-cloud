package gov.acwi.wqp.etl.natdb.gw.levelDateTimeAccuracy;

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

public class GwLevelDateTimeAccuracyFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier("gwLevelDateTimeAccuracyFlow")
	private Flow gwLevelDateTimeAccuracyFlow;

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/gwLevelDateTimeAccuracy/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyGwLevelDateTimeAccuracy.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void deleteGwLevelDateTimeAccuracyStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("deleteGwLevelDateTimeAccuracyStep", testJobParameters);
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
			value="classpath:/testResult/nwis/emptyGwLevelDateTimeAccuracy.xml"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/gwLevelDateTimeAccuracy/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void transformGwLevelDateTimeAccuracyStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformGwLevelDateTimeAccuracyStep", testJobParameters);
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
			value="classpath:/testData/nwis/gwLevelDateTimeAccuracy/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/gwLevelDateTimeAccuracy/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void gwLevelDateTimeAccuracyFlowTest() {
		Job gwLevelDateTimeAccuracyFlowTest = jobBuilderFactory.get("gwLevelDateTimeAccuracyFlowTest")
					.start(gwLevelDateTimeAccuracyFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(gwLevelDateTimeAccuracyFlowTest);
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
