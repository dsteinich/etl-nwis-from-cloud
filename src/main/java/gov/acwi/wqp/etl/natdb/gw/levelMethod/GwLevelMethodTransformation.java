package gov.acwi.wqp.etl.natdb.gw.levelMethod;

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
public class GwLevelMethodTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwLevelMethod")
	private Tasklet deleteGwLevelMethod;

	@Autowired
	@Qualifier("gwReflistGwLevelMethodReader")
	private JdbcCursorItemReader<GwReflist> gwReflistGwLevelMethodReader;

	@Autowired
	@Qualifier("gwLevelMethodProcessor")
	private BasicLookupProcessor gwLevelMethodProcessor;

	@Autowired
	@Qualifier("gwLevelMethodWriter")
	private JdbcBatchItemWriter<BasicLookup> gwLevelMethodWriter;

	@Bean
	public Step deleteGwLevelMethodStep() {
		return stepBuilderFactory.get("deleteGwLevelMethodStep")
				.tasklet(deleteGwLevelMethod)
				.build();
	}

	@Bean
	public Step transformGwLevelMethodStep() {
		return stepBuilderFactory
				.get("transformGwLevelMethodStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistGwLevelMethodReader)
				.processor(gwLevelMethodProcessor)
				.writer(gwLevelMethodWriter)
				.build();
	}

	@Bean
	public Flow gwLevelMethodFlow() {
		return new FlowBuilder<SimpleFlow>("gwLevelMethodFlow")
				.start(deleteGwLevelMethodStep())
				.next(transformGwLevelMethodStep())
				.build();
	}
}
