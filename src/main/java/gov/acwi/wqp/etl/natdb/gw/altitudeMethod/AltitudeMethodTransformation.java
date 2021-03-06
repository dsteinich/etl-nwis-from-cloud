package gov.acwi.wqp.etl.natdb.gw.altitudeMethod;

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
public class AltitudeMethodTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteAltitudeMethod")
	private Tasklet deleteAltitudeMethod;

	@Autowired
	@Qualifier("gwReflistAltitudeMethodReader")
	private JdbcCursorItemReader<GwReflist> gwReflistAltitudeMethodReader;

	@Autowired
	@Qualifier("altitudeMethodProcessor")
	private BasicLookupProcessor altitudeMethodProcessor;

	@Autowired
	@Qualifier("altitudeMethodWriter")
	private JdbcBatchItemWriter<BasicLookup> altitudeMethodWriter;

	@Bean
	public Step deleteAltitudeMethodStep() {
		return stepBuilderFactory.get("deleteAltitudeMethodStep")
				.tasklet(deleteAltitudeMethod)
				.build();
	}

	@Bean
	public Step transformAltitudeMethodStep() {
		return stepBuilderFactory
				.get("transformAltitudeMethodStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistAltitudeMethodReader)
				.processor(altitudeMethodProcessor)
				.writer(altitudeMethodWriter)
				.build();
	}

	@Bean
	public Flow altitudeMethodFlow() {
		return new FlowBuilder<SimpleFlow>("altitudeMethodFlow")
				.start(deleteAltitudeMethodStep())
				.next(transformAltitudeMethodStep())
				.build();
	}
}
