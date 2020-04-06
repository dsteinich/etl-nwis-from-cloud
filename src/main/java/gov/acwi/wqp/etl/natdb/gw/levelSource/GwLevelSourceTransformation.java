package gov.acwi.wqp.etl.natdb.gw.levelSource;

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
public class GwLevelSourceTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwLevelSource")
	private Tasklet deleteGwLevelSource;

	@Autowired
	@Qualifier("gwReflistGwLevelSourceReader")
	private JdbcCursorItemReader<GwReflist> gwReflistGwLevelSourceReader;

	@Autowired
	@Qualifier("gwLevelSourceProcessor")
	private BasicLookupProcessor gwLevelSourceProcessor;

	@Autowired
	@Qualifier("gwLevelSourceWriter")
	private JdbcBatchItemWriter<BasicLookup> gwLevelSourceWriter;

	@Bean
	public Step deleteGwLevelSourceStep() {
		return stepBuilderFactory.get("deleteGwLevelSourceStep")
				.tasklet(deleteGwLevelSource)
				.build();
	}

	@Bean
	public Step transformGwLevelSourceStep() {
		return stepBuilderFactory
				.get("transformGwLevelSourceStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistGwLevelSourceReader)
				.processor(gwLevelSourceProcessor)
				.writer(gwLevelSourceWriter)
				.build();
	}

	@Bean
	public Flow gwLevelSourceFlow() {
		return new FlowBuilder<SimpleFlow>("gwLevelSourceFlow")
				.start(deleteGwLevelSourceStep())
				.next(transformGwLevelSourceStep())
				.build();
	}
}
