pipeline {
    agent {
        label 'master'
    }
    environment {
        cred_id = '62d9a573-7996-4e5b-b163-178fdcf4953f'
        docker_image_name = 'remodeling'
        docker_container_name = 'remodeling'
    }
    parameters {
        string(name: 'branch', defaultValue: 'master', description: 'Git branch')
        string(name: 'pomPath', defaultValue: 'pom.xml', description: 'pom.xml的相对路径')
        string(name: 'lineCoverage', defaultValue: '20', description: '单元测试代码覆盖率要求(%)，小于此值pipeline将会失败！')
    }
    stages {
        stage('检出代码') {
            steps {
                git credentialsId: cred_id, url: 'https://gitee.com/jeanlv/remodeling.git', branch: "$params.branch"
            }
        }
        stage('Maven编译打包并单元测试') {
            steps {
                // 注入jacoco插件配置,clean test执行单元测试代码. All tests should pass.
                sh '''
                    . ~/.bash_profile

                    cd ${WORKSPACE}
                    mvn org.jacoco:jacoco-maven-plugin:prepare-agent -f ${pomPath} clean install -Dautoconfig.skip=true -Dmaven.test.skip=false
                '''
            }
        }
        stage('SonarQube代码质量扫描') {
            steps {
                sh '''
                    . ~/.bash_profile

                    cd ${WORKSPACE}
                    mvn sonar:sonar -Dsonar.projectKey=remodeling-unit-test -Dsonar.host.url=http://60.205.228.49:9000/ -Dsonar.login=afa2c325587621bd72727adec0315d8b89c07f94 -Dsonar.branch.name=${branch}
                '''
                junit '**/target/surefire-reports/*.xml'
                // 配置单元测试覆盖率要求，未达到要求pipeline将会fail,code coverage.LineCoverage>20%.
                jacoco changeBuildStatus: true, maximumLineCoverage: "$params.lineCoverage"
            }
        }
        stage('停止 / 删除 现有Docker Container/Image ') {
            steps {
                script {
                    try {
                        sh 'docker stop $docker_container_name'
                    } catch (exc) {
                        echo 'The container $docker_container_name does not exist'
                    }

                    try {
                        sh 'docker rm $docker_container_name'
                    } catch (exc) {
                        echo 'The container $docker_container_name does not exist'
                    }

                    try {
                        sh 'docker rmi $docker_image_name'
                    } catch (exc) {
                        echo 'The docker image $docker_image_name does not exist'
                    }
                }
            }
        }
        stage('生成新的Docker Image') {
            steps {
                sh '''
                    cd ${WORKSPACE}/docker
                    rm -f remodeling-0.0.1-SNAPSHOT.jar
                    cp ${WORKSPACE}/target/remodeling-0.0.1-SNAPSHOT.jar .
                    docker build -t $docker_image_name .
                '''
            }
        }
        stage('启动新Docker实例') {
            steps {
                sh '''
                    docker run -d --name $docker_container_name -p 8089:8089 -p 6302:6302 -v /usr/local/jacoco/lib/jacocoagent.jar:/usr/local/jacocoagent.jar $docker_image_name
                '''
            }
        }
    }
    post {
        always {
            script {
                println "Do some actions when always need."
            }
        }
        failure {
            script {
                println "Do some actions when build failed."
            }
        }
//         success {
//             script {
//                 println "Here we kickoff run job SpringBoot-Restful-Api-Test-Pipeline"
//                 job_run_result = build job: 'spring-boot-restful-api-test', propagate: false, wait: true
//                 println job_run_result.getResult()
//             }
//         }
    }
}