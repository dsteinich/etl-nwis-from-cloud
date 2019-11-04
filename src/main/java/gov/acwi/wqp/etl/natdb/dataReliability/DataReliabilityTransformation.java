package gov.acwi.wqp.etl.natdb.dataReliability;

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

import gov.acwi.wqp.etl.natdb.BasicLookup;
import gov.acwi.wqp.etl.natdb.BasicLookupProcessor;
import gov.acwi.wqp.etl.natdb.GwReflist;

@Configuration
public class DataReliabilityTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteDataReliability")
	private Tasklet deleteDataReliability;

	@Autowired
	@Qualifier("gwReflistDataReliabilityReader")
	private JdbcCursorItemReader<GwReflist> gwReflistDataReliabilityReader;

	@Autowired
	@Qualifier("dataReliabilityProcessor")
	private BasicLookupProcessor dataReliabilityProcessor;

	@Autowired
	@Qualifier("dataReliabilityWriter")
	private JdbcBatchItemWriter<BasicLookup> dataReliabilityWriter;

	@Bean
	public Step deleteDataReliabilityStep() {
		return stepBuilderFactory.get("deleteDataReliabilityStep")
				.tasklet(deleteDataReliability)
				.build();
	}

	@Bean
	public Step transformDataReliabilityStep() {
		return stepBuilderFactory
				.get("transformDataReliabilityStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistDataReliabilityReader)
				.processor(dataReliabilityProcessor)
				.writer(dataReliabilityWriter)
				.build();
	}

	@Bean
	public Flow dataReliabilityFlow() {
		return new FlowBuilder<SimpleFlow>("dataReliabilityFlow")
				.start(deleteDataReliabilityStep())
				.next(transformDataReliabilityStep())
				.build();
	}
}
