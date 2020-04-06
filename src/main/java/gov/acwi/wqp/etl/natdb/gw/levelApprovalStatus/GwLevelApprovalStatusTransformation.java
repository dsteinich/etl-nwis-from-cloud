package gov.acwi.wqp.etl.natdb.gw.levelApprovalStatus;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gov.acwi.wqp.etl.natdb.gw.BasicLookup;
import gov.acwi.wqp.etl.natdb.gw.BasicLookupProcessor;
import gov.acwi.wqp.etl.natdb.gw.GwReflist;

@Configuration
public class GwLevelApprovalStatusTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwLevelApprovalStatus")
	private Tasklet deleteGwLevelApprovalStatus;

	@Autowired
	@Qualifier("gwReflistGwLevelApprovalStatusReader")
	private JdbcCursorItemReader<GwReflist> gwReflistGwLevelApprovalStatusReader;

	@Autowired
	@Qualifier("gwLevelApprovalStatusProcessor")
	private BasicLookupProcessor gwLevelApprovalStatusProcessor;

	@Autowired
	@Qualifier("gwLevelApprovalStatusWriter")
	private JdbcBatchItemWriter<BasicLookup> gwLevelApprovalStatusWriter;

	@Bean
	public Step deleteGwLevelApprovalStatusStep() {
		return stepBuilderFactory.get("deleteGwLevelApprovalStatusStep")
				.tasklet(deleteGwLevelApprovalStatus)
				.build();
	}

	@Bean
	public Step transformGwLevelApprovalStatusStep() {
		return stepBuilderFactory
				.get("transformGwLevelApprovalStatusStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistGwLevelApprovalStatusReader)
				.processor(gwLevelApprovalStatusProcessor)
				.writer(gwLevelApprovalStatusWriter)
				.build();
	}

	@Bean
	public Flow gwLevelApprovalStatusFlow() {
		return new FlowBuilder<SimpleFlow>("gwLevelApprovalStatusFlow")
				.start(deleteGwLevelApprovalStatusStep())
				.next(transformGwLevelApprovalStatusStep())
				.build();
	}
}
