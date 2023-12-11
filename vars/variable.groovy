// my-shared-library/vars/createPipelineJob.groovy
def call(Map config) {
    def jobName = config.jobName ?: 'defaultJobName'

    pipelineJob(jobName) {
        description("Pipeline for ${jobName}")
        definition {
            echo "Job name inside DSL script: ${jobName}"
            
            // Additional DSL script configurations...
        }
    }
}

