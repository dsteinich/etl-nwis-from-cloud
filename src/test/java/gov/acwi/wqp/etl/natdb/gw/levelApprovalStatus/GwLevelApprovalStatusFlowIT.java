package gov.acwi.wqp.etl.natdb.gw.levelApprovalStatus;

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

public class GwLevelApprovalStatusFlowIT extends BaseFlowIT {

	@Autowired
	@Qualifier("gwLevelApprovalStatusFlow")
	private Flow gwLevelApprovalStatusFlow;

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/gwLevelApprovalStatus/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyGwLevelApprovalStatus.xml",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void deleteGwLevelApprovalStatusStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("deleteGwLevelApprovalStatusStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NATDB,
			value="classpath:/testData/natdb/"
			)
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/emptyGwLevelApprovalStatus.xml"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/gwLevelApprovalStatus/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void transformGwLevelApprovalStatusStepTest() {
		try {
			JobExecution jobExecution = jobLauncherTestUtils.launchStep("transformGwLevelApprovalStatusStep", testJobParameters);
			assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	@DatabaseSetup(
			connection=CONNECTION_NATDB,
			value="classpath:/testData/natdb/"
			)
	@DatabaseSetup(
			connection=CONNECTION_NWIS,
			value="classpath:/testData/nwis/gwLevelApprovalStatus/"
			)
	@ExpectedDatabase(
			connection=CONNECTION_NWIS,
			value="classpath:/testResult/nwis/gwLevelApprovalStatus/",
			assertionMode=DatabaseAssertionMode.NON_STRICT_UNORDERED
			)
	public void gwLevelApprovalStatusFlowTest() {
		Job gwLevelApprovalStatusFlowTest = jobBuilderFactory.get("gwLevelApprovalStatusFlowTest")
					.start(gwLevelApprovalStatusFlow)
					.build()
					.build();
		jobLauncherTestUtils.setJob(gwLevelApprovalStatusFlowTest);
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
