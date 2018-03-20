# detekt-coverity-report

Export the results of detekt in a format that Coverity can read!

At the time of writing, Coverity does not analyze Kotlin and there is really no way around that fact. Coverity can
however import the results from other tools if they export them in a format that Coverity can read.

Enter Detekt, a static code analysis tool for kotlin with a plugin system. This project is a plugin to Detekt that
exports in Coveritys import format.

## How to use

**CLI**

Have a look at the excellent documentation for Detekt for more detailed information.

```
java -jar /path/to/detekt/detekt-cli-[version]-all.jar [parameters]* -p /path/to/detekt-coverity-report/dct-dep.jar
```

**Maven**

```
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-antrun-plugin</artifactId>
              <version>1.8</version>
              <executions>
                  <execution>
                      <!-- This can be run separately with mvn antrun:run@detekt -->
                      <id>detekt</id>
                      <phase>verify</phase>
                      <configuration>
                          <target name="detekt">
                              <java taskname="detekt" dir="${basedir}" fork="true" failonerror="true" classname="io.gitlab.arturbosch.detekt.cli.Main" classpathref="maven.plugin.classpath">
                                  <arg value="-i" />
                                  <arg value="${basedir}/src" />mvn
                                  <arg value="-f" />
                                  <arg value=".*test.*" />
                                  <arg value="-o" />
                                  <arg value="${basedir}/detekt" />
                                  <arg value="-c" />
                                  <arg value="${basedir}/detekt.yml" />
                                  <arg value="-p" />
                                  <arg value="${basedir}/target/dct-dep.jar" />
                              </java>
                          </target>
                      </configuration>
                      <goals><goal>run</goal></goals>
                  </execution>
              </executions>
              <dependencies>
                  <dependency>
                      <groupId>io.gitlab.arturbosch.detekt</groupId>
                      <artifactId>detekt-cli</artifactId>
                      <version>1.0.0-RC6-4</version>
                  </dependency>
                  <dependency>
                      <groupId>com.github.ozsie</groupId>
                      <artifactId>detekt-coverity-report</artifactId>
                      <version>0.0.2</version>
                  </dependency>
              </dependencies>
          </plugin>
      </plugins>
  </build>
  <pluginRepositories>
      <pluginRepository>
          <id>arturbosch-code-analysis</id>
          <name>arturbosch-code-analysis (for detekt)</name>
          <url>https://dl.bintray.com/arturbosch/code-analysis/</url>
          <layout>default</layout>
          <releases>
              <enabled>true</enabled>
              <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
              <enabled>false</enabled>
              <updatePolicy>never</updatePolicy>
          </snapshots>
      </pluginRepository>
      <pluginRepositories>
          <pluginRepository>
              <snapshots>
                  <enabled>false</enabled>
              </snapshots>
              <id>bintray-ozsie-maven</id>
              <name>bintray-plugins</name>
              <url>https://dl.bintray.com/ozsie/maven</url>
          </pluginRepository>
      </pluginRepositories>
  </pluginRepositories>
``