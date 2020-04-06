package gov.acwi.wqp.etl.natdb.gw.altitudeMethod;

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

public class AltitudeMethodFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier("altitudeMethodFlow")
	private Flow altitudeMethodFlow;

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/altitudeMethod/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyAltitudeMethod.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void deleteAltitudeMethodStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("deleteAltitudeMethodStep", testJobParameters);
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
			value="classpath:/testResult/nwis/emptyAltitudeMethod.xml"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/altitudeMethod/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void transformAltitudeMethodStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformAltitudeMethodStep", testJobParameters);
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
			value="classpath:/testData/nwis/altitudeMethod/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/altitudeMethod/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void altitudeMethodFlowTest() {
		Job altitudeMethodFlowTest = jobBuilderFactory.get("altitudeMethodFlowTest")
					.start(altitudeMethodFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(altitudeMethodFlowTest);
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
