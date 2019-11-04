package gov.acwi.wqp.etl.natdb.huc;

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

@Configuration
public class HucTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteHuc")
	private Tasklet deleteHuc;

	@Autowired
	@Qualifier("hucReader")
	private JdbcCursorItemReader<Huc> hucReader;

	@Autowired
	@Qualifier("hucProcessor")
	private HucProcessor hucProcessor;

	@Autowired
	@Qualifier("hucWriter")
	private JdbcBatchItemWriter<Huc> hucWriter;

	@Bean
	public Step deleteHucStep() {
		return stepBuilderFactory.get("deleteHuc")
				.tasklet(deleteHuc)
				.build();
	}

	@Bean
	public Step transformHucStep() {
		return stepBuilderFactory
				.get("transformHucStep")
				.<Huc, Huc> chunk(1000)
				.reader(hucReader)
				.processor(hucProcessor)
				.writer(hucWriter)
				.build();
	}

	@Bean
	public Flow hucFlow() {
		return new FlowBuilder<SimpleFlow>("hucFlow")
				.start(deleteHucStep())
				.next(transformHucStep())
				.build();
	}

}
