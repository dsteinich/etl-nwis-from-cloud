package gov.acwi.wqp.etl;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    @Qualifier("nawqaSitesFlow")
    private Flow nawqaSitesFlow;

    @Bean
    public Job nwisFromCloudEtl() {
        return jobBuilderFactory.get("WQP_NWIS_FROM_CLOUD_ETL")
                .start(nawqaSitesFlow)
                .build()
                .build();
    }
}
