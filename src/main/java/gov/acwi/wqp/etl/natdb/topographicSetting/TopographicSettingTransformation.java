package gov.acwi.wqp.etl.natdb.topographicSetting;

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
public class TopographicSettingTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteTopographicSetting")
	private Tasklet deleteTopographicSetting;

	@Autowired
	@Qualifier("gwReflistTopographicSettingReader")
	private JdbcCursorItemReader<GwReflist> gwReflistTopographicSettingReader;

	@Autowired
	@Qualifier("topographicSettingProcessor")
	private BasicLookupProcessor topographicSettingProcessor;

	@Autowired
	@Qualifier("topographicSettingWriter")
	private JdbcBatchItemWriter<BasicLookup> topographicSettingWriter;

	@Bean
	public Step deleteTopographicSettingStep() {
		return stepBuilderFactory.get("deleteTopographicSettingStep")
				.tasklet(deleteTopographicSetting)
				.build();
	}

	@Bean
	public Step transformTopographicSettingStep() {
		return stepBuilderFactory
				.get("transformTopographicSettingStep")
				.<GwReflist, BasicLookup>chunk(1000)
				.reader(gwReflistTopographicSettingReader)
				.processor(topographicSettingProcessor)
				.writer(topographicSettingWriter)
				.build();
	}

	@Bean
	public Flow topographicSettingFlow() {
		return new FlowBuilder<SimpleFlow>("topographicSettingFlow")
				.start(deleteTopographicSettingStep())
				.next(transformTopographicSettingStep())
				.build();
	}
}
