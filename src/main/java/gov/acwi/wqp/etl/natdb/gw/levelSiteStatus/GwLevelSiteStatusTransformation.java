package gov.acwi.wqp.etl.natdb.gw.levelSiteStatus;

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
public class GwLevelSiteStatusTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwLevelSiteStatus")
	private Tasklet deleteGwLevelSiteStatus;

	@Autowired
	@Qualifier("gwReflistGwLevelSiteStatusReader")
	private JdbcCursorItemReader<GwReflist> gwReflistGwLevelSiteStatusReader;

	@Autowired
	@Qualifier("gwLevelSiteStatusProcessor")
	private BasicLookupProcessor gwLevelSiteStatusProcessor;

	@Autowired
	@Qualifier("gwLevelSiteStatusWriter")
	private JdbcBatchItemWriter<BasicLookup> gwLevelSiteStatusWriter;

	@Bean
	public Step deleteGwLevelSiteStatusStep() {
		return stepBuilderFactory.get("deleteGwLevelSiteStatusStep")
				.tasklet(deleteGwLevelSiteStatus)
				.build();
	}

	@Bean
	public Step transformGwLevelSiteStatusStep() {
		return stepBuilderFactory
				.get("transformGwLevelSiteStatusStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistGwLevelSiteStatusReader)
				.processor(gwLevelSiteStatusProcessor)
				.writer(gwLevelSiteStatusWriter)
				.build();
	}

	@Bean
	public Flow gwLevelSiteStatusFlow() {
		return new FlowBuilder<SimpleFlow>("gwLevelSiteStatusFlow")
				.start(deleteGwLevelSiteStatusStep())
				.next(transformGwLevelSiteStatusStep())
				.build();
	}
}
