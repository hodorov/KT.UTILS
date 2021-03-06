@file:Suppress("SpringJavaInjectionPointsAutowiringInspection")

package ru.hodorov.ktutils.h2.autoconfigure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import ru.hodorov.ktutils.base.service.AppFolderService
import javax.sql.DataSource


@Configuration
class H2Configuration: DataSourceAutoConfiguration() {

    @Autowired
    private lateinit var appFolderService: AppFolderService

    @Bean(name = ["H2DataSource"])
    @Primary
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
                .url("jdbc:h2:${appFolderService.getAppFolder().canonicalPath}/db.h2;DB_CLOSE_ON_EXIT=FALSE;")
                .build()
    }
}