package gov.acwi.wqp.etl.natdb.gw.levelDateTimeAccuracy;

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
public class GwLevelDateTimeAccuracyTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwLevelDateTimeAccuracy")
	private Tasklet deleteGwLevelDateTimeAccuracy;

	@Autowired
	@Qualifier("gwReflistGwLevelDateTimeAccuracyReader")
	private JdbcCursorItemReader<GwReflist> gwReflistGwLevelDateTimeAccuracyReader;

	@Autowired
	@Qualifier("gwLevelDateTimeAccuracyProcessor")
	private BasicLookupProcessor gwLevelDateTimeAccuracyProcessor;

	@Autowired
	@Qualifier("gwLevelDateTimeAccuracyWriter")
	private JdbcBatchItemWriter<BasicLookup> gwLevelDateTimeAccuracyWriter;

	@Bean
	public Step deleteGwLevelDateTimeAccuracyStep() {
		return stepBuilderFactory.get("deleteGwLevelDateTimeAccuracyStep")
				.tasklet(deleteGwLevelDateTimeAccuracy)
				.build();
	}

	@Bean
	public Step transformGwLevelDateTimeAccuracyStep() {
		return stepBuilderFactory
				.get("transformGwLevelDateTimeAccuracyStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistGwLevelDateTimeAccuracyReader)
				.processor(gwLevelDateTimeAccuracyProcessor)
				.writer(gwLevelDateTimeAccuracyWriter)
				.build();
	}

	@Bean
	public Flow gwLevelDateTimeAccuracyFlow() {
		return new FlowBuilder<SimpleFlow>("gwLevelDateTimeAccuracyFlow")
				.start(deleteGwLevelDateTimeAccuracyStep())
				.next(transformGwLevelDateTimeAccuracyStep())
				.build();
	}
}
