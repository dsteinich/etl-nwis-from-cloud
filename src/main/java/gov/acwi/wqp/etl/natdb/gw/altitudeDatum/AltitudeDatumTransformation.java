package gov.acwi.wqp.etl.natdb.gw.altitudeDatum;

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
public class AltitudeDatumTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteAltitudeDatum")
	private Tasklet deleteAltitudeDatum;

	@Autowired
	@Qualifier("gwReflistAltitudeDatumReader")
	private JdbcCursorItemReader<GwReflist> gwReflistAltitudeDatumReader;

	@Autowired
	@Qualifier("altitudeDatumProcessor")
	private BasicLookupProcessor altitudeDatumProcessor;

	@Autowired
	@Qualifier("altitudeDatumWriter")
	private JdbcBatchItemWriter<BasicLookup> altitudeDatumWriter;

	@Bean
	public Step deleteAltitudeDatumStep() {
		return stepBuilderFactory.get("deleteAltitudeDatumStep")
				.tasklet(deleteAltitudeDatum)
				.build();
	}

	@Bean
	public Step transformAltitudeDatumStep() {
		return stepBuilderFactory
				.get("transformAltitudeDatumStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistAltitudeDatumReader)
				.processor(altitudeDatumProcessor)
				.writer(altitudeDatumWriter)
				.build();
	}

	@Bean
	public Flow altitudeDatumFlow() {
		return new FlowBuilder<SimpleFlow>("altitudeDatumFlow")
				.start(deleteAltitudeDatumStep())
				.next(transformAltitudeDatumStep())
				.build();
	}
}
