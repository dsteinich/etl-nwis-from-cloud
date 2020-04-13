package gov.acwi.wqp.etl.natdb.gw.levelAccuracy;

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
public class GwLevelAccuracyTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwLevelAccuracy")
	private Tasklet deleteGwLevelAccuracy;

	@Autowired
	@Qualifier("gwReflistGwLevelAccuracyReader")
	private JdbcCursorItemReader<GwReflist> gwReflistGwLevelAccuracyReader;

	@Autowired
	@Qualifier("gwLevelAccuracyProcessor")
	private BasicLookupProcessor gwLevelAccuracyProcessor;

	@Autowired
	@Qualifier("gwLevelAccuracyWriter")
	private JdbcBatchItemWriter<BasicLookup> gwLevelAccuracyWriter;

	@Bean
	public Step deleteGwLevelAccuracyStep() {
		return stepBuilderFactory.get("deleteGwLevelAccuracyStep")
				.tasklet(deleteGwLevelAccuracy)
				.build();
	}

	@Bean
	public Step transformGwLevelAccuracyStep() {
		return stepBuilderFactory
				.get("transformGwLevelAccuracyStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistGwLevelAccuracyReader)
				.processor(gwLevelAccuracyProcessor)
				.writer(gwLevelAccuracyWriter)
				.build();
	}

	@Bean
	public Flow gwLevelAccuracyFlow() {
		return new FlowBuilder<SimpleFlow>("gwLevelAccuracyFlow")
				.start(deleteGwLevelAccuracyStep())
				.next(transformGwLevelAccuracyStep())
				.build();
	}
}
