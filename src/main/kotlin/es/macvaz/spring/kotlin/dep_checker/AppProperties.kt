package es.macvaz.spring.kotlin.dep_checker

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("blog")
data class AppProperties(var title: String, val banner: Banner) {
	data class Banner(val title: String? = null, val content: String)
}
