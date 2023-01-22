package plugins.activity_search

import org.gradle.api.Plugin
import org.gradle.api.Project

class ActivitySearchPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.task("hello"){
            doLast{
                println("Hello from the Greeting Plugin")
            }
        }
    }
}
