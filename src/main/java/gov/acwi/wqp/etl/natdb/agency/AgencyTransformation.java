package gov.acwi.wqp.etl.natdb.agency;

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
public class AgencyTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteAgency")
	private Tasklet deleteAgency;

	@Autowired
	@Qualifier("agencyReader")
	private JdbcCursorItemReader<Agency> agencyReader;

	@Autowired
	@Qualifier("agencyProcessor")
	private AgencyProcessor agencyProcessor;

	@Autowired
	@Qualifier("agencyWriter")
	private JdbcBatchItemWriter<Agency> agencyWriter;

	@Bean
	public Step deleteAgencyStep() {
		return stepBuilderFactory.get("deleteAgency")
				.tasklet(deleteAgency)
				.build();
	}

	@Bean
	public Step transformAgencyStep() {
		return stepBuilderFactory
				.get("transformAgencyStep")
				.<Agency, Agency> chunk(1000)
				.reader(agencyReader)
				.processor(agencyProcessor)
				.writer(agencyWriter)
				.build();
	}

	@Bean
	public Flow agencyFlow() {
		return new FlowBuilder<SimpleFlow>("agencyFlow")
				.start(deleteAgencyStep())
				.next(transformAgencyStep())
				.build();
	}

}
