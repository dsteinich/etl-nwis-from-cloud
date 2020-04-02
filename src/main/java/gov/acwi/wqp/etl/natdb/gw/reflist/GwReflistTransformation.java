package gov.acwi.wqp.etl.natdb.gw.reflist;

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

import gov.acwi.wqp.etl.natdb.gw.GwReflist;

@Configuration
public class GwReflistTransformation {

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	@Qualifier("deleteGwReflist")
	private Tasklet deleteGwReflist;

	@Autowired
	@Qualifier("gwReflistReader")
	private JdbcCursorItemReader<GwReflist> gwReflistReader;

	@Autowired
	@Qualifier("gwReflistProcessor")
	private GwReflistProcessor gwReflistProcessor;

	@Autowired
	@Qualifier("gwReflistWriter")
	private JdbcBatchItemWriter<GwReflist> gwReflistWriter;

	@Bean
	public Step deleteGwReflistStep() {
		return stepBuilderFactory.get("deleteGwReflistStep")
				.tasklet(deleteGwReflist)
				.build();
	}

	@Bean
	public Step transformGwReflistStep() {
		return stepBuilderFactory
				.get("transformGwReflistStep")
				.<GwReflist, GwReflist>chunk(1000)
				.reader(gwReflistReader)
				.processor(gwReflistProcessor)
				.writer(gwReflistWriter)
				.build();
	}

	@Bean
	public Flow gwReflistFlow() {
		return new FlowBuilder<SimpleFlow>("gwReflistFlow")
				.start(deleteGwReflistStep())
				.next(transformGwReflistStep())
				.build();
	}
}
