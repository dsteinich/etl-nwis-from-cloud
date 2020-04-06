package gov.acwi.wqp.etl.natdb.gw.topographicSetting;

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

public class TopographicSettingFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier("topographicSettingFlow")
	private Flow topographicSettingFlow;

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/topographicSetting/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyTopographicSetting.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void deleteTopographicSettingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("deleteTopographicSettingStep", testJobParameters);
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
			value="classpath:/testResult/nwis/emptyTopographicSetting.xml"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/topographicSetting/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void transformTopographicSettingStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformTopographicSettingStep", testJobParameters);
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
			value="classpath:/testData/nwis/topographicSetting/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/topographicSetting/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void topographicSettingFlowTest() {
		Job topographicSettingFlowTest = jobBuilderFactory.get("topographicSettingFlowTest")
					.start(topographicSettingFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(topographicSettingFlowTest);
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
