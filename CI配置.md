# CI持续集成的配置

## Jenkins的安装与配置
Jenkins是开源CI&CD软件领导者、提供持续集成和持续交付服务，有超过1000个插件来支持构建、部署、自动化，满足任何项目的需要。

jenkins docker安装
1. 拉去jenkins的镜像
    ```shell
    # 1.Docker hub获取镜像:https://hub.docker.com/r/jenkins/jenkins/
    # 拉去镜像、安装并运行
    docker run -d --name=jenkins -p 8080:8080 jenkins/jenkins
    ```
2. 挂载目录
    ```shell
    ## 2.创建目录，并赋权限
    ## 当前目录，新建文件夹
    mkdir jenkins
    ## 目录赋权限
    chmod 777 jenkins
    ```
3. 启动jenkins的容器
    ```shell script
    docker run --name jenkins -d -p 8888:8080 -p 5000:5000 -v ${PWD}/jenkins:/var/jenkins_home -v /usr/local/jdk:/usr/local/jdk -v /usr/local/maven:/usr/local/maven jenkins/jenkins
    ```
   注意：要将本地的jdk和maven的安装，映射到容器中，容器并不带有jdk和maven的安装，如果不加，则需要在容器中配置，会很麻烦的

浏览器输入地址：http://ip:8888

## Jenkins页面的配置

# 代码审计的配置
## SonarQube
SonarQube docker安装
### 拉取镜像

1. postgres
   ```shell
   docker pull postgres
   ```
2. sonarqube
   ```shell
   docker pull sonarqube
   ```

### 启动镜像
1. 查看镜像
   ```shell
   [root@iZ2ze0gzw63rkie6uwjs74Z lvjing]# docker images
   REPOSITORY                                                        TAG                 IMAGE ID            CREATED             SIZE
   postgres                                                          latest              62473370e7ee        4 days ago          314MB
   sonarqube                                                         latest              ce76b6b9372a        4 weeks ago         461MB
   ```
2. 启动postgres容器

   ```shell
   [root@iZ2ze0gzw63rkie6uwjs74Z lvjing]# docker run --name db -e POSTGRES_USER=sonar -e POSTGRES_PASSWORD=sonar -d postgres
   770c0d5d0dea6165c2a1846d002bd682db864ddb034d9e88a5584daecb00c2de
   ```

   ```shell
   # 产品化部署--postgres
   docker run -d --name sonarqube_postgres -e POSTGRES_USER=sonar -e POSTGRES_PASSWORD=sonar -e PGDATA=/var/lib/postgresql/data/pgdata -v $PWD/postgresql:/var/lib/postgresql/data postgres
   ```

3. 进入postgres容器，建库并授权

   ```shell
   [root@iZ2ze0gzw63rkie6uwjs74Z lvjing]# docker ps
   CONTAINER ID        IMAGE                                                                    COMMAND                  CREATED             STATUS                 PORTS                                                  NAMES
   770c0d5d0dea        postgres                                                                 "docker-entrypoint.s…"   34 minutes ago      Up 34 minutes          5432/tcp                                               db
   [root@iZ2ze0gzw63rkie6uwjs74Z lvjing]# docker exec -it db bash  #（进入db容器）
   root@770c0d5d0dea:/# psql -U sonar # （进入数据库）
   psql (12.4 (Debian 12.4-1.pgdg100+1))
   Type "help" for help.
   
   sonar=# create database sonar #（建表）
   sonar=# alter role sonar createdb;alter role sonar superuser;alter role sonar createrole; # （给sonar授权）
   ALTER ROLE
   ALTER ROLE
   ALTER ROLE
   sonar=# alter database sonar owner to sonar; #（sonar 数据库owner）
   ALTER DATABASE
   sonar=# exit # （退出数据库）
   root@770c0d5d0dea:/# exit #（退出容器）
   exit
   ``` 

4. 启动sonarqube容器

   ```shell
   [root@iZ2ze0gzw63rkie6uwjs74Z lvjing]# docker run --name sq --link db  -e SONARQUBE_JDBC_URL=jdbc:postgresql://db:5432/sonar -e SONARQUBE_JDBC_USERNAME=sonar -e SONARQUBE_JDBC_PASSWORD=sonar -p 9000:9000 -d sonarqube
   d4a502d03f11f5ef31201ff8ae1849f49bc77e5ac1f0a1f44ebdabffe89ada7e
 
   ```

   ```shell
   # 产品化部署(8.x以上的版本部署)
   mkdir sonarqube_data sonarqube_extensions sonarqube_logs
   chown -R 999:999 sonarqube_data sonarqube_extensions sonarqube_logs
   docker run -d --name sonarqube -p 9000:9000 -p 9092:9092 --link sonarqube_postgres: -e SONAEQUBE_JDBC_USERNAME=sonarqube -e SONAEQUBE_JDBC_PASSWORD=sonarqube -e SONARQUBE_JDBC_URL="jdbc:postgresql://db:5432/sonarqube" -v $PWD/sonarqube_data:/opt/sonarqube/data -v $PWD/sonarqube_extensions:/opt/sonarqube/extensions -v $PWD/sonarqube_logs:/opt/sonarqube/logs sonarqube
   ``` 

启动报错

```shell
[root@iZ2ze0gzw63rkie6uwjs74Z lvjing]# docker logs -f 6b085e241e43
2020.08.19 08:04:32 INFO  app[][o.s.a.AppFileSystem] Cleaning or creating temp directory /opt/sonarqube/temp
2020.08.19 08:04:32 INFO  app[][o.s.a.es.EsSettings] Elasticsearch listening on /127.0.0.1:9001
2020.08.19 08:04:32 INFO  app[][o.s.a.ProcessLauncherImpl] Launch process[[key='es', ipcIndex=1, logFilenamePrefix=es]] from [/opt/sonarqube/elasticsearch]: /opt/sonarqube/elasticsearch/bin/elasticsearch
2020.08.19 08:04:32 INFO  app[][o.s.a.SchedulerImpl] Waiting for Elasticsearch to be up and running
2020.08.19 08:04:32 INFO  app[][o.e.p.PluginsService] no modules loaded
2020.08.19 08:04:32 INFO  app[][o.e.p.PluginsService] loaded plugin [org.elasticsearch.transport.Netty4Plugin]
OpenJDK 64-Bit Server VM warning: Option UseConcMarkSweepGC was deprecated in version 9.0 and will likely be removed in a future release.
2020.08.19 08:04:34 INFO  es[][o.e.e.NodeEnvironment] using [1] data paths, mounts [[/ (overlay)]], net usable_space [31.1gb], net total_space [39.9gb], types [overlay]
2020.08.19 08:04:34 INFO  es[][o.e.e.NodeEnvironment] heap size [494.9mb], compressed ordinary object pointers [true]
2020.08.19 08:04:34 INFO  es[][o.e.n.Node] node name [sonarqube], node ID [SKVuhHPgQ7aq-BBErKxEPQ]
2020.08.19 08:04:34 INFO  es[][o.e.n.Node] version[6.8.4], pid[38], build[default/tar/bca0c8d/2019-10-16T06:19:49.319352Z], OS[Linux/4.18.0-147.5.1.el8_1.x86_64/amd64], JVM[AdoptOpenJDK/OpenJDK 64-Bit Server VM/11.0.6/11.0.6+10]
2020.08.19 08:04:34 INFO  es[][o.e.n.Node] JVM arguments [-XX:+UseConcMarkSweepGC, -XX:CMSInitiatingOccupancyFraction=75, -XX:+UseCMSInitiatingOccupancyOnly, -Des.networkaddress.cache.ttl=60, -Des.networkaddress.cache.negative.ttl=10, -XX:+AlwaysPreTouch, -Xss1m, -Djava.awt.headless=true, -Dfile.encoding=UTF-8, -Djna.nosys=true, -XX:-OmitStackTraceInFastThrow, -Dio.netty.noUnsafe=true, -Dio.netty.noKeySetOptimization=true, -Dio.netty.recycler.maxCapacityPerThread=0, -Dlog4j.shutdownHookEnabled=false, -Dlog4j2.disable.jmx=true, -Djava.io.tmpdir=/opt/sonarqube/temp, -XX:ErrorFile=../logs/es_hs_err_pid%p.log, -Des.enforce.bootstrap.checks=true, -Xmx512m, -Xms512m, -XX:+HeapDumpOnOutOfMemoryError, -Des.path.home=/opt/sonarqube/elasticsearch, -Des.path.conf=/opt/sonarqube/temp/conf/es, -Des.distribution.flavor=default, -Des.distribution.type=tar]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [analysis-common]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [lang-painless]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [mapper-extras]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [parent-join]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [percolator]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [reindex]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [repository-url]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] loaded module [transport-netty4]
2020.08.19 08:04:34 INFO  es[][o.e.p.PluginsService] no plugins loaded
2020.08.19 08:04:36 WARN  es[][o.e.d.c.s.Settings] [http.enabled] setting was deprecated in Elasticsearch and will be removed in a future release! See the breaking changes documentation for the next major version.
2020.08.19 08:04:36 INFO  es[][o.e.d.DiscoveryModule] using discovery type [zen] and host providers [settings]
2020.08.19 08:04:37 INFO  es[][o.e.n.Node] initialized
2020.08.19 08:04:37 INFO  es[][o.e.n.Node] starting ...
2020.08.19 08:04:37 INFO  es[][o.e.t.TransportService] publish_address {127.0.0.1:9001}, bound_addresses {127.0.0.1:9001}
2020.08.19 08:04:37 INFO  es[][o.e.b.BootstrapChecks] explicitly enforcing bootstrap checks
ERROR: [1] bootstrap checks failed
[1]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
2020.08.19 08:04:37 INFO  es[][o.e.n.Node] stopping ...
2020.08.19 08:04:37 INFO  es[][o.e.n.Node] stopped
2020.08.19 08:04:37 INFO  es[][o.e.n.Node] closing ...
2020.08.19 08:04:37 INFO  es[][o.e.n.Node] closed
2020.08.19 08:04:37 WARN  app[][o.s.a.p.AbstractManagedProcess] Process exited with exit value [es]: 78
2020.08.19 08:04:37 INFO  app[][o.s.a.SchedulerImpl] Process[es] is stopped
2020.08.19 08:04:37 INFO  app[][o.s.a.SchedulerImpl] SonarQube is stopped
```

从上面的log查看错误

```shell
ERROR: [1] bootstrap checks failed
[1]: max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
```

解决方案

```shell
# vi /etc/sysctl.conf # 修改本地文件的内存大小值（添加，vm.max_map_count = 655360）
# sysctl -p
# 再次启动SonarQube
```

### 访问SonarQube

- 访问地址：http://ip:9000/
- 用户名：admin/admin