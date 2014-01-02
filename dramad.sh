#!/bin/sh
# FIXME: replace this with something more portable, eg get maven to generate the classpath
java -cp target/dramad-1.0-SNAPSHOT.jar:/home/galdosd/.m2/repository/com/typesafe/akka/akka-actor_2.10/2.2.3/akka-actor_2.10-2.2.3.jar:/home/galdosd/.m2/repository/org/scala-lang/scala-library/2.10.2/scala-library-2.10.2.jar:/home/galdosd/.m2/repository/com/typesafe/config/1.0.2/config-1.0.2.jar akka.Main com.cmwp.dramad.DaemonActor
